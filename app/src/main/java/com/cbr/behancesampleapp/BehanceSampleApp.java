package com.cbr.behancesampleapp;

import com.cbr.behancesampleapp.di.AppComponent;
import com.cbr.behancesampleapp.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public class BehanceSampleApp extends DaggerApplication {

	@Override
	protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
		AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
		appComponent.inject(this);
		return appComponent;
	}

}