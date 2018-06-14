package com.example.administrator.douyindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.douyindemo.adapter.VideoRecyclerViewAdapter;
import com.example.administrator.douyindemo.util.DataUtil;
import com.example.ijkplayer.player.IjkVideoView;


import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setNestedScrollingEnabled(false);
        final PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(new VideoRecyclerViewAdapter(DataUtil.getDouYinVideoList(), this));
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                IjkVideoView ijkVideoView = view.findViewById(R.id.video_view);
                if (ijkVideoView != null) {
                    ijkVideoView.stopPlayback();
                }
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case SCROLL_STATE_IDLE: //滚动停止
                        IjkVideoView ijkVideoView = snapHelper.findSnapView(layoutManager).findViewById(R.id.video_view);
                        if (ijkVideoView != null) {
                            ijkVideoView.setScreenScale(IjkVideoView.SCREEN_SCALE_CENTER_CROP);
                            ijkVideoView.start();
                        }
                        break;
                }
            }
        });

//        recyclerView.post(() -> {
//            //自动播放第一个
//            View view = recyclerView.getChildAt(0);
//            IjkVideoView ijkVideoView = view.findViewById(R.id.video_view);
//            ijkVideoView.start();
//        });
    }
}
