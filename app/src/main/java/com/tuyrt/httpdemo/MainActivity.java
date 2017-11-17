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

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.tuyrt.httpdemo.http.RestServiceUtils;
import com.tuyrt.httpdemo.http.RxFunction;
import com.tuyrt.httpdemo.http.RxManager;
import com.tuyrt.httpdemo.http.RxObserver;
import com.tuyrt.httpdemo.http.RxSchedulers;
import com.tuyrt.httpdemo.http.config.HttpConfig;
import com.tuyrt.httpdemo.http.entity.BaseDataPointVo;
import com.tuyrt.httpdemo.http.entity.BaseEntity;
import com.tuyrt.httpdemo.http.entity.GrowthValueVo;
import com.tuyrt.httpdemo.http.entity.MedalVo;
import com.tuyrt.httpdemo.http.entity.QCloudResult;
import com.tuyrt.httpdemo.http.entity.RobotChildVo;
import com.tuyrt.httpdemo.http.progress.ProgressListener;
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
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;
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
    @BindView(R.id.number_progress_bar)
    NumberProgressBar mNumberProgressBar;

    private String TAG;
    private RxManager mRxManager;
    private MedalPresenter mMedalPresenter;
    private ProgressInfo mLastUploadingingInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initListener();
        TAG = getPackageName() + "." + getClass().getSimpleName();

        mRxManager = RxManager.getInstance();

        //测试地区
        //getLocationInfo();
        mMedalPresenter = new MedalPresenter(this);
    }

    private void initListener() {
        mNumberProgressBar.setMax(100);
        mNumberProgressBar.setProgress(0);

        // Okhttp/Retofit 下载监听
        ProgressManager.getInstance().addRequestListener(
                HttpConfig.BASE_URL + HttpConfig.UPLOAD_ONE_FILE, getUploadProgressListener());
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

    String path = Environment.getExternalStorageDirectory().getAbsolutePath() +
           // "/Pictures/Screenshots/test1.jpg";
    "/Movies/Screenrecords/S70421.mp4";

    @OnClick({
            R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6,
            R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11, R.id.button12
    })
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

                MultipartBody.Part uploadFile = RestServiceUtils.buildUploadFilePart("uploadFile", path);
                mRxManager.getApiService().uploadOneFile(uploadFile)
                        .map(new RxFunction<QCloudResult>())
                        .compose(RxSchedulers.<QCloudResult>io_main())
                        .subscribe(new RxObserver<QCloudResult>(this, TAG, 2, false) {
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

            case R.id.button7:

                upload();
                break;
            case R.id.button8:
                break;
            case R.id.button9:
                break;
            case R.id.button10:
                break;
            case R.id.button11:
                break;
            case R.id.button12:
                break;
        }
    }

    private me.jessyan.progressmanager.ProgressListener getUploadProgressListener() {
        return new me.jessyan.progressmanager.ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                // 如果你不屏蔽用户重复点击上传或下载按钮,就可能存在同一个 Url 地址,上一次的上传或下载操作都还没结束,
                // 又开始了新的上传或下载操作,那现在就需要用到 id(请求开始时的时间) 来区分正在执行的进度信息
                // 这里我就取最新的上传进度用来展示,顺便展示下 id 的用法

                if (mLastUploadingingInfo == null) {
                    mLastUploadingingInfo = progressInfo;
                }

                //因为是以请求开始时的时间作为 Id ,所以值越大,说明该请求越新
                if (progressInfo.getId() < mLastUploadingingInfo.getId()) {
                    return;
                } else if (progressInfo.getId() > mLastUploadingingInfo.getId()) {
                    mLastUploadingingInfo = progressInfo;
                }

                int progress = mLastUploadingingInfo.getPercent();
                mNumberProgressBar.setProgress(progress);
                //mUploadProgressText.setText(progress + "%");
                Log.d(TAG, "--Upload-- " + progress + " %  " + mLastUploadingingInfo.getSpeed() + " byte/s  " + mLastUploadingingInfo.toString());
                if (mLastUploadingingInfo.isFinish()) {
                    //说明已经上传完成
                    Log.d(TAG, "--Upload-- finish");
                }
            }

            @Override
            public void onError(long id, Exception e) {

            }
        };
    }


    private void upload() {


        RxManager.getInstance().uploadFile(path, new ProgressListener() {
            @Override
            public void onProgress(long currentBytes, long contentLength, boolean done) {
                Log.i(TAG, "currentBytes==" + currentBytes + "==contentLength==" + contentLength + "==done==" + done);
                int progress = (int) (currentBytes * 100 / contentLength);
                mNumberProgressBar.setProgress(progress);
            }
        })
                .map(new RxFunction<QCloudResult>())
                .compose(RxSchedulers.<QCloudResult>io_main())
                .subscribe(new RxObserver<QCloudResult>(this, TAG, 0, false) {
                    @Override
                    public void onSuccess(int whichRequest, QCloudResult qCloudResult) {
                        Log.i(TAG, "onSuccess: " + qCloudResult.toString());
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {
                        Log.i(TAG, "uploadFile onError:");
                    }
                });
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
