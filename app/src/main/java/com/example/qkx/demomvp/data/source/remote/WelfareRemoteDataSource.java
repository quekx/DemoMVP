package com.example.qkx.demomvp.data.source.remote;

import com.example.qkx.demomvp.Constants;
import com.example.qkx.demomvp.data.Welfare;
import com.example.qkx.demomvp.data.source.WelfareDataSource;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qkx on 16/4/28.
 */
public class WelfareRemoteDataSource implements WelfareDataSource{

    private static volatile WelfareRemoteDataSource INSTANCE;

    private final MyHttpService mHttpService;

    private WelfareRemoteDataSource() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mHttpService = retrofit.create(MyHttpService.class);
    }

    public static WelfareRemoteDataSource getInstance() {
        if (null == INSTANCE) {
            synchronized (WelfareRemoteDataSource.class) {
                if (null == INSTANCE) {
                    INSTANCE = new WelfareRemoteDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void queryPage(String page, final GetCallback callback) {
        Call<Welfare> call = mHttpService.queryPage(page);

        call.enqueue(new Callback<Welfare>() {
            @Override
            public void onResponse(Call<Welfare> call, Response<Welfare> response) {
                Welfare welfare = response.body();
                if (null != welfare) {
                    callback.onWelfareGet(welfare);
                }
            }

            @Override
            public void onFailure(Call<Welfare> call, Throwable t) {

            }
        });
    }

    @Override
    public void queryHome(GetCallback callback) {
        queryPage("" + 1, callback);
    }

    @Override
    public void queryRandom(final GetCallback callback) {
        Call<Welfare> call = mHttpService.queryRandom();

        call.enqueue(new Callback<Welfare>() {
            @Override
            public void onResponse(Call<Welfare> call, Response<Welfare> response) {
                Welfare welfare = response.body();
                if (null != welfare) {
                    callback.onWelfareGet(welfare);
                }
            }

            @Override
            public void onFailure(Call<Welfare> call, Throwable t) {

            }
        });
    }
}
