package com.bwei.MyJD.api;


import com.bwei.MyJD.entity.ShouYeEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 房国伟 on 2018/9/6.
 */
public interface SHOUYeApi {

    @GET("home/getHome")
    Observable<ShouYeEntity> shouye();

}
