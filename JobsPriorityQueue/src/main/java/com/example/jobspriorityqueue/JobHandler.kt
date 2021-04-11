package com.example.jobspriorityqueue

import java.io.Serializable
import java.util.*

open class JobHandler(var onJobSuccessListener: OnJobSuccessListener?) {

    private val jobExecutor: com.example.jobspriorityqueue.JobExecutor =
        com.example.jobspriorityqueue.JobExecutor()

    var job = com.example.jobspriorityqueue.Job()


    fun createJob(myTask: Any, priority: Int) : JobHandler {
        job.myTaskObject = myTask
        job.priority = priority
        job.jobId = generateUUID()
        return this
    }


    fun addJob(job: JobHandler){
        jobExecutor.addJob(job)
    }


    fun removeAllJobs() {
        jobExecutor.clearQueue()
    }

    open fun execute(myTask: Any, onTaskCompleteListener: OnTaskCompleteListener) {
    }


    open fun postExecute() {
        if (onJobSuccessListener != null) {
            onJobSuccessListener?.onSuccessfulJobCompletion()
        }
    }


    companion object {
        const val PRIORITY_HIGHEST : Int = 1;
        const val PRIORITY_HIGH : Int = 2;
        const val PRIORITY_MEDIUM : Int = 3;
        const val PRIORITY_LOW : Int = 4;
    }

    interface OnTaskCompleteListener {
        fun onTaskCompleted()
        fun onError()
    }

    interface OnJobSuccessListener : Serializable {
        fun onSuccessfulJobCompletion()
    }

    private fun generateUUID(): String {
        return UUID.randomUUID().toString()
    }

}