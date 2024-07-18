package br.edu.ifsuldeminas.mch.netflix;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoPlayer videoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView videoView = findViewById(R.id.mainVideo);
        SeekBar seekBar = findViewById(R.id.currentViewBar);
        TextView timeElapsed = findViewById(R.id.timeElapsed);
        TextView timeTotal = findViewById(R.id.timeTotal);
        ImageButton playPauseButton = findViewById(R.id.buttomPlay);

        videoPlayer = new VideoPlayer(this, videoView, seekBar, timeElapsed, timeTotal, playPauseButton);

        Button btnTrabalho2 = findViewById(R.id.btnTrabalho2);
        Button btnTrabalho4 = findViewById(R.id.btnTrabalho4);

        btnTrabalho2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Trabalho02Activity.class);
                startActivity(intent);
            }
        });

        btnTrabalho4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Trabalho04Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoPlayer.release();
    }
}
