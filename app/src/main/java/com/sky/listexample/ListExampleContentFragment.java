package com.sky.listexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sky.myapplication.study02.R;

/**
 * @author 施 凯 沅
 * @version 0.0.1
 */
public class ListExampleContentFragment extends Fragment {
    private String titleText;
    private String contentText;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_example, container, false);
        return view;
    }
    public void  refresh(String title,String content){
        TextView titleText = view.findViewById(R.id.layout_fragment_example_title);
        TextView contentText = view .findViewById(R.id.layout_fragment_example_content);
        titleText.setText(title);
        contentText.setText(content);
    }
}
