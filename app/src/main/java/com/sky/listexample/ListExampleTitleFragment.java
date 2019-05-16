package com.sky.listexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sky.myapplication.study02.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 施 凯 沅
 * @version 0.0.1
 */
public class ListExampleTitleFragment extends Fragment {
    private RecyclerView layout_fragment_example_list_recycler;
    private List<News> newsList = new ArrayList<News>();
    //判断是否为大屏幕
    private boolean isTwoFragment = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_fragment_example_list, container, false);
        addList(20);
        ListExampleTitleRecyclerAdapter listExampleTitleRecyclerAdapter = new ListExampleTitleRecyclerAdapter(newsList);
        layout_fragment_example_list_recycler = inflate.findViewById(R.id.layout_fragment_example_list_recycler);
        layout_fragment_example_list_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        layout_fragment_example_list_recycler.setAdapter(listExampleTitleRecyclerAdapter);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if ((getActivity().findViewById(R.id.list_example_fragment_content)) != null) {
            isTwoFragment = true;
        } else {
            isTwoFragment = false;
        }
    }

    //增加list中的数据
    private void addList(int count) {
        String title = "这是标题";
        Random random = new Random(47);
        for (int i = 0; i < count; i++) {
            newsList.add(new News().setNewsTiele(title + i).setNewsContent(getContent(random.nextInt(30))));
        }
    }
    private String getContent(int index) {
        String content = "这是内容";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < index; i++) {
            sb.append(content).append(i);
        }
        return sb.toString();
    }
    class ListExampleTitleRecyclerAdapter extends RecyclerView.Adapter<ListExampleTitleRecyclerAdapter.MyViewHolder> {
        private List<News> newsList;
        public ListExampleTitleRecyclerAdapter(List<News> newsList) {
            this.newsList = newsList;
        }
        class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView layout_recycler_example_title;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                layout_recycler_example_title = itemView.findViewById(R.id.layout_recycler_example_title);
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_example_list_item, parent, false);
            final MyViewHolder myViewHolder = new MyViewHolder(view);
            view.findViewById(R.id.layout_recycler_example_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = myViewHolder.getLayoutPosition();
                    News news = newsList.get(index);
                    if (isTwoFragment) {
                        ListExampleContentFragment fragment = (ListExampleContentFragment) getFragmentManager()
                                .findFragmentById(R.id.list_example_fragment_content);
                        fragment.refresh(news.getNewsTiele(),news.getNewsContent());
                    } else {
                        Intent intent = new Intent(getActivity(),ListExampleContentActivity.class);
                        intent.putExtra("title",news.getNewsTiele());
                        intent.putExtra("content",news.getNewsContent());
                        startActivity(intent);
                    }
                }
            });
            return myViewHolder;
        }
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.layout_recycler_example_title.setText(newsList.get(position).getNewsTiele());
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }
    }
}
