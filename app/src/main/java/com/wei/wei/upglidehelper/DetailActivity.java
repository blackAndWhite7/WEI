package com.wei.wei.upglidehelper;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wei.wei.R;
import com.wei.wei.activity.Video2Activity;
import com.wei.wei.activity.VideoActivity;

/**
 * Created by xmuSistone on 2016/9/19.
 */
public class DetailActivity extends FragmentActivity {

    protected static final float FLIP_DISTANCE = 400;

    public static final String EXTRA_IMAGE_URL = "detailImageUrl";

    public static final String IMAGE_TRANSITION_NAME = "transitionImage";
    public static final String ADDRESS1_TRANSITION_NAME = "address1";
    public static final String ADDRESS2_TRANSITION_NAME = "address2";
    public static final String ADDRESS3_TRANSITION_NAME = "address3";
    public static final String ADDRESS4_TRANSITION_NAME = "address4";
    public static final String ADDRESS5_TRANSITION_NAME = "address5";
    public static final String RATINGBAR_TRANSITION_NAME = "ratingBar";

    public static final String HEAD1_TRANSITION_NAME = "head1";
    public static final String HEAD2_TRANSITION_NAME = "head2";
    public static final String HEAD3_TRANSITION_NAME = "head3";
    public static final String HEAD4_TRANSITION_NAME = "head4";

    private TextView address1, address2, address3, address4, address5;
    private ImageView imageView;
    private RatingBar ratingBar;

    private LinearLayout listContainer;
    private static final String[] headStrs = {HEAD1_TRANSITION_NAME, HEAD2_TRANSITION_NAME, HEAD3_TRANSITION_NAME, HEAD4_TRANSITION_NAME};
    private static final int[] imageIds = {R.drawable.head1, R.drawable.head2, R.drawable.head3, R.drawable.head4};
    private GestureDetector mDetector;
    private int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = (ImageView) findViewById(R.id.image);
        address1 = findViewById(R.id.address1);
//        address2 = findViewById(R.id.address2);
        address3 = findViewById(R.id.address3);
        address4 = findViewById(R.id.address4);
        address5 = findViewById(R.id.address5);
        ratingBar = (RatingBar) findViewById(R.id.rating);
        listContainer = (LinearLayout) findViewById(R.id.detail_list_container);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        String imageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        ImageLoader.getInstance().displayImage(imageUrl, imageView);
        String sID=getIntent().getStringExtra("position");
        id = Integer.parseInt(sID);

        ViewCompat.setTransitionName(imageView, IMAGE_TRANSITION_NAME);
        ViewCompat.setTransitionName(address1, ADDRESS1_TRANSITION_NAME);
//        ViewCompat.setTransitionName(address2, ADDRESS2_TRANSITION_NAME);
        ViewCompat.setTransitionName(address3, ADDRESS3_TRANSITION_NAME);
        ViewCompat.setTransitionName(address4, ADDRESS4_TRANSITION_NAME);
        ViewCompat.setTransitionName(address5, ADDRESS5_TRANSITION_NAME);
        ViewCompat.setTransitionName(ratingBar, RATINGBAR_TRANSITION_NAME);

        initView();

        dealListView();

        initListener();

    }

    private void initView() {
        switch (id){
            case 0:
                address1.setText("林允");
                address3.setText("17");
                address4.setText("韩国少女时代组合成员");
                address5.setText("排名1");
                ratingBar.setRating(5);
                break;
            case 1:
                address1.setText("舒畅");
                address3.setText("18");
                address4.setText("魔幻手机傻妞");
                address5.setText("排名2");
                ratingBar.setRating((float) 4.5);
                break;
            case 2:
                address1.setText("刘诗诗");
                address3.setText("19");
                address4.setText("仙剑奇侠传演员");
                address5.setText("排名3");
                ratingBar.setRating(4);
                break;
            case 3:
                address1.setText("景甜");
                address3.setText("20");
                address4.setText("人美歌甜超可爱");
                address5.setText("排名4");
                ratingBar.setRating((float) 3.5);
                break;
            case 4:
                address1.setText("江疏影");
                address3.setText("21");
                address4.setText("花样姐姐美美哒");
                address5.setText("排名5");
                ratingBar.setRating(3);
                break;
        }
    }

    private void initListener() {
        mDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (e1 != null && e2 != null) {
                    if (e2.getY() - e1.getY() > FLIP_DISTANCE) {
                        Log.i("MYTAG", "向下滑...");
                        onBackPressed();
                        return true;
                    }
                }
                Log.d("TAG", e2.getX() + " " + e2.getY());
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            /**
             *
             * e1 The first down motion event that started the fling. e2 The
             * move motion event that triggered the current onFling.
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }

    private void dealListView() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        for (int i = 0; i < 4; i++) {
            View childView = layoutInflater.inflate(R.layout.detail_list_item, null);
            listContainer.addView(childView);
            ImageView headView = (ImageView) childView.findViewById(R.id.head);
            TextView name = childView.findViewById(R.id.name);
            TextView comment = childView.findViewById(R.id.comment);
            name.setText("person"+ String.valueOf(i));
            comment.setText("美女排行榜"+String.valueOf(i));
            if (i < headStrs.length) {
                headView.setImageResource(imageIds[i % imageIds.length]);
                ViewCompat.setTransitionName(headView, headStrs[i]);
            }
            final int finalI = i;
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    if(id == 0 || id == 2 || id == 4){
                        intent.setClass(DetailActivity.this,VideoActivity.class);
                        intent.putExtra("position",finalI);
                        intent.putExtra("videoUrl",VideoConstant.videoUrlList[finalI]);
                        intent.putExtra("imageUrl",VideoConstant.videoThumbList[finalI]);

                    }else {
                        intent.setClass(DetailActivity.this,Video2Activity.class);
                        intent.putExtra("videoUrl",VideoConstant.videoUrlList[finalI]);
                        intent.putExtra("imageUrl",VideoConstant.videoThumbList[finalI]);
                    }
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("按下了back键   onBackPressed()");
    }
}
