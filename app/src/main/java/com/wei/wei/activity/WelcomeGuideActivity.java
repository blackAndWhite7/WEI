package com.wei.wei.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wei.wei.R;
import com.wei.wei.adapter.FragmentAdapter;
import com.wei.wei.fragment.FirstGuideFragment;
import com.wei.wei.fragment.SecondGuideFragment;
import com.wei.wei.fragment.ThirdGuideFragment;
import com.wei.wei.util.SharedPreferencesUtil;
import com.wei.wei.view.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by wei on 2018/2/1/00.
 */
public class WelcomeGuideActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.loginAndRegister)
    LinearLayout loginAndRegister;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.enter)
    Button enter;
    @BindView(R.id.indicator)
    CircleIndicator indicator;

    FragmentAdapter mAdapter;
    private List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_guide);
        ButterKnife.bind(this);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        enter.setOnClickListener(this);

        fragments.add(new FirstGuideFragment());
        fragments.add(new SecondGuideFragment());
        fragments.add(new ThirdGuideFragment());
        mAdapter = new FragmentAdapter(getSupportFragmentManager(),fragments);


        mPager.setAdapter(mAdapter);
        indicator.setViewPager(mPager);
        mPager.setPageTransformer(true, new MyPageTransform());

        initListener();
    }

    private void initListener() {

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == fragments.size()-1){
//                    loginAndRegister.setVisibility(View.VISIBLE);
                    enter.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login){
            Toast.makeText(this,"登录",Toast.LENGTH_SHORT).show();
        }else if(view.getId() == R.id.register){
            Toast.makeText(this,"注册",Toast.LENGTH_SHORT).show();
        }else if(view.getId() == R.id.enter){
            startActivity(new Intent(WelcomeGuideActivity.this, WelcomeActivity.class));
            SharedPreferencesUtil.putBoolean(WelcomeGuideActivity.this, SharedPreferencesUtil.FIRST_OPEN, false);
            finish();
        }
    }

    class MyPageTransform implements ViewPager.PageTransformer {

        final float SCALE_MAX = 0.8f;
        final float ALPHA_MAX = 0.5f;

        @Override
        public void transformPage(View page, float position) {
            float scale = (position < 0)
                    ? ((1 - SCALE_MAX) * position + 1)
                    : ((SCALE_MAX - 1) * position + 1);
            float alpha = (position < 0)
                    ? ((1 - ALPHA_MAX) * position + 1)
                    : ((ALPHA_MAX - 1) * position + 1);
            //为了滑动过程中，page间距不变，这里做了处理
            if(position < 0) {
                ViewCompat.setPivotX(page, page.getWidth());
                ViewCompat.setPivotY(page, page.getHeight() / 2);
            } else {
                ViewCompat.setPivotX(page, 0);
                ViewCompat.setPivotY(page, page.getHeight() / 2);
            }
            ViewCompat.setScaleX(page, scale);
            ViewCompat.setScaleY(page, scale);
            ViewCompat.setAlpha(page, Math.abs(alpha));
        }
    }

}
