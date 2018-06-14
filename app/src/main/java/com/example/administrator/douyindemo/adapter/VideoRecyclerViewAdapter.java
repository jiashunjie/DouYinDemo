package com.example.administrator.douyindemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.douyindemo.R;
import com.example.administrator.douyindemo.bean.VideoBean;
import com.example.administrator.douyindemo.controller.VideoController;
import com.example.ijkplayer.player.IjkVideoView;
import com.example.ijkplayer.player.PlayerConfig;

import java.util.List;

/**
 * @author Jshunjie
 * @date 2018/5/25
 * @description 视频数据适配器
 */
public class VideoRecyclerViewAdapter extends RecyclerView.Adapter<VideoRecyclerViewAdapter.VideoHolder> {
    private List<VideoBean> videos;
    private Context context;

    public VideoRecyclerViewAdapter(List<VideoBean> videos, Context context) {
        this.videos = videos;
        this.context = context;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_video_play, parent, false);
        return new VideoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VideoHolder holder, int position) {
        VideoBean videoBean = videos.get(position);
        Glide.with(context)
                .load(videoBean.getThumb())
                .into(holder.controller.getThumb());
        holder.ijkVideoView.setScreenScale(IjkVideoView.SCREEN_SCALE_CENTER_CROP);
        holder.ijkVideoView.setUrl(videoBean.getUrl());
        holder.ijkVideoView.setTitle(videoBean.getTitle());
        holder.title.setText(videoBean.getTitle());
        holder.name.setText("@" + videoBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        private IjkVideoView ijkVideoView;
        private VideoController controller;
        private TextView title, name;
        private PlayerConfig mPlayerConfig;

        VideoHolder(View itemView) {
            super(itemView);
            ijkVideoView = itemView.findViewById(R.id.video_view);
            ijkVideoView.setScreenScale(IjkVideoView.SCREEN_SCALE_CENTER_CROP);
            controller = new VideoController(context);
            ijkVideoView.setVideoController(controller);
            title = itemView.findViewById(R.id.tv_title);
            name = itemView.findViewById(R.id.tv_name);
            mPlayerConfig = new PlayerConfig.Builder()
                    .enableCache()
                    .setLooping()
                    .savingProgress()
                    .usingSurfaceView()
                    .addToPlayerManager()
                    .build();
            ijkVideoView.setPlayerConfig(mPlayerConfig);
        }
    }

}