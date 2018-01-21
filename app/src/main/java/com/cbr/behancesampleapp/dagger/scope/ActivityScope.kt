package com.cbr.behancesampleapp.dagger.scope

import javax.inject.Scope

/**
 * Created by dimitrios on 24/08/2017.
 *
 * Taken From https://github.com/googlesamples/android-architecture/blob/todo-mvp-dagger/
 * In Dagger, an unscoped component cannot depend on a scoped component. As
 * [com.cbr.behancesampleapp.dagger.AppComponent] is a scoped component (`@Singleton`, we create a custom
 * scope to be used by all fragment components. Additionally, a component with a specific scope
 * cannot have a sub component with the same scope.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope
