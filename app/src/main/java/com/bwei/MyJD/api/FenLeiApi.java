package com.bwei.MyJD.api;

import android.database.Observable;

import com.bwei.MyJD.entity.FenLeiEntity;

import retrofit2.http.GET;

/**
 * Created by 房国伟 on 2018/9/8.
 */
public interface FenLeiApi {

    @GET("home/getHome")
    Observable<FenLeiEntity> fenlei();
}
