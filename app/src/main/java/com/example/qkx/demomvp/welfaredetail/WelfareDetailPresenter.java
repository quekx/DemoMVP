package com.example.qkx.demomvp.welfaredetail;

import com.example.qkx.demomvp.data.Welfare;
import com.example.qkx.demomvp.data.source.WelfareDataSource;
import com.example.qkx.demomvp.data.source.WelfareRepository;

/**
 * Created by qkx on 16/4/28.
 */
public class WelfareDetailPresenter implements WelfareDetailContact.Presenter {

    private WelfareRepository mWelfareRepository;

    private WelfareDetailContact.View mView;

    public WelfareDetailPresenter(WelfareRepository welfareRepository, WelfareDetailContact.View view) {
        this.mWelfareRepository = welfareRepository;
        this.mView = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        getHome();
    }

    @Override
    public void getRandom() {
        mWelfareRepository.queryRandom(new WelfareDataSource.GetCallback() {
            @Override
            public void onWelfareGet(Welfare welfare) {
                mView.showPic(welfare);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void getHome() {
        mWelfareRepository.queryHome(new WelfareDataSource.GetCallback() {
            @Override
            public void onWelfareGet(Welfare welfare) {
                mView.showPic(welfare);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
