package com.tuyrt.httpdemo.http.api;

import com.tuyrt.httpdemo.http.config.HttpConfig;
import com.tuyrt.httpdemo.http.entity.BaseDataPointVo;
import com.tuyrt.httpdemo.http.entity.BaseEntity;
import com.tuyrt.httpdemo.http.entity.GrowthValueVo;
import com.tuyrt.httpdemo.http.entity.MedalVo;
import com.tuyrt.httpdemo.http.entity.QCloudResult;
import com.tuyrt.httpdemo.http.entity.RobotChildVo;
import com.tuyrt.httpdemo.http.entity.TokenVo;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by lytcom on 2017/5/7.
 */

public interface ApiService {

    @GET(HttpConfig.TOKEN)
    Call<BaseEntity<TokenVo>> getToken(@Query("pk") String pk, @Query("mac") String mac);

    @GET(HttpConfig.ROBOTINFO)
    Observable<BaseEntity<RobotChildVo>> getRobotChildInfo();

    @GET(HttpConfig.BASEDATAPOINT)
    Observable<BaseEntity<BaseDataPointVo>> getBaseDataPoint();

    @GET(HttpConfig.MEDAL)
    Observable<BaseEntity<ArrayList<MedalVo>>> getMedal();

    @GET(HttpConfig.GROWNVALUE)
    Observable<BaseEntity<GrowthValueVo>> getGrowValue(@QueryMap(encoded = true) Map<String, Integer> map);

    @DELETE(HttpConfig.DELETE_CONVENTION)
    Observable<BaseEntity<Void>> deleteConvention(@Query("id") String id);

    @Multipart
    @POST(HttpConfig.UPLOAD_ONE_FILE)
    Observable<BaseEntity<QCloudResult>> uploadOneFile(@Part MultipartBody.Part file);
}
