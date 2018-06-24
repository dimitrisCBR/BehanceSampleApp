package com.cbr.behancesampleapp.domain.dagger.scope

import javax.inject.Scope

/**
 * Created by dimitrios on 24/08/2017.
 *
 *
 * Taken From https://github.com/googlesamples/android-architecture/blob/todo-mvp-dagger/
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class FragmentScope
