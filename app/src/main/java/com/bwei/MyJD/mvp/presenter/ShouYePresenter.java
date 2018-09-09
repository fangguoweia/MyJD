package com.bwei.MyJD.mvp.presenter;

import com.bwei.MyJD.entity.ShouYeEntity;
import com.bwei.MyJD.mvp.contract.ShouYeContract;

import io.reactivex.functions.Consumer;

/**
 * Created by 房国伟 on 2018/9/6.
 */
public class ShouYePresenter extends ShouYeContract.ShouYePresenter{

    @Override
    public void select() {
        //subscribeOn被观察者 发射事件的线程 网络请求过程中，指的是被观察者的线程
        //observeOn观察者 接收事件的线程，得到响应的线程
        mModel.ShouYeQuary().subscribe(new Consumer<ShouYeEntity>() {
            @Override
            public void accept(ShouYeEntity shouYeEntity) throws Exception {
                mView.success(shouYeEntity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.failure("网络异常");
            }
        });

    }
}
