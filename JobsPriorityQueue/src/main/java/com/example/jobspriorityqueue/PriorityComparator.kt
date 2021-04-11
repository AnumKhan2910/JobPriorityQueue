package com.example.jobspriorityqueue

import java.util.Comparator

class PriorityComparator : Comparator<JobHandler> {

    override fun compare(o1: JobHandler, o2: JobHandler): Int {
        return o1.job.priority.compareTo(o2.job.priority)
    }
}
