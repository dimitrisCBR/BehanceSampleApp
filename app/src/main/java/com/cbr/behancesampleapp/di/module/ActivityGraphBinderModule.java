package com.cbr.behancesampleapp.di.module;


import com.cbr.behancesampleapp.di.scope.ActivityScope;
import com.cbr.behancesampleapp.ui.userdetails.UserDetailsActivity;
import com.cbr.behancesampleapp.ui.userdetails.di.UserDetailsActivityModule;
import com.cbr.behancesampleapp.ui.landing.LandingActivity;
import com.cbr.behancesampleapp.ui.landing.di.LandingActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by dimitrios on 24/08/2017.
 * <p>
 * Taken From https://github.com/googlesamples/android-architecture/blob/todo-mvp-dagger/
 * <p>
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be {@link com.cbr.behancesampleapp.di.AppComponent} (check the included "modules=").
 * <p>
 * The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * <p>
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create X subcomponents for us, where X is the number of @ContributesAndroidInjector annotated methods.
 */
@Module
public abstract class ActivityGraphBinderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = LandingActivityModule.class)
    abstract LandingActivity bindMainActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = UserDetailsActivityModule.class)
    abstract UserDetailsActivity bindDetailsActivity();

}
