package com.example.musicappandroi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lib.Models.TrackSongModel;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Extend_Sub_Home_Song_Items extends RecyclerView.Adapter<Extend_Sub_Home_Song_Items.MyViewHolder> {
    List<TrackSongModel.TrackSong> SongsList;
    Context context;


    public SendObj isendobj;


    public Extend_Sub_Home_Song_Items(Context context ,List<TrackSongModel.TrackSong>Songs,SendObj obj){
        this.SongsList = Songs;
        this.context = context;
        this.isendobj = obj;
    }
    @NonNull
    @Override
    public Extend_Sub_Home_Song_Items.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.extend_sub_home_song_items,parent,false);
        return new Extend_Sub_Home_Song_Items.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int Index = position;
        Picasso.get().load(SongsList.get(position).getSongImage()).into(holder.SongImage);
        holder.SongName.setText(SongsList.get(position).getSongName());
        holder.Singer.setText("Singer: "+SongsList.get(position).getArtistName());
        holder.Duration.setText("Duration: "+SongsList.get(position).getSongDuration());
        holder.PublicDate.setText("Publish: "+SongsList.get(position).getSongDate());
        holder.Album.setText("Album: "+SongsList.get(position).getAlbumName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idSong = SongsList.get(Index).getSongId();
                ArrayList<TrackSongModel.TrackSong> Lsongs = (ArrayList<TrackSongModel.TrackSong>) SongsList;
                isendobj.Myobject(idSong,Lsongs);


              /*  Intent intent = new Intent(context,Layout_Playing.class);
                Bundle bundle = new Bundle();
                TrackSongModel.TrackSong song = SongsList.get(Index);
                bundle.putSerializable("Song",song);
                ArrayList<TrackSongModel.TrackSong> Lsongs = (ArrayList<TrackSongModel.TrackSong>) SongsList;
                Log.v("PUTABC", String.valueOf(Lsongs.size()));
                bundle.putSerializable("songlist",Lsongs);
                bundle.putString("idSong",SongsList.get(Index).getSongId());
                intent.putExtra("SongBundle",bundle);
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return SongsList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView SongImage;
        LinearLayout MoreInformationClick,ItemSongClick;
        TextView Singer,SongName,Duration,PublicDate,Album;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            SongImage = itemView.findViewById(R.id.SongImage);
            SongName = itemView.findViewById(R.id.SongName);
            Singer = itemView.findViewById(R.id.Singer);
            Duration = itemView.findViewById(R.id.Duration);
            PublicDate = itemView.findViewById(R.id.PublicDate);
            MoreInformationClick = itemView.findViewById(R.id.MoreInformationClick);
            ItemSongClick = itemView.findViewById(R.id.ItemSongClick);
            Album = itemView.findViewById(R.id.Album);

        }
    }
}
