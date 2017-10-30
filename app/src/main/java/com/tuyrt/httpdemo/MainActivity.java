package com.tuyrt.httpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tuyrt.httpdemo.http.AppRestQueue;
import com.tuyrt.httpdemo.http.entity.BaseDataPointVo;
import com.tuyrt.httpdemo.http.entity.ResponseResult;
import com.tuyrt.httpdemo.http.entity.RobotChildVo;

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

public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();

    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.button3)
    Button mButton3;
    @BindView(R.id.text1)
    TextView mText1;
    private AppRestQueue mRestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRestQueue = AppRestQueue.getInstance();
    }

    @OnClick({R.id.button, R.id.button2, R.id.button3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Log.i(TAG, "onClick:button1 ");
                mRestQueue.getRobotChildInfo()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<RobotChildVo>() {
                            @Override
                            public void accept(@NonNull RobotChildVo robotChildVo) throws Exception {
                                Log.i(TAG, "subscribe:button1 ");
                                mText1.setText(robotChildVo.toString());
                            }
                        });
                break;
            case R.id.button2:
                mRestQueue.getApiRestService().getBaseDataPoint()
                        .map(new Function<ResponseResult<BaseDataPointVo>, BaseDataPointVo>() {
                            @Override
                            public BaseDataPointVo apply(@NonNull ResponseResult<BaseDataPointVo> baseDataPointVoResponseResult) throws Exception {
                                return baseDataPointVoResponseResult.getData();
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


                break;
            case R.id.button3:
                break;
        }
    }
}
