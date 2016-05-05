package com.example.qkx.demomvp.data.source;

import com.example.qkx.demomvp.data.Welfare;

import java.util.List;

/**
 * Created by qkx on 16/4/28.
 */
public interface WelfareDataSource {

    interface LoadCallback {

        void onWelfaresLoad(List<Welfare> welfares);

        void onDataNotAvailuble();
    }

    interface GetCallback {

        void onWelfareGet(Welfare welfare);

        void onDataNotAvailable();
    }

    void queryHome(GetCallback callback);

    void queryPage(String page, GetCallback callback);

    void queryRandom(GetCallback callback);
}
