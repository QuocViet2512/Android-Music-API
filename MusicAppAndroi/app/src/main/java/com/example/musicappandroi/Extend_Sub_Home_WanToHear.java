package com.example.musicappandroi;

import static com.example.lib.RetrofitClient.getRetrofit;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lib.InterfaceResponsitory.Methods;
import com.example.lib.Models.GenreModel;
import com.example.lib.Models.TrackSongModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Extend_Sub_Home_WanToHear extends RecyclerView.Adapter<Extend_Sub_Home_WanToHear.MyViewHolder> {

    public List<TrackSongModel.TrackSong>ListWTH;
    SendObj sendplay;

    public Extend_Sub_Home_WanToHear(List<TrackSongModel.TrackSong> Songs, SendObj send) {
        this.ListWTH=Songs;
        this.sendplay = send;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.extend_sub_home_wantohear,parent,false);
        return new Extend_Sub_Home_WanToHear.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int index = position;
        Picasso.get().load(ListWTH.get(position).getSongImage()).into(holder.ImgWTH);
        holder.NameWTH.setText(ListWTH.get(position).getSongName());
        holder.Items_SH_WTH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idSong = ListWTH.get(index).getSongId();
                ArrayList<TrackSongModel.TrackSong>Lsong = (ArrayList<TrackSongModel.TrackSong>) ListWTH;
                sendplay.Myobject(idSong,Lsong);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListWTH.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ImgWTH;
        public  TextView NameWTH;
        LinearLayout Items_SH_WTH;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ImgWTH = itemView.findViewById(R.id.ImageWTH);
            NameWTH= itemView.findViewById(R.id.NameWTH);
            Items_SH_WTH = itemView.findViewById(R.id.Items_SH_WTH);
        }

    }
}
