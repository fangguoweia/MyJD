package com.bwei.MyJD.mvp.model;



import com.bwei.MyJD.api.SHOUYeApi;
import com.bwei.MyJD.common.Api;
import com.bwei.MyJD.entity.ShouYeEntity;
import com.bwei.MyJD.mvp.contract.ShouYeContract;
import com.bwei.base.net.RetrofitUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 房国伟 on 2018/9/6.
 */
public class ShouYeModel implements ShouYeContract.ShouYeModel{


    @Override
    public Observable<ShouYeEntity> ShouYeQuary() {
        return RetrofitUtils.getInstance().createApi(Api.SHOUYE,SHOUYeApi.class)
                .shouye().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }
}
