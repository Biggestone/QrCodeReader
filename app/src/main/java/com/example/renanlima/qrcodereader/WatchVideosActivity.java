package com.example.renanlima.qrcodereader;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class WatchVideosActivity extends AppCompatActivity {

    MediaSessionCompat mediaSessionCompat;
    PlaybackStateCompat.Builder playbackBuilder;
    VideoView mVideoViewPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_watch_videos);



        /*mediaSessionCompat = new MediaSessionCompat(this,"String qualquer");
        mediaSessionCompat.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS|
                MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        mediaSessionCompat.setMediaButtonReceiver(null);

        playbackBuilder = new PlaybackStateCompat.Builder().setActions(PlaybackStateCompat.ACTION_PLAY|
        PlaybackStateCompat.ACTION_PLAY_PAUSE);
        mediaSessionCompat.setPlaybackState(playbackBuilder.build());

        mediaSessionCompat.setCallback(new MySessionCallback());

        MediaControllerCompat mediaController = new MediaControllerCompat(this, mediaSessionCompat);

        MediaControllerCompat.setMediaController(this,mediaController);*/

    }

    public void lerQrCode(View view) {

        IntentIntegrator integrator =  new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);


        if(result!=null){
            String content = result.getContents();

            initiateVideo(content);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void initiateVideo(String videoName){
        Uri uri=null;

        mVideoViewPlayer = (VideoView)findViewById(R.id.vv_player);

        MediaController mediaController = new MediaController(this);
        mediaController.setAlpha(0.5f);
        mediaController.setAnchorView(mVideoViewPlayer);

        if(videoName.equals("escada")){
            uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video_a);
        }else if(videoName.equals("concreto")){
            uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video_b);
        }else if(videoName.equals("tecnologia")){
            uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video_c);
        }
        //Uri uri = Uri.parse("https://www.youtube.com/785bd60f-d16f-403a-aa19-981611edff32");
        mVideoViewPlayer.setMediaController(mediaController);
        mVideoViewPlayer.setVideoURI(uri);
        mVideoViewPlayer.requestFocus();
        mVideoViewPlayer.start();
    }
}
