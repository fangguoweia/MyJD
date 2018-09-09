package com.bwei.MyJD.adapter;

import android.content.Context;
import android.view.View;

import com.bwei.MyJD.R;
import com.bwei.MyJD.entity.ShouYeEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 房国伟 on 2018/9/6.
 */
public class ShouYeXreAdapter extends BaseQuickAdapter<ShouYeEntity.DataBean.TuijianBean.ListBeanX,BaseViewHolder> {

    private Context context;
    private List<ShouYeEntity.DataBean.TuijianBean.ListBeanX> list;

    public ShouYeXreAdapter(int layoutResId, List<ShouYeEntity.DataBean.TuijianBean.ListBeanX> list) {
        super(layoutResId,list);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShouYeEntity.DataBean.TuijianBean.ListBeanX item) {
        helper.setText(R.id.item_title,item.getTitle());
        helper.setText(R.id.item_price,"$"+item.getBargainPrice());
        String[] split = item.getImages().split("\\|");
        SimpleDraweeView view = helper.getView(R.id.item_sdv);
        view.setImageURI(split[0]);

    }
}
