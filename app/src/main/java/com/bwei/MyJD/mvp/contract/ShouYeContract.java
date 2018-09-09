package com.bwei.MyJD.mvp.contract;


import com.bwei.MyJD.entity.ShouYeEntity;
import com.bwei.MyJD.mvp.model.ShouYeModel;
import com.bwei.base.base.mvp.BasePresenter;
import com.bwei.base.base.mvp.IBaseModel;
import com.bwei.base.base.mvp.IBaseView;

import io.reactivex.Observable;

/**
 * Created on 2018/9/6.
 * 契约类
 */
public interface ShouYeContract {

    //p
    abstract class ShouYePresenter extends BasePresenter<ShouYeModel,ShouyeView>{

        @Override
        public ShouYeModel getmModel() {
            return new com.bwei.MyJD.mvp.model.ShouYeModel();
        }
        public abstract void select();
    }

    //m
    interface ShouYeModel extends IBaseModel{
        Observable<ShouYeEntity> ShouYeQuary();
    }

    //v
    interface ShouyeView extends IBaseView{
        void success(ShouYeEntity shouYeEntity);
        void failure(String error);
    }

}
