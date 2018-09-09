package com.bwei.MyJD.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.MyJD.R;
import com.bwei.MyJD.entity.ShouYeEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 房国伟 on 2018/9/6.
 */
public class SDFSD extends BaseQuickAdapter<String,BaseViewHolder> {
    public SDFSD(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.sdf,item);
    }





}
