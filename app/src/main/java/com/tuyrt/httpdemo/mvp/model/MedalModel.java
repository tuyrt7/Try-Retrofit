package com.tuyrt.httpdemo.mvp.model;

import com.tuyrt.httpdemo.http.RxFunction;
import com.tuyrt.httpdemo.http.RxManager;
import com.tuyrt.httpdemo.http.RxSchedulers;
import com.tuyrt.httpdemo.http.entity.MedalVo;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by futao on 2017/11/2.
 */

public class MedalModel  {
    public Observable<ArrayList<MedalVo>> getMedalList() {
        return RxManager.getInstance().getApiService().getMedal().map(new RxFunction<ArrayList<MedalVo>>()).compose(RxSchedulers.<ArrayList<MedalVo>>io_main());
    }
}
