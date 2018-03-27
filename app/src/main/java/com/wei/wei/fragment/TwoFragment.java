package com.wei.wei.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.Animation.OrderEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.wei.wei.R;

import java.util.ArrayList;
import java.util.List;

public class TwoFragment extends Fragment {

    BoomMenuButton bmb;
    private String type;
    private List<String> mDatas;

    private static int[] imageResources = new int[]{
            R.drawable.bat,
            R.drawable.bear,
            R.drawable.bee,
            R.drawable.butterfly,
            R.drawable.cat,
            R.drawable.deer,
            R.drawable.dolphin,
            R.drawable.eagle,
            R.drawable.horse,
            R.drawable.elephant,
            R.drawable.owl,
            R.drawable.peacock,
            R.drawable.pig,
            R.drawable.rat,
            R.drawable.snake,
            R.drawable.squirrel
    };

    public static TwoFragment newInstance(String url){
        TwoFragment fragment = new TwoFragment();
        Bundle args = new Bundle();
        args.putString("type", url);
        fragment.setArguments(args);
        return fragment;
    }

    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        RecyclerView recyclerView  = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDatas = new ArrayList<>();
        for ( int i=0; i < 40; i++) {
            mDatas.add( "item"+i);
        }
        HomeAdapter homeAdapter = new HomeAdapter(getActivity(),mDatas);
        recyclerView.setAdapter(homeAdapter);
//        textView.setText(type);
        initBoomMenu(view);
        return view;
    }

    private void initBoomMenu(View view) {
        bmb = view.findViewById(R.id.bmb);
        bmb.setInFragment(true);
        bmb.setOrderEnum(OrderEnum.RANDOM);
        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++) {
            SimpleCircleButton.Builder builder = new SimpleCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            // When the boom-button corresponding this builder is clicked.
                            Toast.makeText(getActivity(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .rotateImage(false)
                    .normalImageRes(getImageResource());
            bmb.addBuilder(builder);
        }
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{

        private List<String> mDatas;
        private Context mContext;
        private LayoutInflater inflater;

        public HomeAdapter(Context context, List<String> datas){
            this. mContext=context;
            this. mDatas=datas;
            inflater=LayoutInflater. from(mContext);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        //填充onCreateViewHolder方法返回的holder中的控件
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            holder.tv.setText( mDatas.get(position));
        }

        //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.item_home,parent, false);
            MyViewHolder holder= new MyViewHolder(view);
            return holder;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv=(TextView) view.findViewById(R.id. tv_item);
            }

        }
    }



}