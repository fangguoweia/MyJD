package com.bwei.MyJD.activity;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.bwei.MyJD.R;
import com.bwei.MyJD.fragment.IndexFragment;
import com.bwei.MyJD.fragment.ClassifyFragment;
import com.bwei.MyJD.fragment.ShoppingFragment;
import com.bwei.MyJD.fragment.MyFragment;
import com.bwei.base.base.BaseActivity;
import com.gyf.barlibrary.ImmersionBar;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frame_layout)
    FrameLayout framelayout;
    @BindView(R.id.radio_group)
    RadioGroup radio_group;

    private IndexFragment f1;
    private ClassifyFragment f2;
    private ShoppingFragment f3;
    private MyFragment f4;
    private FragmentManager fragmentManager;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        //初始化沉浸式
        ImmersionBar.with(this).init();

        f1 = new IndexFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout,f1).commit();

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                if (f1!=null){transaction.hide(f1);}
                if (f2!=null){transaction.hide(f2);}
                if (f3!=null){transaction.hide(f3);}
                if (f4!=null){transaction.hide(f4);}
                
                switch (checkedId){
                    case R.id.radio01:
                        if (f1==null){
                            f1 = new IndexFragment();
                            transaction.add(R.id.frame_layout,f1);
                        }else {
                            transaction.show(f1);
                        }
                        break;
                    case R.id.radio02:
                        if (f2==null){
                            f2 = new ClassifyFragment();
                            transaction.add(R.id.frame_layout, f2);
                        }else {
                            transaction.show(f2);
                        }
                        break;
                    case R.id.radio03:
                        if (f3==null){
                            f3 = new ShoppingFragment();
                            transaction.add(R.id.frame_layout, f3);
                        }else {
                            transaction.show(f3);
                        }
                        break;
                    case R.id.radio04:
                        if (f4==null){
                            f4 = new MyFragment();
                            transaction.add(R.id.frame_layout, f4);
                        }else {
                            transaction.show(f4);
                        }
                        break;
                }
                transaction.commit();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){
            unbinder.unbind();//释放资源，回收内存，优化性能
            unbinder=null;
        }
    }
}
