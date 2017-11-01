package com.tuyrt.httpdemo.http.service;

import com.tuyrt.httpdemo.http.config.HttpConfig;
import com.tuyrt.httpdemo.http.entity.BaseDataPointVo;
import com.tuyrt.httpdemo.http.entity.BaseEntity;
import com.tuyrt.httpdemo.http.entity.RobotChildVo;
import com.tuyrt.httpdemo.http.entity.TokenVo;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lytcom on 2017/5/7.
 */

public interface ApiRestService {

    @GET(HttpConfig.GET_TOKEN)
    Call<BaseEntity<TokenVo>> getToken(@Query("pk") String pk, @Query("mac") String mac);

    @GET(HttpConfig.GET_ROBOT_INFO)
    Observable<BaseEntity<RobotChildVo>> getRobotChildInfo();

    @GET(HttpConfig.GET_BASE_DATA_POINT)
    Observable<BaseEntity<BaseDataPointVo>> getBaseDataPoint();
}
