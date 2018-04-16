package com.example.dell.filmera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoActivity extends YouTubeBaseActivity  {
   YouTubePlayerView playerView;
   YouTubePlayer.OnInitializedListener onInitializedListener;
   ArrayList<ApiClassForVideos.Data4> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Intent k=getIntent();
        final String key=k.getStringExtra("key");
        playerView=findViewById(R.id.youtube);
        onInitializedListener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(key);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        playerView.initialize("AIzaSyABhpSyzxbs1dNmImT-2KWC3GuqsC1vCFg",onInitializedListener);
    }

}
