Jobs Priority Queue to handle jobs with different priority efficiently.

Download the latest AAR from Maven Central or grab via Gradle:

![image](https://user-images.githubusercontent.com/26744380/114300152-3a7fce80-9ad8-11eb-8e56-544cd6a7b3de.png)


Create a class for your own job execution.

For Example:

class MyJob(jobSuccessListener: OnJobSuccessListener?) : JobHandler(jobSuccessListener) {


    override fun execute(myTask: Any, onTaskCompleteListener: OnTaskCompleteListener) {
        super.execute(myTask, onTaskCompleteListener)
        /*
        Add job to be executed here
         */
        
        // Call this listener on the end of your job
        onTaskCompleteListener.onTaskCompleted()
        
        
        /* if task not completed successfully like any realm job failed
        etc so this task will be again added in the queue
         */
        onTaskCompleteListener.onError()
    }

    override fun postExecute() {
        /*
        This method is call on successful execution of your Job
         */
    }
    
}


Now on your Main activity create your job object and add it in the job queue.

var myJob : MyJob = JobHandler(object : onSuccessfulJobCompletion {
  /* On successful completetion this listener will be called and you can further proceed your work on successful completion. */
})

myJob.addJob(myJob.createJob(*your object to be used in execution*, JobHandler.PRIORITY_HIGHEST *add priority of your choice*))

Thats it... 
