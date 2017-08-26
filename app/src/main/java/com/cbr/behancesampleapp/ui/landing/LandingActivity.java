package com.cbr.behancesampleapp.ui.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cbr.behancesampleapp.R;
import com.cbr.behancesampleapp.network.BehanceRepository;
import com.cbr.behancesampleapp.network.BehanceSubscriber;
import com.cbr.behancesampleapp.network.query.UsersQuery;
import com.example.dimitrios.disample.model.BehanceUser;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import retrofit2.Response;

public class LandingActivity extends AppCompatActivity {

	@Inject
	BehanceRepository mBehanceRepository;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		AndroidInjection.inject(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mBehanceRepository.getUserById(new UsersQuery().withCountry("gr").build())
					.subscribe(new BehanceSubscriber<Response<BehanceUser>>() {

						@Override
						public void onNext(Response<BehanceUser> behanceUserResponse) {

						}

						@Override
						public void onError(Throwable e) {

						}
					});
			}
		});
	}

}
