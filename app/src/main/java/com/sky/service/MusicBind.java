package com.sky.service;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Binder;

import com.sky.myapplication.study02.R;

/**
 * @author 施 凯 沅
 * @version 0.0.1
 */
public class MusicBind extends Binder {
    private MediaPlayer mediaPlayer;
    private boolean isOnstart = false;

    /**
     * 开启音乐的方法
     */
    public void onStartMusic(Context context) {
        if (isOnstart == false) {
            if (mediaPlayer == null) {
                //初始化多媒体播放器
                mediaPlayer = new MediaPlayer();
            }
            //系统提供的播放器
            mediaPlayer = MediaPlayer.create(context, R.raw.play);
            mediaPlayer.start();
            isOnstart = true;
        }
    }

    /**
     * 暂停播放音乐
     */
    public void onPauseMusic() {
        if(mediaPlayer != null){
            mediaPlayer.pause();
            isOnstart = false;
        }
    }
}
