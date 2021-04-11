package com.example.jobspriorityqueue
import java.io.Serializable

class MyJob(jobSuccessListener: OnJobSuccessListener?, var onJobComplete: OnJobComplete) : com.example.jobspriorityqueue.JobHandler(jobSuccessListener) {

    var incNum : Int = 0

    override fun execute(myTask: Any, onTaskCompleteListener: OnTaskCompleteListener) {
        super.execute(myTask, onTaskCompleteListener)
        val num : String = myTask as String
        incNum = num.toInt() + 1
        onTaskCompleteListener.onTaskCompleted()
    }

    override fun postExecute() {
        onJobComplete.onJobCompleted(incNum)
    }

    interface OnJobComplete : Serializable {
        fun onJobCompleted(int: Int)
    }

}