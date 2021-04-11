package com.example.jobspriorityqueue

import java.util.*

class JobQueue {

    init {
        instance
    }

    fun addElement(job: JobHandler) {
        queue!!.add(job)
    }

    fun removeElement(): JobHandler? {
        return if (!queue!!.isEmpty()) {
            queue!!.poll()
        } else null
    }

    fun isQueueEmpty() : Boolean {
        return queue.isNullOrEmpty()
    }

    companion object Singleton {

        private var queue: PriorityQueue<JobHandler>? = null


        val instance: PriorityQueue<*>
            get() {
                if (queue == null) {
                    queue = PriorityQueue(11, PriorityComparator())
                }
                return queue!!
            }
    }


    fun clearQueue() {
        queue?.clear();
    }
}