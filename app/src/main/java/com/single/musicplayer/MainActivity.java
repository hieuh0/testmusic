package com.single.musicplayer;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ListView lvMusic;


    // Songs list
    public ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
  // public String MEDIA_PATH = String.valueOf(Environment.getExternalStorageState());
    // Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        final ArrayList<HashMap<String, String>> songsListData = new ArrayList<HashMap<String, String>>();
        final String MEDIA_PATH = Environment.getExternalStorageDirectory().getPath() + "/";
        SongsManager plm = new SongsManager();        // get all songs from sdcard
        Log.d("TAG",MEDIA_PATH);

        this.songsList = plm.getPlayList();
        // looping through playlist
        for (int i = 0; i < songsList.size(); i++) {
            // creating new HashMap
            HashMap<String, String> song = songsList.get(i);
            Log.d("TAG",song.toString());
            // adding HashList to ArrayList
            songsListData.add(song);
        }

        // Adding menuItems to ListView
        ListAdapter adapter = new SimpleAdapter(this, songsListData,
                R.layout.playlist_item, new String[] { "songTitle" }, new int[] {
                R.id.songTitle });
       lvMusic.setAdapter(adapter);


        lvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SONGPATH",songsListData.get(position).get("songPath"));
                Intent intent = new Intent(getApplicationContext(),PlayerActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });

    }


    private void initView(){
        lvMusic = (ListView) findViewById(android.R.id.list);
    }


}
