package com.wei.wei.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wei.wei.R;
import com.wei.wei.activity.ClassifyActivity;
import com.wei.wei.activity.GalleryActivity;
import com.wei.wei.activity.SlideMenuActivity;
import com.wei.wei.activity.UpglideActivity;

public class ThreeFragment extends Fragment implements View.OnClickListener{

    private String type;

    public static ThreeFragment newInstance(String url){
        ThreeFragment fragment = new ThreeFragment();
        Bundle args = new Bundle();
        args.putString("type", url);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        initView(view);
        return view;
    }
    private void initView(View view) {
        Button btn1 = (Button) view.findViewById(R.id.btn1);
        Button btn2 = (Button) view.findViewById(R.id.btn2);
        Button btn3 = (Button) view.findViewById(R.id.btn3);
        Button btn4 = (Button) view.findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:
                startActivity(new Intent(getActivity(),SlideMenuActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(getActivity(),ClassifyActivity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(getActivity(),GalleryActivity.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(getActivity(),UpglideActivity.class));
                break;

        }
    }
}
