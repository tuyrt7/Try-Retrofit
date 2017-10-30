package com.tuyrt.httpdemo.http.Api;

import com.tuyrt.httpdemo.http.entity.BaseDataPointVo;
import com.tuyrt.httpdemo.http.entity.ResponseResult;
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

    @GET("body/getAccessToken")
    Call<ResponseResult<TokenVo>> getToken(@Query("pk") String pk, @Query("mac") String mac);

    @GET("body/getRobotChild")
    Observable<ResponseResult<RobotChildVo>> getRobotChildInfo();

    @GET("body/baseDataPoint")
    Observable<ResponseResult<BaseDataPointVo>> getBaseDataPoint();
}
