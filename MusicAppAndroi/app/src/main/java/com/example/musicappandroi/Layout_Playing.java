package com.example.musicappandroi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.text.SimpleDateFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib.Models.TrackSongModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Layout_Playing extends AppCompatActivity {
ImageButton PreSong,NextSong,Play_Pause,Random,Loop,PlayList,List;
TextView RuntimeSong,DurationSong,TitleSong,SingerSongPlaying;
ImageView ImageSong;
public static MediaPlayer mediaPlayer;
SeekBar SeekbarSong;


    int vitri =0;
    boolean repeatsong= false;
    boolean randomsong= false;
    boolean nextsong=false;
    public  ArrayList<TrackSongModel.TrackSong> listsong = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_layout_playing);
        PreSong = findViewById(R.id.ButPrePlaying);
        NextSong = findViewById(R.id.ButNextPlaying);
        Play_Pause = findViewById(R.id.But_Play_Pause);
        RuntimeSong = findViewById(R.id.RuntimeSongPlaying);
        DurationSong = findViewById(R.id.DurationSongPlaying);
        TitleSong = findViewById(R.id.TitleSongPlaying);
        ImageSong = findViewById(R.id.ImageSongPlaying);
        SeekbarSong = findViewById(R.id.SeekbarSongPlaying);
        Random = findViewById(R.id.But_Random);
        Loop = findViewById(R.id.But_Loop);
        PlayList = findViewById(R.id.But_PlayList);
        SingerSongPlaying = findViewById(R.id.SingerSongPlaying);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("SongBundle");
        String song3 =  bundle.getString("idSong");
        listsong = (ArrayList<TrackSongModel.TrackSong>) bundle.getSerializable("songlist");
        TrackSongModel.TrackSong Song = (TrackSongModel.TrackSong) bundle.getSerializable("Song");
        Log.v("GETABC", String.valueOf(listsong.size()));
        Toast.makeText(this, "dfg", Toast.LENGTH_SHORT).show();
        StopMusic();
        if(listsong.size()>0){
            for (TrackSongModel.TrackSong data : listsong
            ) {
                if(song3.equals(data.getSongId()))
                {
                    new PlayAudio().execute(data.getSongLocation());
                    Play_Pause.setImageResource(R.drawable.pause_24px);
                    setAtt(data.getSongImage(),data.getSongName(),data.getArtistName(),data.getSongDuration());
                }
            }
        }
        ClickEvent();

    }




    private void ClickEvent(){

        // nút play
        Play_Pause.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                Play_Pause.setImageResource(R.drawable.play_24px);
            } else {
                mediaPlayer.start();
               Play_Pause.setImageResource(R.drawable.pause_24px);
            }
        });
        // tua nhạc
        SeekbarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        // nút repeat
        Loop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeatsong == false){
                    if(randomsong == true){
                        randomsong = false;
                        Random.setColorFilter(Color.WHITE);
                    }
                    Loop.setColorFilter(Color.RED);
                    Toast.makeText(Layout_Playing.this, "Repeat is on", Toast.LENGTH_SHORT).show();
                    repeatsong=true;
                }else {
                    Loop.setColorFilter(Color.WHITE);
                    Toast.makeText(Layout_Playing.this, "Repeat is off", Toast.LENGTH_SHORT).show();
                    repeatsong=false;
                }
            }
        });

        Random.setOnClickListener(v -> {
            if (randomsong == false){
                if(repeatsong == true){
                    repeatsong = false;
                    Loop.setColorFilter(Color.WHITE);
                }
                Random.setColorFilter(Color.RED);
                randomsong=true;
                Toast.makeText(Layout_Playing.this, "Random is on", Toast.LENGTH_SHORT).show();
            }else {
                Random.setColorFilter(Color.WHITE);
                randomsong=false;
                Toast.makeText(Layout_Playing.this, "Random is off", Toast.LENGTH_SHORT).show();
            }
        });

        NextSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listsong.size() >0 ){
                    StopMusic();
                    if(vitri < (listsong.size())){
                        Play_Pause.setImageResource(R.drawable.pause_24px);
                        vitri++;
                        if(repeatsong == true){
                            if(vitri == 0){
                                vitri = listsong.size();
                            }
                            vitri = -1;
                        }
                        if(randomsong == true){
                            Random random = new Random();
                            int i = random.nextInt(listsong.size());
                            if(i == vitri){
                                vitri = i-1;
                            }
                            vitri = i;
                        }
                        if(vitri > (listsong.size()-1)){
                            vitri=0;
                        }
                        new PlayAudio().execute(listsong.get(vitri).getSongLocation());
                        setAtt(listsong.get(vitri).getSongImage(),listsong.get(vitri).getSongName(),listsong.get(vitri).getArtistName(),listsong.get(vitri).getSongDuration());
                        TimeRun();
                    }
                }
                PreSong.setClickable(false);
                NextSong.setClickable(false);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PreSong.setClickable(true);
                        NextSong.setClickable(true);
                    }
                },5000);
            }
        });

        PreSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listsong.size() >0 ){
                    StopMusic();
                    if(vitri < (listsong.size())){
                        Play_Pause.setImageResource(R.drawable.pause_24px);
                        vitri--;
                        if(vitri <0){
                            vitri = listsong.size()-1;
                        }
                        if(repeatsong == true){

                            vitri +=1;
                        }
                        if(randomsong == true){
                            Random random = new Random();
                            int i = random.nextInt(listsong.size());
                            if(i == vitri){
                                vitri = i-1;
                            }
                            vitri = i;
                        }
                        new PlayAudio().execute(listsong.get(vitri).getSongLocation());
                        setAtt(listsong.get(vitri).getSongImage(),listsong.get(vitri).getSongName(),listsong.get(vitri).getArtistName(),listsong.get(vitri).getSongDuration());
                        TimeRun();
                    }
                }
                PreSong.setClickable(false);
                NextSong.setClickable(false);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PreSong.setClickable(true);
                        NextSong.setClickable(true);
                    }
                },5000);
            }
        });

    }

    private void StopMusic(){
        if(mediaPlayer !=null && mediaPlayer.isPlaying()){
            try {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer=null;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public class  PlayAudio extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }
        @Override
        protected void onPostExecute(String song) {
            super.onPostExecute(song);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                mediaPlayer.setOnCompletionListener(mp -> {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                });

                mediaPlayer.setDataSource(song);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            ThoiGianSong();
            TimeRun();
        }
    }
    private void ThoiGianSong() {
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("mm:ss");
        DurationSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        SeekbarSong.setMax(mediaPlayer.getDuration());
    }

    private  void TimeRun(){
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer !=null){
                    SeekbarSong.setProgress(mediaPlayer.getCurrentPosition());
                    java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("mm:ss");
                    RuntimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            nextsong=true;
                            try {
                                Thread.sleep(1200);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (nextsong == true) {
                    if (vitri < (listsong.size())) {
                       Play_Pause.setImageResource(R.drawable.pause_24px);
                        vitri++;
                        if (repeatsong == true) {
                            if (vitri == 0) {
                                vitri = listsong.size();
                            }
                            vitri = -1;
                        }
                        if (randomsong == true) {
                            Random random = new Random();
                            int i = random.nextInt(listsong.size());
                            if (i == vitri) {
                                vitri = i - 1;
                            }
                            vitri = i;
                        }
                        if (vitri > (listsong.size() - 1)) {
                            vitri = 0;
                        }
                        new PlayAudio().execute(listsong.get(vitri).getSongLocation());
                        setAtt(listsong.get(vitri).getSongImage(),listsong.get(vitri).getSongName(),listsong.get(vitri).getArtistName(),listsong.get(vitri).getSongDuration());

                    }


                    PreSong.setClickable(false);
                    NextSong.setClickable(false);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            PreSong.setClickable(true);
                            NextSong.setClickable(true);
                        }
                    }, 5000);
                    nextsong=false;
                    handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this,1200);
                }
            }
        },1000);
    }




    private void setAtt(String urlImage, String titleSong,String Singer, String DuraSongs){

            TitleSong.setText(titleSong);
            SingerSongPlaying.setText(Singer);


        Picasso.get().load(urlImage).into(ImageSong);
        DurationSong.setText(DuraSongs);
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
       RuntimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        SeekbarSong.setMax(mediaPlayer.getDuration());
    }

}