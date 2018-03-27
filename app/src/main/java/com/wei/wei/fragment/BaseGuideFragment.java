package com.wei.wei.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by wei on 2018/2/1/001.
 */

public abstract class BaseGuideFragment extends Fragment {

    public abstract int[] getChildViewIds() ;

    public abstract int getRootViewId();
}