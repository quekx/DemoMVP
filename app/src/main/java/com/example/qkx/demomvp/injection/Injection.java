package com.example.qkx.demomvp.injection;

import android.content.Context;

import com.example.qkx.demomvp.data.source.WelfareRepository;
import com.example.qkx.demomvp.data.source.local.WelfareLocalDataSource;
import com.example.qkx.demomvp.data.source.remote.WelfareRemoteDataSource;

/**
 * Created by qkx on 16/4/28.
 */
public class Injection {
    public static WelfareRepository provideWelfareRepository(Context ctx) {
        return WelfareRepository.getInstance(WelfareLocalDataSource.getInstance(ctx),
                WelfareRemoteDataSource.getInstance());
    }
}
