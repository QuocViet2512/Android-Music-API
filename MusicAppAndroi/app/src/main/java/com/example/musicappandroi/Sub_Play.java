package com.example.musicappandroi;

import static com.example.lib.RetrofitClient.getRetrofit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lib.InterfaceResponsitory.Methods;
import com.example.lib.Models.ArtistModel;
import com.example.lib.Models.GenreModel;
import com.example.lib.Models.Get_Infor_Artist_Model;
import com.example.lib.Models.TrackSongModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sub_Play extends Fragment {
   public static Context mcontext;


    TrackSongModel.TrackSong song;
    public View mview;
    ImageButton PreSong,NextSong,Play_Pause,Random,Loop,PlayList,List,InforArtist;
    TextView RuntimeSong,DurationSong,TitleSong,Singer,ArtistName,ArtistNational;
    ImageView ImageSong,ArtistImage;
    public static MediaPlayer mediaPlayer;
    SeekBar SeekbarSong;
    Dialog dialog;



    public static  int vitri =0;
   public static boolean repeatsong= false;
   public static boolean  randomsong= false;
    boolean nextsong=false;
    int length =0;
    public static ArrayList<TrackSongModel.TrackSong> listsong ;
    public static  Bundle getbundel;

    public Sub_Play(Context context){
        this.mcontext=context;
    }
    public Sub_Play(){}
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mview =inflater.inflate(R.layout.sub_play,container,false);
        PreSong = mview.findViewById(R.id.S_ButPrePlaying);
        NextSong = mview.findViewById(R.id.S_ButNextPlaying);
        Play_Pause = mview.findViewById(R.id.S_But_Play_Pause);
        RuntimeSong = mview.findViewById(R.id.S_RuntimeSongPlaying);
        DurationSong = mview.findViewById(R.id.S_DurationSongPlaying);
        TitleSong = mview.findViewById(R.id.S_TitleSongPlaying);
        ImageSong = mview.findViewById(R.id.S_ImageSongPlaying);
        SeekbarSong = mview.findViewById(R.id.S_SeekbarSongPlaying);
        Random = mview.findViewById(R.id.S_But_Random);
        Loop = mview.findViewById(R.id.S_But_Loop);
        PlayList = mview.findViewById(R.id.S_But_PlayList);
        Singer = mview.findViewById(R.id.S_SingerSongPlaying);
        InforArtist = mview.findViewById(R.id.S_But_Information);
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_information_artist);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ArtistName= dialog.findViewById(R.id.Art_Information_Name);
        ArtistNational = dialog.findViewById(R.id.Art_Information_National);
        ArtistImage  = dialog.findViewById(R.id.Art_Information_Image);
        getbundel = getArguments();
      //  Log.v("akkk",( getbundel.get("LSong")).toString());

        if(listsong==null&&getbundel==null) {
            return mview;
        }else {

            if ((ArrayList<TrackSongModel.TrackSong>) getbundel.get("LSong") != null) {
                Fist_Play();

            } else {
                setAttAfter(listsong.get(vitri).getSongImage(), listsong.get(vitri).getSongName(), listsong.get(vitri).getArtistName());
            }
            ClickEvent();
            showlistplay();
            setListtenInfor();

        }

        return mview;
    }


    //--------------------------------------------code----------------------------------//
    //==========FistPlay=======================//
    public void Fist_Play(){
        String SongID = (String) getbundel.get("idSong");
        listsong = (ArrayList<TrackSongModel.TrackSong>) getbundel.get("LSong");
        Log.v("LOOG", getbundel.toString());
        StopMusic();
        int index =0;
        if(listsong.size()>0){
            for (TrackSongModel.TrackSong data : listsong) {
                if(SongID.equals(data.getSongId()))
                {
                    vitri = index;
                    new Sub_Play.PlaySong().execute(data.getSongLocation());
                    Play_Pause.setImageResource(R.drawable.pause_24px);
                    setAtt(data.getSongImage(),data.getSongName(),data.getArtistName());
                }
                index++;
            }
        }
        this.getArguments().clear();
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
                    Toast.makeText(getActivity(), "Repeat is on", Toast.LENGTH_SHORT).show();
                    repeatsong=true;
                }else {
                    Loop.setColorFilter(Color.WHITE);
                    Toast.makeText(getActivity(), "Repeat is off", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), "Random is on", Toast.LENGTH_SHORT).show();
            }else {
                Random.setColorFilter(Color.WHITE);
                randomsong=false;
                Toast.makeText(getActivity(), "Random is off", Toast.LENGTH_SHORT).show();
            }
        });

        NextSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listsong.size() >0 ){
                    StopMusic();
                    if(vitri < (listsong.size())){
                        Play_Pause.setImageResource(R.drawable.pause_24px);
                        vitri+=1;
                        if(randomsong == true){
                            java.util.Random random = new Random();
                            int i = random.nextInt(listsong.size());
                            if(i == vitri){
                                vitri = i-1;
                            }
                            vitri = i;
                        }
                        if(vitri > (listsong.size()-1)){
                            vitri=0;
                        }
                        new Sub_Play.PlaySong().execute(listsong.get(vitri).getSongLocation());
                        setAtt(listsong.get(vitri).getSongImage(),listsong.get(vitri).getSongName(),listsong.get(vitri).getArtistName());
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
                        vitri -=1;
                        if(vitri <0){
                            vitri = listsong.size()-1;
                        }

                        if(randomsong == true){
                            Random random = new Random();
                            int i = random.nextInt(listsong.size());
                            if(i == vitri){
                                vitri = i-1;
                            }
                            vitri = i;
                        }

                        new Sub_Play.PlaySong().execute(listsong.get(vitri).getSongLocation());
                        setAtt(listsong.get(vitri).getSongImage(),listsong.get(vitri).getSongName(),listsong.get(vitri).getArtistName());
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

    public class  PlaySong extends AsyncTask<String,Void,String> {

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
           // mediaPlayer.seekTo(length);
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
                        vitri+=1;
                        if (repeatsong == true) {
                            if (vitri <= 0) {
                                vitri = listsong.size();
                            }
                            vitri -= 1;

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

                        new Sub_Play.PlaySong().execute(listsong.get(vitri).getSongLocation());
                        setAtt(listsong.get(vitri).getSongImage(),listsong.get(vitri).getSongName(),listsong.get(vitri).getArtistName());

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




    private void setAtt(String urlImage, String titleSong,String Singersong){
        TitleSong.setText(titleSong);
        Singer.setText(Singersong);
        Picasso.get().load(urlImage).into(ImageSong);
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("mm:ss");
        Loop.setColorFilter(  repeatsong ==true? Color.RED: Color.WHITE );
        Random.setColorFilter(randomsong ==true? Color.RED: Color.WHITE );
    }
    private void setAttAfter(String urlImage, String titleSong,String Singersong){
        Loop.setColorFilter(  repeatsong ==true? Color.RED: Color.WHITE );
        Random.setColorFilter(randomsong ==true? Color.RED: Color.WHITE );
        Play_Pause.setImageResource(mediaPlayer.isPlaying()?R.drawable.pause_24px:R.drawable.play_24px);
        TitleSong.setText(titleSong);
        Singer.setText(Singersong);
        Picasso.get().load(urlImage).into(ImageSong);
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("mm:ss");
           DurationSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
           RuntimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
           SeekbarSong.setMax(mediaPlayer.getDuration());
           mediaPlayer.seekTo(mediaPlayer.getCurrentPosition());
    }

    private void TimeSong() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        RuntimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        SeekbarSong.setMax(mediaPlayer.getDuration());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    public void setListtenInfor(){
        InforArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Methods methods =getRetrofit().create(Methods.class);
                Call<Get_Infor_Artist_Model> call = methods.GetInforArtist(listsong.get(vitri).getSongId());
                call.enqueue(new Callback<Get_Infor_Artist_Model>() {
                    @Override
                    public void onResponse(Call<Get_Infor_Artist_Model> call, Response<Get_Infor_Artist_Model> response) {
                        if(response.isSuccessful()){
                            Get_Infor_Artist_Model.Infor_Artist art = response.body().getData();
                            ArtistName.setText("Name:  "+art.getArtistName());
                            ArtistNational.setText("National:  "+art.getArtistCountry());
                            Picasso.get().load(art.getArtistImage()).into(ArtistImage);
                            dialog.show();
                        }else {
                            Log.v("TestDialog","false");
                        }
                    }

                    @Override
                    public void onFailure(Call<Get_Infor_Artist_Model> call, Throwable t) {

                    }
                });
            }
        });

    }
    public void showlistplay(){
        PlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).OpenDetail(1,listsong);
            }
        });
    }

}
