package com.example.jobspriorityqueue

open class JobExecutor {
    private val jobQueue = JobQueue()


    companion object {
        /*
        Number of jobs currently executing
         */
        private var taskCount : Int = 0

        /*
        No of jobs that can be executed in parallel
         */
        private const val NO_OF_PARALLEL_TASK_ALLOWED = 50
    }

    /**
     * Purpose of this method is to add jobs in priority queue
     * and run execute method if no job is currently running
     */
    fun addJob(job: JobHandler) {
        jobQueue.addElement(job)
        if (taskCount == 0) {
            execute()
        }
    }

    /**
     * This method is responsible to fetch jobs from queue and execute it parallel
     */
    fun execute ()  {
        while (taskCount <= NO_OF_PARALLEL_TASK_ALLOWED) {
            if (!jobQueue.isQueueEmpty()) {
                taskCount++
                val myJob = jobQueue.removeElement()
                myJob?.execute(myJob.job.myTaskObject, (object : JobHandler.OnTaskCompleteListener {
                    override fun onTaskCompleted() {
                        postExecute(myJob)
                    }

                    override fun onError() {
                        jobQueue.addElement(myJob)
                    }
                }))
            } else {
                break
            }
        }
    }

    /**
     * This method is responsible for executing post job execution operations
     */
    private fun postExecute(job: JobHandler){
        taskCount--
        job.postExecute()

        /*
        If queue still consist of jobs even after
        execution of total number of allowed task
        then run execute method again
         */
        if (taskCount == 0 && !jobQueue.isQueueEmpty()) {
            execute()
        }
    }

    fun clearQueue() {
        jobQueue.clearQueue()
    }

}