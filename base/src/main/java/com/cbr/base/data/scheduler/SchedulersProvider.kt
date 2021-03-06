package com.cbr.base.data.scheduler

import io.reactivex.Scheduler

interface SchedulersProvider {

    fun io(): Scheduler

    fun ui(): Scheduler

    fun cpu() : Scheduler
}
