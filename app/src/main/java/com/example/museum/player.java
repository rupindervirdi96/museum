package com.example.museum;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class player extends AppCompatActivity implements View.OnClickListener {
    ImageView playBtn, AlbumArt, nextBtn, prevBtn,addToPlaylist;
    MediaPlayer mp;
    TextView songName;
    song song;
    SeekBar seekbar;
    List<song> allSongs;
    boolean playing = false;
    int currentLength = 0;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);
        nextBtn = findViewById(R.id.nextBtn);
        addToPlaylist=findViewById(R.id.addToPlaylist);
        addToPlaylist.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        prevBtn = findViewById(R.id.prevbtn);
        prevBtn.setOnClickListener(this);
        allSongs = (List<song>) getIntent().getExtras().getSerializable("allSongs");
        playBtn = findViewById(R.id.playButton);
        AlbumArt = findViewById(R.id.albumArt);
        playBtn.setOnClickListener(this);
        songName = findViewById(R.id.songName);
        song = (song) getIntent().getExtras().getSerializable("song");

        getSupportActionBar().hide();
        seekbar = findViewById(R.id.seekbar);
        songName.setText(song.getName().toUpperCase());


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.playButton:
                if (playing) {
                    mp.pause();
                    playBtn.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    currentLength = mp.getCurrentPosition();
                    playing = false;
                } else {
                    play();
                    mp.seekTo(currentLength);
                    currentLength = 0;
                }
                break;
            case R.id.prevbtn:
                playPrevious();
                break;
            case R.id.nextBtn:
                playnext();
                break;
            case R.id.addToPlaylist:
                startActivity(new Intent(this,player.class).putExtra("song",song));

        }
    }

    private void play() {
        uri = Uri.parse(song.getUrl());
        mp = MediaPlayer.create(getApplicationContext(), uri);
        songName.setText(song.getName().toUpperCase());
        mp.start();
        playBtn.setImageResource(R.drawable.ic_pause_black_24dp);
        playing = true;
    }


    private void playnext() {
        for (song x :
                allSongs) {
            if (x.getUrl().equals(uri.toString())) {
                int z = allSongs.indexOf(x);
                if (z < allSongs.size()-1) {
                    song = allSongs.get(z + 1);
                }
                mp.pause();
                play();
            }

        }
    }

    private void playPrevious() {
        for (song x :
                allSongs) {
            if (x.getUrl().equals(uri.toString())) {
                int z = allSongs.indexOf(x);
                if (z > 0) {
                    song = allSongs.get(z - 1);
                }
                mp.pause();
                play();
            }
        }
    }
}
