package com.cbr.behancesampleapp.dagger.module;

import android.content.Context;

import com.cbr.behancesampleapp.BuildConfig;
import com.cbr.behancesampleapp.domain.network.BehanceApiService;
import com.cbr.behancesampleapp.domain.network.BehanceRepository;
import com.cbr.behancesampleapp.domain.network.Urls;
import com.cbr.behancesampleapp.util.NetworkUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dimitrios on 22/08/2017.
 */
@Module
public class NetModule {

	private static final String HEADER_CACHE_CONTROL = "Cache-Control";
	private static final int CACHE_SIZE = 50 * 1024 * 1024; //50 MB

	@Provides
	static Cache providesCache(Context context) {
		return new Cache(context.getCacheDir(), CACHE_SIZE);
	}

	@Provides
	static Interceptor providesOkHttpInterceptor(final Context context) {
		return new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request();

				if (NetworkUtil.isNetworkAvailable(context)) {
					request = request.newBuilder().header(HEADER_CACHE_CONTROL, "public, max-age=" + 60 * 60 * 24).build();
				} else {
					request = request.newBuilder().header(HEADER_CACHE_CONTROL, "public, only-if-cached, max-stale=" + 60 * 60 * 24).build();
				}

				return chain.proceed(request);
			}
		};
	}

	@Provides
	static OkHttpClient providesOkHttpClient(Interceptor interceptor, Cache cache) {

		OkHttpClient.Builder client = new OkHttpClient.Builder();

		final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

		if (BuildConfig.DEBUG) {
			//For Web Debugging.
			logging.setLevel(HttpLoggingInterceptor.Level.BODY);
		} else {
			logging.setLevel(HttpLoggingInterceptor.Level.NONE);
		}

		return client
			.addInterceptor(logging)
			.addNetworkInterceptor(interceptor)
			.cache(cache)
			.connectTimeout(10, TimeUnit.SECONDS)
			.build();
	}

	@Provides
	static Retrofit providesRetrofit(OkHttpClient client) {
		return new Retrofit.Builder()
			.baseUrl(Urls.Behance.BASE_URL)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create(new Gson()))
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.build();
	}

	@Provides
	static BehanceApiService providesBehanceApiService(Retrofit retrofit) {
		return retrofit.create(BehanceApiService.class);
	}

	@Provides
	static BehanceRepository providesBehanceRepository(BehanceApiService service) {
		return new BehanceRepository(service);
	}
}
