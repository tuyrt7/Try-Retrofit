package com.tuyrt.httpdemo;

import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tuyrt.httpdemo.http.RestServiceUtils;
import com.tuyrt.httpdemo.http.RxFunction;
import com.tuyrt.httpdemo.http.RxManager;
import com.tuyrt.httpdemo.http.RxObserver;
import com.tuyrt.httpdemo.http.RxSchedulers;
import com.tuyrt.httpdemo.http.entity.BaseDataPointVo;
import com.tuyrt.httpdemo.http.entity.BaseEntity;
import com.tuyrt.httpdemo.http.entity.GrowthValueVo;
import com.tuyrt.httpdemo.http.entity.MedalVo;
import com.tuyrt.httpdemo.http.entity.QCloudResult;
import com.tuyrt.httpdemo.http.entity.RobotChildVo;
import com.tuyrt.httpdemo.mvp.presenter.MedalPresenter;
import com.tuyrt.httpdemo.mvp.view.MedalView;
import com.tuyrt.httpdemo.util.LocationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class MainActivity extends AppCompatActivity implements MedalView {
    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.button3)
    Button mButton3;
    @BindView(R.id.button4)
    Button mButton4;
    @BindView(R.id.button5)
    Button mButton5;
    @BindView(R.id.button6)
    Button mButton6;
    @BindView(R.id.text1)
    TextView mText1;

    private String TAG;
    private RxManager mRxManager;
    private MedalPresenter mMedalPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TAG = getPackageName() + "." + getClass().getSimpleName();
        mRxManager = RxManager.getInstance();

        //测试地区
        //getLocationInfo();
        mMedalPresenter = new MedalPresenter(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxManager.getInstance().clear(TAG);
    }

    void getLocationInfo() {
        new Thread() {
            @Override
            public void run() {
                Location location = LocationUtils.getLocation(MainActivity.this);
                Log.i(TAG, "run: location=" + location.getLatitude() + "=" + location.getLongitude());
                List<Address> address = LocationUtils.getAddress(MainActivity.this, location);
                if (address == null || address.size() == 0) {
                    Log.i(TAG, "run: error------------");
                    return;
                }
                String s = address.get(0).toString();
                Log.i(TAG, "run: address=" + s);

            }
        }.start();

    }

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Log.i(TAG, "onClick:button1 ");
                getChildRobotInfo();
                break;
            case R.id.button2:
                getBaseDataPoint();
                break;
            case R.id.button3:
                mMedalPresenter.getMedalList(this, TAG, 0, false);
                break;
            case R.id.button4:
                Map<String, Integer> params = new HashMap<>();
                params.put("type", 2);
                params.put("year", 2015);
                params.put("month", -1);
                params.put("day", -1);
                getGrowthValue(params);
                break;
            case R.id.button5:
                mRxManager.getApiService().deleteConvention("45ec78f0-7f5e-497c-a2bd-8fae003a30f3")
                        .compose(RxSchedulers.<BaseEntity>io_main())
                        .subscribe(new Consumer<BaseEntity>() {
                            @Override
                            public void accept(@NonNull BaseEntity baseEntity) throws Exception {
                                if (baseEntity.getCode() == 204) {
                                    Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                break;
            case R.id.button6:
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/Screenshots/test1.jpg";
                MultipartBody.Part uploadFile = RestServiceUtils.buildUploadFilePart("uploadFile", path);
                mRxManager.getApiService().uploadOneFile(uploadFile)
                        .map(new RxFunction<QCloudResult>())
                        .compose(RxSchedulers.<QCloudResult>io_main())
                        .subscribe(new RxObserver<QCloudResult>(this, TAG, 2, true) {
                            @Override
                            public void onSuccess(int whichRequest, QCloudResult qCloudResult) {
                                Log.i(TAG, "onSuccess: " + qCloudResult.toString());
                                mText1.setText(qCloudResult.toString());
                            }

                            @Override
                            public void onError(int whichRequest, Throwable e) {
                                Log.i(TAG, "onError: " + whichRequest + "======error=" + e.toString());
                            }
                        });
                break;
        }
    }


    @Override
    public void loadDateSuccess(ArrayList<MedalVo> medalVoList) {
        Log.i(TAG, "loadDateSuccess: " + medalVoList.toString());
        mText1.setText(medalVoList.toString());
    }

    @Override
    public void onError(int whichRequest, Throwable t) {
        Log.i(TAG, "onError: Throwable=" + t.toString());
    }

    @Override
    public void onStartLoading(int whichRequest) {
        Log.i(TAG, "onStartLoading: Main");
    }

    @Override
    public void onEndLoading(int whichRequest) {
        Log.i(TAG, "onEndLoading: Main");
    }

    private void getBaseDataPoint() {
        mRxManager.getApiService().getBaseDataPoint()
                .map(new Function<BaseEntity<BaseDataPointVo>, BaseDataPointVo>() {
                    @Override
                    public BaseDataPointVo apply(@NonNull BaseEntity<BaseDataPointVo> baseDataPointVoBaseEntity) throws Exception {
                        return baseDataPointVoBaseEntity.getData();
                    }

                    //sad
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseDataPointVo>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BaseDataPointVo baseDataPointVo) {
                        Log.i(TAG, "subscribe:button2 =" + baseDataPointVo.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getChildRobotInfo() {
        mRxManager.getApiService().getRobotChildInfo()
                .map(new RxFunction<RobotChildVo>())
                .compose(RxSchedulers.<RobotChildVo>io_main())
                .subscribeWith(new RxObserver<RobotChildVo>(this, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, RobotChildVo robotChildVo) {
                        Log.i(TAG, "subscribe:button1 ");
                        mText1.setText(robotChildVo.toString());
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }

    public void getGrowthValue(Map<String, Integer> map) {
        mRxManager.getApiService().getGrowValue(map)
                .map(new RxFunction<GrowthValueVo>())
                .compose(RxSchedulers.<GrowthValueVo>io_main())
                .subscribeWith(new RxObserver<GrowthValueVo>(this, TAG, 0, true) {

                    @Override
                    public void onSuccess(int whichRequest, GrowthValueVo growthValueVo) {
                        Log.i(TAG, "onSuccess: GrowthValue ----- : " + growthValueVo.toString());
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {
                        Log.i(TAG, "onError: GrowthValue ----- : " + e.toString());
                    }
                });
    }
}
