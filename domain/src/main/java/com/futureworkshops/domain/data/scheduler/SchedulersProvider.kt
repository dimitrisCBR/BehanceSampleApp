package com.futureworkshops.domain.data.scheduler

import io.reactivex.Scheduler

interface SchedulersProvider {

    fun io(): Scheduler

    fun ui(): Scheduler
}
