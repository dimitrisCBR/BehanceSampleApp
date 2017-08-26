package com.cbr.behancesampleapp.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by dimitrios on 24/08/2017.
 *
 * Taken From https://github.com/googlesamples/android-architecture/blob/todo-mvp-dagger/
 * In Dagger, an unscoped component cannot depend on a scoped component. As
 * {@link com.cbr.behancesampleapp.di.AppComponent} is a scoped component ({@code @Singleton}, we create a custom
 * scope to be used by all fragment components. Additionally, a component with a specific scope
 * cannot have a sub component with the same scope.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {

}
