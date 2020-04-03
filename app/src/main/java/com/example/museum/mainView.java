package com.example.museum;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class mainView extends AppCompatActivity implements  View.OnClickListener {
    LinearLayout btnShowPlayer;
//    LinearLayout SongList;
    private ListView listView;
    List<song> songNames = new ArrayList<>();
//    private ViewPager viewPager;
//    private TabLayout tabLayout;
//    private androidx.appcompat.widget.Toolbar mToolbar;

//    private Albums albums;
//    private Artists artists;
//    private Songs songs;
//    private Playlists playlists;
//    private Genres genres;
//
//    private DrawerLayout drawerLayout;
//    private NavigationView navigationView;
//



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnShowPlayer = findViewById(R.id.showPlayer);
        btnShowPlayer.setOnClickListener(this);
//        songNames=getAllAudioFromDevice(this);
//        listView=findViewById(R.id.listAllSongs);
////        List<String> songs=new ArrayList<>();
//        for (song x: songNames)
//        {
//            songs.add(x.getName());
//        }
//        ArrayAdapter<String> adapter;
//        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,songs);
//        listView.setAdapter(adapter);
//        albums = new Albums();
//        artists = new Artists();
//        songs = new Songs();
//        playlists = new Playlists();
//        genres = new Genres();
//        viewPager = findViewById(R.id.view_pager);
//        tabLayout= findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0 );
//        viewPagerAdapter.addFragment(songs,getString(R.string.tab1));
//        viewPagerAdapter.addFragment(albums,getString(R.string.tab2));
//        viewPagerAdapter.addFragment(artists,getString(R.string.tab3));
//        viewPagerAdapter.addFragment(genres,getString(R.string.tab4));
//        viewPagerAdapter.addFragment(playlists,getString(R.string.tab5));
//        viewPager.setAdapter(viewPagerAdapter);

//        mToolbar = findViewById(R.id.main_toolbar);
//        setSupportActionBar(mToolbar);
//
//        drawerLayout = findViewById(R.id.drawer_layout);
//        navigationView= findViewById(R.id.nav_view);


//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
//                this,
//                drawerLayout,
//                mToolbar,
//                R.string.openNav,
//                R.string.closeNav
//        );
//
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);

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
                    song s = new song(name.replace(".mp3",""), artist, url);
                    tempAudioList.add(s);
                } while (cursor.moveToNext());
            }

            cursor.close(); }
        return tempAudioList;
    }


    @Override
    public void onClick(View v) {
        int btnId = v.getId();
        switch (btnId) {
            case R.id.songTile:
                break;
            case R.id.showPlayer:
                startActivity(new Intent(this,player.class));
                break;
        }
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        return false;
//    }


//    private class ViewPagerAdapter extends FragmentPagerAdapter {
//
//        private List<Fragment> fragments = new ArrayList<>();
//        private List<String> titles = new ArrayList<>();
//
//
//        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
//            super(fm, behavior);
//        }
//
//        public void addFragment(Fragment fragment, String title){
//            fragments.add(fragment);
//            titles.add(title);
//        }
//
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            return fragments.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return fragments.size();
//        }
//
//        @Nullable
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return titles.get(position);
//        }
    }



