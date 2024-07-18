package br.edu.ifsuldeminas.mch.netflix;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import android.media.MediaPlayer;
import android.view.View;

public class VideoPlayer {
    private VideoView videoView;
    private SeekBar seekBar;
    private TextView timeElapsed;
    private TextView timeTotal;
    private ImageButton playPauseButton;
    private Handler handler = new Handler();
    private Runnable updateSeekBar;

    public VideoPlayer(Context context, VideoView videoView, SeekBar seekBar, TextView timeElapsed, TextView timeTotal, ImageButton playPauseButton) {
        this.videoView = videoView;
        this.seekBar = seekBar;
        this.timeElapsed = timeElapsed;
        this.timeTotal = timeTotal;
        this.playPauseButton = playPauseButton;

        Uri src = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.video);
        videoView.setVideoURI(src);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                int totalDuration = videoView.getDuration();
                timeTotal.setText(formatTime(totalDuration));
                seekBar.setMax(totalDuration);

                updateSeekBar = new Runnable() {
                    @Override
                    public void run() {
                        int currentPosition = videoView.getCurrentPosition();
                        seekBar.setProgress(currentPosition);
                        timeElapsed.setText(formatTime(currentPosition));
                        handler.postDelayed(this, 1000);
                    }
                };

                handler.postDelayed(updateSeekBar, 0);
                videoView.start();
                playPauseButton.setImageResource(android.R.drawable.ic_media_pause);
            }
        });

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                    playPauseButton.setImageResource(android.R.drawable.ic_media_play);
                } else {
                    videoView.start();
                    playPauseButton.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    videoView.seekTo(progress);
                    timeElapsed.setText(formatTime(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacks(updateSeekBar);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                handler.postDelayed(updateSeekBar, 0);
            }
        });
    }

    public void release() {
        handler.removeCallbacks(updateSeekBar);
    }

    private String formatTime(int milliseconds) {
        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / (1000 * 60)) % 60;
        int hours = (milliseconds / (1000 * 60 * 60)) % 24;

        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }
}
