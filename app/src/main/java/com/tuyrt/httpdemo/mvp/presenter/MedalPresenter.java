package com.tuyrt.httpdemo.mvp.presenter;

import android.content.Context;

import com.tuyrt.httpdemo.http.RxObserver;
import com.tuyrt.httpdemo.http.entity.MedalVo;
import com.tuyrt.httpdemo.mvp.model.MedalModel;
import com.tuyrt.httpdemo.mvp.view.MedalView;

import java.util.ArrayList;

/**
 * Created by futao on 2017/1/2.
 */

public class MedalPresenter {

    private MedalModel model;
    private MedalView view;
    public MedalPresenter(MedalView view) {
        this.view = view;
        this.model = new MedalModel();
    }

    public void getMedalList(Context context, String key, int whichRequest, boolean isShowDialog) {
         model.getMedalList().subscribe(new RxObserver<ArrayList<MedalVo>>(context, key, whichRequest, isShowDialog) {
            @Override
            public void onStart(int whichRequest) {
                super.onStart(whichRequest);
                view.onStartLoading(whichRequest);
            }

            @Override
            public void onSuccess(int whichRequest, ArrayList<MedalVo> medalVos) {
                view.loadDateSuccess(medalVos);
                view.onEndLoading(whichRequest);
            }

            @Override
            public void onError(int whichRequest, Throwable e) {
                view.onEndLoading(whichRequest);
                view.onError(whichRequest,e);
            }
        });
    }
}
