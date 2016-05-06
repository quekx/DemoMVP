package com.example.qkx.demomvp.welfaredetail;

import com.example.qkx.demomvp.BasePresenter;
import com.example.qkx.demomvp.BaseView;
import com.example.qkx.demomvp.data.Welfare;

/**
 * Created by qkx on 16/4/28.
 */
public interface WelfareDetailContact {

    interface View extends BaseView<Presenter> {

        void showPic(Welfare data);
    }

    interface Presenter extends BasePresenter {

        void displayHome();

        void refreshRandom();
    }
}
