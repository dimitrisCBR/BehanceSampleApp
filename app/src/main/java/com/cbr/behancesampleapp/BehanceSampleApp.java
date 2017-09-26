package com.cbr.behancesampleapp;

import android.app.Activity;
import android.app.Application;

import com.cbr.behancesampleapp.dagger.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public class BehanceSampleApp extends Application implements HasActivityInjector {

	@Inject
	DispatchingAndroidInjector<Activity> mActivityInjector;

	@Override
	public void onCreate() {
		super.onCreate();
		DaggerAppComponent.builder()
			.application(this)
			.build()
			.inject(this);
	}

	@Override
	public AndroidInjector<Activity> activityInjector() {
		return mActivityInjector;
	}
}