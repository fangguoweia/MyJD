package com.bwei.MyJD.fragment;


import android.content.Context;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.MyJD.R;
import com.bwei.MyJD.adapter.FenLeiAdapter;
import com.bwei.MyJD.adapter.ShouYeXreAdapter;
import com.bwei.MyJD.entity.ShouYeEntity;
import com.bwei.MyJD.mvp.contract.ShouYeContract;
import com.bwei.MyJD.mvp.presenter.ShouYePresenter;
import com.bwei.base.base.mvp.BaseMvpFragment;
import com.bwei.base.base.mvp.BasePresenter;
import com.donkingliang.banner.CustomBanner;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class IndexFragment extends BaseMvpFragment<ShouYeContract.ShouYeModel,ShouYeContract.ShouYePresenter> implements ShouYeContract.ShouyeView {

    private ArrayList<String> images= new ArrayList<>();//轮播图片集合

    @BindView(R.id.recy_view)
    RecyclerView recy_view;//推荐类
    @BindView(R.id.smart_fresh)
    SmartRefreshLayout smart_fresh;
    /*@BindView(R.id.index_banner)
    Banner index_banner;*/
    private CustomBanner banner;
    private ShouYeXreAdapter shouYeXreAdapter;
    //private RecyclerView one_fenlei_recyclerview;//九宫格分类


    //布局
    @Override
    protected int bindLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        super.initData();
        GetData();
        setSmart();
    }

    private void GetData() {
        presenter.select();
    }

    private void setSmart() {
        //设置 Header 为 贝塞尔雷达 样式
        smart_fresh.setRefreshHeader(new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
        smart_fresh.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
        smart_fresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                GetData();
               smart_fresh.finishRefresh(2000);

            }
        });
        smart_fresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                GetData();
                smart_fresh.finishLoadMore(2000);
            }
        });

    }

    @Override
    public BasePresenter initPresenter() {
        return new ShouYePresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    //请求失败的方法
    @Override
    public void fail(String msg) {

    }

    //请求成功的方法
    @Override
    public void success(ShouYeEntity shouYeEntity) {
        //获取数据
        List<ShouYeEntity.DataBean.TuijianBean.ListBeanX> list = shouYeEntity.getData().getTuijian().getList();
        /*
        * 推荐类
        * */
        recy_view.setLayoutManager(new GridLayoutManager(getActivity(),2));
        shouYeXreAdapter = new ShouYeXreAdapter(R.layout.shouye_item, list);
        recy_view.setAdapter(shouYeXreAdapter);

        /**
         * == 轮播图 ==
         */
        View headview = getLayoutInflater().inflate(R.layout.banner_item, (ViewGroup) recy_view.getParent(), false);
        banner = headview.findViewById(R.id.banner);
        shouYeXreAdapter.addHeaderView(headview);
        for (int i = 0; i < shouYeEntity.getData().getBanner().size(); i++) {
            String icon = shouYeEntity.getData().getBanner().get(i).getIcon();
            images.add(icon);
        }
        setBean(images);
        /**
         * == 分类 ==
         */
        View fenleiview = getLayoutInflater().inflate(R.layout.fenlei_layout, (ViewGroup) recy_view.getParent(), false);
        RecyclerView one_fenlei_recyclerview = fenleiview.findViewById(R.id.felei_layout_view);
        shouYeXreAdapter.addHeaderView(fenleiview);

        one_fenlei_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2, LinearLayoutManager.HORIZONTAL,false));
        FenLeiAdapter fenLeiAdapter = new FenLeiAdapter(R.layout.fenlei_item, shouYeEntity.getData().getFenlei());
        one_fenlei_recyclerview.setAdapter(fenLeiAdapter);
    }

    private void setBean(final ArrayList<String> beans){
        banner.setPages(new CustomBanner.ViewCreator<String>() {
            @Override
            public View createView(Context context, int i) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int position, String s) {
                Glide.with(context).load(s).into((ImageView) view);
            }

        },beans).startTurning(2000);
    }

    @Override
    public void failure(String error) {
    }
}
