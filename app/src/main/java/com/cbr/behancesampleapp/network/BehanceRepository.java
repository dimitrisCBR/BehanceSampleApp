package com.cbr.behancesampleapp.network;

import com.example.dimitrios.disample.model.BehanceUser;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.QueryMap;

/**
 * Created by dimitrios on 25/08/2017.
 */
public class BehanceRepository extends DataRepository {

    private BehanceApiService mBehanceApiService;

    public BehanceRepository(BehanceApiService service) {
        this.mBehanceApiService = service;
    }

    public Observable<Response<List<BehanceUser>>> getUsers(Map<String, Object> params) {
        return mBehanceApiService.getUsers(params).compose(this.<Response<List<BehanceUser>>>applySchedulers());
    }

    public Observable<Response<BehanceUser>> getUserById(Map<String, Object> params) {
        return this.mBehanceApiService.getUserById(params).compose(this.<Response<BehanceUser>>applySchedulers());
    }

    public Observable<Response<BehanceUser>> getUserByName(Map<String, Object> params) {
        return this.mBehanceApiService.getUserByUsername(params).compose(this.<Response<BehanceUser>>applySchedulers());
    }


}