package com.wei.wei.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.wei.wei.R;
import com.wei.wei.galleryhelper.BlurBitmapUtils;
import com.wei.wei.galleryhelper.CardAdapter;
import com.wei.wei.galleryhelper.CardScaleHelper;
import com.wei.wei.galleryhelper.ViewSwitchUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.blurView)
    ImageView mBlurView;

    private List<Integer> mList = new ArrayList<>();
    private CardScaleHelper mCardScaleHelper = null;
    private Runnable mBlurRunnable;
    private int mLastPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        for (int i = 1; i <= 6; i++) {
            try {
                Field field = R.drawable.class.getDeclaredField("bea" + i);
                mList.add(Integer.parseInt(field.get(null).toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        mList.add(R.drawable.bea1);
//        mList.add(R.drawable.pic2);
//        mList.add(R.drawable.pic3);
//        mList.add(R.drawable.pic4);
//        mList.add(R.drawable.pic5);
//        mList.add(R.drawable.pic6);
//        mList.add(R.drawable.pic7);
//        mList.add(R.drawable.pic8);
//        mList.add(R.drawable.pic9);
//        mList.add(R.drawable.pic10);
//        mList.add(R.drawable.pic11);
//        mList.add(R.drawable.pic12);
//        mList.add(R.drawable.pic13);
//        mList.add(R.drawable.pic14);
//        mList.add(R.drawable.pic15);
//        mList.add(R.drawable.pic16);
//        mList.add(R.drawable.pic17);
//        mList.add(R.drawable.pic18);
//        mList.add(R.drawable.pic19);
//        mList.add(R.drawable.pic20);
//        mList.add(R.drawable.pic21);


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new CardAdapter(mList));
        // mRecyclerView绑定scale效果
        mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.setCurrentItemPos(0);
        mCardScaleHelper.attachToRecyclerView(mRecyclerView);

        initBlurBackground();
    }

    private void initBlurBackground() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    notifyBackgroundChange();
                }
            }
        });

        notifyBackgroundChange();
    }

    private void notifyBackgroundChange() {
        if (mLastPos == mCardScaleHelper.getCurrentItemPos()) return;
        mLastPos = mCardScaleHelper.getCurrentItemPos();
        final int resId = mList.get(mCardScaleHelper.getCurrentItemPos());
        mBlurView.removeCallbacks(mBlurRunnable);
        mBlurRunnable = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
                ViewSwitchUtils.startSwitchBackgroundAnim(mBlurView, BlurBitmapUtils.getBlurBitmap(mBlurView.getContext(), bitmap, 15));
            }
        };
        mBlurView.postDelayed(mBlurRunnable, 500);
    }
}
