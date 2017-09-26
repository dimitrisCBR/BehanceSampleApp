package com.cbr.behancesampleapp.dagger.scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Scope;

/**
 * Created by dimitrios on 24/08/2017.
 * <p>
 * Taken From https://github.com/googlesamples/android-architecture/blob/todo-mvp-dagger/
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface FragmentScope {

}
