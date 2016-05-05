package com.example.qkx.demomvp.data.source;

import com.example.qkx.demomvp.data.Welfare;
import com.example.qkx.demomvp.data.source.local.WelfareLocalDataSource;
import com.example.qkx.demomvp.data.source.remote.WelfareRemoteDataSource;

/**
 * Created by qkx on 16/4/28.
 */
public class WelfareRepository implements WelfareDataSource {

    private static volatile WelfareRepository INSTANCE;

    private WelfareLocalDataSource mLocalDataSource;

    private WelfareRemoteDataSource mRemoteDataSource;

    private WelfareRepository(WelfareLocalDataSource mLocalDataSource, WelfareRemoteDataSource mRemoteDataSource) {
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    public static WelfareRepository getInstance(WelfareLocalDataSource l, WelfareRemoteDataSource r) {
        if (null == INSTANCE) {
            synchronized (WelfareRepository.class) {
                if (null == INSTANCE) {
                    INSTANCE = new WelfareRepository(l, r);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void queryHome(final GetCallback callback) {
        // first load from local
        mLocalDataSource.queryHome(new GetCallback() {
            @Override
            public void onWelfareGet(Welfare welfare) {
                callback.onWelfareGet(welfare);
            }

            @Override
            public void onDataNotAvailable() {
                // if load from local failed
                // load form remote network and update recent 10 pics
                mRemoteDataSource.queryHome(new GetCallback() {
                    @Override
                    public void onWelfareGet(Welfare welfare) {
                        callback.onWelfareGet(welfare);
                        // if succeed, save to local
                        mLocalDataSource.updateWelfare(welfare);
                    }

                    @Override
                    public void onDataNotAvailable() {

                    }
                });
            }
        });
    }

    @Override
    public void queryPage(String page, final GetCallback callback) {
        mRemoteDataSource.queryPage(page, new GetCallback() {
            @Override
            public void onWelfareGet(Welfare welfare) {
                callback.onWelfareGet(welfare);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void queryRandom(final GetCallback callback) {
        mRemoteDataSource.queryRandom(new GetCallback() {
            @Override
            public void onWelfareGet(Welfare welfare) {
                callback.onWelfareGet(welfare);
                mLocalDataSource.updateWelfare(welfare);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
