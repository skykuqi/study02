package com.sky.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.myapplication.study02.R;

/**
 * @author 施 凯 沅
 * @version 0.0.1
 */
public class LeftFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //绑定布局
        View inflate = inflater.inflate(R.layout.layout_fragment_example, container, false);
        return inflate;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        //判断当前的Fragment是否可见
        super.setUserVisibleHint(isVisibleToUser);
    }
}
