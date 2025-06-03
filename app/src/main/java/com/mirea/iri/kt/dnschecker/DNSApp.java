package com.mirea.iri.kt.dnschecker;

import android.app.Application;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DNSApp extends Application {
    public static final String LOG_TAG = "DNSApp";
    private static Auth auth;
    private static Domains domains;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(LOG_TAG, "DNSChecker created!");

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit authRetrofit = new Retrofit.Builder()
                .baseUrl("https://android-for-students.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        auth = authRetrofit.create(Auth.class);

        Retrofit domainsRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.domainsdb.info")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        domains = domainsRetrofit.create(Domains.class);
    }

    public static Auth getAuthService() {
        return auth;
    }

    public static Domains getDomainsService() {
        return domains;
    }
}