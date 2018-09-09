package com.bwei.MyJD.adapter;

import android.support.annotation.Nullable;

import com.bwei.MyJD.R;
import com.bwei.MyJD.entity.ShouYeEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 房国伟 on 2018/9/8.
 */
public class FenLeiAdapter extends BaseQuickAdapter<ShouYeEntity.DataBean.FenleiBean,BaseViewHolder> {
    public FenLeiAdapter(int layoutResId, @Nullable List<ShouYeEntity.DataBean.FenleiBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShouYeEntity.DataBean.FenleiBean item) {
        helper.setText(R.id.fenlei_item_text,item.getName());
        SimpleDraweeView fenlei_item_sdv = helper.getView(R.id.fenlei_item_sdv);
        fenlei_item_sdv.setImageURI(item.getIcon());
    }
}
