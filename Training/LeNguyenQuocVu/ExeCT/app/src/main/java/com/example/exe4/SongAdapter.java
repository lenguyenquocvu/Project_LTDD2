package com.example.exe4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends BaseAdapter {
    // Song list và layout
    private ArrayList<Song> songs;
    private LayoutInflater songInf;

    // Constructor
    public SongAdapter(ArrayList<Song> songs, LayoutInflater songInf) {
        this.songs = songs;
        this.songInf = songInf;
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout listview_item = (LinearLayout) songInf.inflate(R.layout.listview_item,
                                                                    parent, false);
        TextView songView = (TextView)listview_item.findViewById(R.id.song_title);

        TextView artistView = (TextView)listview_item.findViewById(R.id.song_artist);

        // Get song
        Song currentSong = songs.get(position);

        // Get title and artist
        songView.setText(currentSong.getTitle());
        songView.setText(currentSong.getArtist());

        // Setting tag and one song is one location
        listview_item.setTag(position);
        return listview_item;
    }
}
