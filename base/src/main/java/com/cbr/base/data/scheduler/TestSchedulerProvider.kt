package com.cbr.base.data.scheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider : SchedulersProvider {

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler = Schedulers.trampoline()

    override fun cpu(): Scheduler = Schedulers.trampoline()

}