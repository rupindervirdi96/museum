package com.example.museum;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class mainView extends AppCompatActivity implements View.OnClickListener {
    LinearLayout btnShowPlayer;
    private ListView listView;
    List<song> songNames = new ArrayList<>();
    private ListView menuListView,optionsListView;
    private DrawerLayout drawerLayout;
    LinearLayout menuDrawer;
    ImageView menuBtn,optionsBtn;
    TextView username;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        optionsListView=findViewById(R.id.secondaryListView);
        menuListView = findViewById(R.id.menuListView);
        ArrayList<String> secondaryMenuListItems = new ArrayList<>(Arrays.asList("SETTINGS"));
        ArrayList<String> mainMenuListItems = new ArrayList<>(Arrays.asList("ABOUT-US", "LOGOUT"));
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.list_item_basic, R.id.textView, mainMenuListItems);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, R.layout.list_item_basic, R.id.textView, secondaryMenuListItems);
        optionsListView.setAdapter(adapter3);
        optionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClickOnSecondaryMenuItem(parent,view,position,id);
            }
        } );
        menuListView.setAdapter(adapter2);
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClickOnMenuItem(parent,view,position,id);
            }
        } );
        username = findViewById(R.id.userName);
        String X = getIntent().getStringExtra("CurrUser");
        username.setText(X);
        drawerLayout = findViewById(R.id.drawer_layout);
        btnShowPlayer = findViewById(R.id.showPlayer);
        btnShowPlayer.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "PLEASE ALLOW STORAGE PERMISSION", Toast.LENGTH_LONG).show();
        }
        else{
        songNames = getAllAudioFromDevice(this);
        }
        listView = findViewById(R.id.listAllSongs);
        menuDrawer = findViewById(R.id.listMenu);
        List<String> songs = new ArrayList<>();
        List<String> albums = new ArrayList<>();
        for (song x : songNames) {
            songs.add(x.getName()
            );
            albums.add(x.getAlbum());
        }
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, R.layout.fragment_song, R.id.songName, songs);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClickOnSong(parent,view,position,id);
            }
        } );

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater li = LayoutInflater.from(this);
        View customToolbar = li.inflate(R.layout.toolbar, null);

        mActionBar.setCustomView(customToolbar);
        mActionBar.setDisplayShowCustomEnabled(true);

        menuBtn = customToolbar.findViewById(R.id.menu);
        menuBtn.setOnClickListener(this);
        optionsBtn=customToolbar.findViewById(R.id.options);
        optionsBtn.setOnClickListener(this);
    }


    @SuppressLint("ResourceType")
    public List<song> getAllAudioFromDevice(final Context context) {
        final List<song> tempAudioList = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    song s = new song(name.replace(".mp3", ""), artist, url);
                    tempAudioList.add(s);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return tempAudioList;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.menu:
                if (drawerLayout.isDrawerOpen(menuDrawer)) {
                    drawerLayout.closeDrawer(menuDrawer);
                } else {
                    drawerLayout.openDrawer(menuDrawer);
                }
                break;
            case R.id.options:
                if (drawerLayout.isDrawerOpen(optionsListView)) {
                    drawerLayout.closeDrawer(optionsListView);
                } else {
                    drawerLayout.openDrawer(optionsListView);
                }
                break;
        }
    }

    public void ClickOnSong(AdapterView<?> parent, View view, int position, long id)
    {
        Intent i = new Intent(this, player.class);
        song song = songNames.get(position);
        i.putExtra("song", song);
        i.putExtra("allSongs",(Serializable) songNames);
        startActivity(i);
    }

    public void ClickOnMenuItem(AdapterView<?> parent, View view, int position, long id)
    {
        Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
        switch (position)
        {
            case 0:
                startActivity(new Intent(this,activity_about_us.class));
                break;
            case 1:
                startActivity(new Intent(this,login.class));
                break;
        }
    }
    private void ClickOnSecondaryMenuItem(AdapterView<?> parent, View view, int position, long id) {


    }
}



