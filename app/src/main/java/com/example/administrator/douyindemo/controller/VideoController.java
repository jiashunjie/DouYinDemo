package com.example.administrator.douyindemo.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.administrator.douyindemo.R;
import com.example.ijkplayer.controller.GestureVideoController;
import com.example.ijkplayer.player.IjkVideoView;
import com.example.ijkplayer.util.L;


/**
 * @author Jshunjie
 * @date 2018/5/25
 * @description 视频控制器
 */
public class VideoController extends GestureVideoController implements View.OnClickListener {
    private ImageView playButton;
    private ProgressBar loadingProgress;
    private ImageView thumb;

    public VideoController(@NonNull Context context) {
        super(context);
//        this.thumb = thumb;
    }

    public VideoController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_controller;
    }

    @Override
    protected void initView() {
        super.initView();
        thumb = controllerView.findViewById(R.id.thumb);
        thumb.setOnClickListener(this);
        playButton = controllerView.findViewById(R.id.iv_play);
        loadingProgress = controllerView.findViewById(R.id.loading);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_play:
            case R.id.thumb: {
                doPauseResume();
            }
            break;
            default:
                break;
        }
    }

    @Override
    public void setPlayState(int playState) {
        super.setPlayState(playState);
        switch (playState) {
            case IjkVideoView.STATE_IDLE://闲置状态
                L.e("STATE_IDLE");
                hide();
                isLocked = false;
                mediaPlayer.setLock(false);
                loadingProgress.setVisibility(GONE);
                thumb.setVisibility(VISIBLE);
                playButton.setVisibility(GONE);
                break;
            case IjkVideoView.STATE_PLAYING://播放状态
                L.e("STATE_PLAYING");
                post(mShowProgress);
                loadingProgress.setVisibility(GONE);
                thumb.setVisibility(GONE);
                playButton.setVisibility(GONE);
                break;
            case IjkVideoView.STATE_PAUSED://暂停状态
                L.e("STATE_PAUSED");
                playButton.setVisibility(VISIBLE);
                break;
            case IjkVideoView.STATE_PREPARING://准备状态
                L.e("STATE_PREPARING");
                playButton.setVisibility(GONE);
                loadingProgress.setVisibility(VISIBLE);
                thumb.setVisibility(VISIBLE);
                break;
            case IjkVideoView.STATE_ERROR://播放错误
                L.e("STATE_ERROR");
                playButton.setVisibility(GONE);
                loadingProgress.setVisibility(GONE);
                thumb.setVisibility(GONE);
                break;

            case IjkVideoView.STATE_PLAYBACK_COMPLETED://播放完成
                L.e("STATE_PLAYBACK_COMPLETED");
                hide();
                removeCallbacks(mShowProgress);
//                playButton.setVisibility(VISIBLE);
//                thumb.setVisibility(VISIBLE);
                isLocked = false;
//                mediaPlayer.pause();
                break;
        }
    }

    @Override
    public void hide() {
        if (mShowing) {
            playButton.setVisibility(GONE);
            mShowing = false;
            doPauseResume();
        }
    }

    public ImageView getThumb() {
        return thumb;
    }

    private void show(int timeout) {
        if (!mShowing) {
            playButton.setVisibility(VISIBLE);
            mShowing = true;
            doPauseResume();
        }
        removeCallbacks(mFadeOut);
        if (timeout != 0) {
//            postDelayed(mFadeOut, timeout);
        }
    }

    @Override
    public void show() {
        show(sDefaultTimeout);
    }
}
