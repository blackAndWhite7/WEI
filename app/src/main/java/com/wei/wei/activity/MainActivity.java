package com.wei.wei.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wei.wei.R;
import com.wei.wei.adapter.FragmentAdapter;
import com.wei.wei.classifyhelper.util.StatusBarUtil;
import com.wei.wei.fragment.OneFragment;
import com.wei.wei.fragment.ThreeFragment;
import com.wei.wei.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.leibnik.wechatradiobar.WeChatRadioGroup;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.radiogroup)
    WeChatRadioGroup gradualRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setStatusBarTranslucent(this, false);
        ButterKnife.bind(this);
        initFragment();

    }

    private void initFragment() {
        List<Fragment> list = new ArrayList<>();
        OneFragment oneFragment = OneFragment.newInstance("首页");
        TwoFragment twoFragment = TwoFragment.newInstance("发现");
        ThreeFragment threeFragment = ThreeFragment.newInstance("我");
        list.add(oneFragment);
        list.add(twoFragment);
        list.add(threeFragment);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), list));
        gradualRadioGroup.setViewPager(viewPager);
    }


}
