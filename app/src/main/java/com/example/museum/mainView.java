package com.example.museum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class mainView extends AppCompatActivity implements View.OnClickListener {
    LinearLayout btnShowPlayer;
    LinearLayout btnSong;
    private ListView listView;
//    private String songNames[];
    List<String> songNames = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnShowPlayer = findViewById(R.id.showPlayer);
        btnShowPlayer.setOnClickListener(this);
        btnSong = findViewById(R.id.song);
        btnSong.setOnClickListener(this);
//        }
        getAllAudioFromDevice(this);


    }

//    private ArrayList<File> readSongs(File root) {
//        ArrayList<File> arrayList = new ArrayList<File>();
//        File files[] = root.listFiles();
//        for (File file : files) {
//            if (file.isDirectory()) {
//                arrayList.addAll(readSongs(file));
//            } else {
//                if (file.getName().endsWith(".mp3")) {
//                    arrayList.add(file);
//                }
//            }
//        }
//        return arrayList;
//    }

    public List<song> getAllAudioFromDevice(final Context context) {
        final List<song> tempAudioList = new ArrayList<>();

//        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        String[] projection = {MediaStore.Audio.AudioColumns.DATA,MediaStore.Audio.AudioColumns.TITLE ,MediaStore.Audio.AudioColumns.ALBUM, MediaStore.Audio.ArtistColumns.ARTIST,};
//        Cursor c = context.getContentResolver().query(uri, projection, MediaStore.Audio.Media.IS_MUSIC + " like ? ", new String[]{"%utm%"}, null);

//        if (c != null) {
//            while (c.moveToNext()) {
//                song audioModel = new song();
//
//                String path = c.getString(0);   // Retrieve path.
//                String name = c.getString(1);   // Retrieve name.
//                String album = c.getString(2);  // Retrieve album name.
//                String artist = c.getString(3); // Retrieve artist name.
//
//                // Set data to the model object.
//                audioModel.setaName(name);
//                audioModel.setaAlbum(album);
//                audioModel.setaArtist(artist);
//                audioModel.setaPath(path);
//
//                Log.e("Name :" + name, " Album :" + album);
//                Log.e("Path :" + path, " Artist :" + artist);
//
//                // Add the model object to the list .
//                tempAudioList.add(audioModel);
//            }
//            c.close();
//        }

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    song s = new song(name, artist, url);
                    songNames.add(name);
                    tempAudioList.add(s);
                } while (cursor.moveToNext());
            }

            cursor.close(); }

        // Return the list.
        return tempAudioList;
    }


    @Override
    public void onClick(View v) {
        int btnId = v.getId();
        switch (btnId) {
            case R.id.song:
                Toast.makeText(this, ""+songNames.get(0), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,player.class));
                break;
            case R.id.showPlayer:
                break;
        }
    }
}
