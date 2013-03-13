package com.acti.questionme;

import com.acti.questionme.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class UserPageActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_page);
		
		TabHost tabHost=getTabHost();
		TabSpec myQuestions=tabHost.newTabSpec("My");
		Intent myQ=new Intent(this,MyQuestionsActivity.class);
		
		
//		TabHost tabHost = getTabHost();
		 
//        // Tab for Photos
//        TabSpec photospec = tabHost.newTabSpec("Photos");
//        // setting Title and Icon for the Tab
//        photospec.setIndicator("Photos", getResources().getDrawable(R.drawable.icon_photos_tab));
//        Intent photosIntent = new Intent(this, PhotosActivity.class);
//        photospec.setContent(photosIntent);
// 
//        // Tab for Songs
//        TabSpec songspec = tabHost.newTabSpec("Songs");
//        songspec.setIndicator("Songs", getResources().getDrawable(R.drawable.icon_songs_tab));
//        Intent songsIntent = new Intent(this, SongsActivity.class);
//        songspec.setContent(songsIntent);
// 
//        // Tab for Videos
//        TabSpec videospec = tabHost.newTabSpec("Videos");
//        videospec.setIndicator("Videos", getResources().getDrawable(R.drawable.icon_videos_tab));
//        Intent videosIntent = new Intent(this, VideosActivity.class);
//        videospec.setContent(videosIntent);
// 
//        // Adding all TabSpec to TabHost
//        tabHost.addTab(photospec); // Adding photos tab
//        tabHost.addTab(songspec); // Adding songs tab
//        tabHost.addTab(videospec); // Adding vide
	}




}
