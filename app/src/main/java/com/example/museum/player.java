package com.example.museum;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class player extends AppCompatActivity implements View.OnClickListener {
    ImageView playBtn;
    MediaPlayer mp;
    String song;
    boolean playing=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);
        playBtn=findViewById(R.id.playButton);
        playBtn.setOnClickListener(this);
        song=getIntent().getExtras().getString("song");
        Toast.makeText(this, ""+song, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.playButton:
                if(playing)
                {
                    mp.pause();
                playing=false;
                }
                else{
                play();
                }

        }
    }

    private void play() {
                Uri u=Uri.parse(song);
                mp= MediaPlayer.create(getApplicationContext(),u);
                mp.start();
                playing=true;
    }
}
