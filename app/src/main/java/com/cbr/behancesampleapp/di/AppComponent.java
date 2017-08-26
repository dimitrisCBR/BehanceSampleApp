package com.cbr.behancesampleapp.di;

import android.app.Application;

import com.cbr.behancesampleapp.BehanceSampleApp;
import com.cbr.behancesampleapp.di.module.ActivityGraphBinderModule;
import com.cbr.behancesampleapp.di.module.AppModule;
import com.cbr.behancesampleapp.di.module.NetModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by dimitrios on 22/08/2017.
 * <p>
 * Taken From https://github.com/googlesamples/android-architecture/blob/todo-mvp-dagger/
 * <p>
 * This is a Dagger component. Refer to {@link com.cbr.behancesampleapp.BehanceSampleApp} for the list of Dagger components
 * used in this application.
 * <p>
 * Even though Dagger allows annotating a {@link Component} as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in {@link com.cbr.behancesampleapp.BehanceSampleApp}.
 * <p>
 * //{@link AndroidInjectionModule}
 * // is the module from Dagger.Android that helps with the generation
 * // and location of subcomponents.
 */
@Singleton
@Component(modules = {
	AndroidInjectionModule.class,
	ActivityGraphBinderModule.class,
	AppModule.class,
	NetModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

	@Component.Builder
	interface Builder {

		@BindsInstance
		Builder application(Application application);

		AppComponent build();
	}

	void inject(BehanceSampleApp application);

}
