package com.example.musicappandroi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lib.Models.TrackSongModel;

import java.util.ArrayList;
import java.util.List;

public class Extend_Sub_Find_List_Song extends RecyclerView.Adapter<Extend_Sub_Find_List_Song.MyViewHolder> {
    List<TrackSongModel.TrackSong> ListSong;
    Context context;
   SendObj Sendplay;

    public Extend_Sub_Find_List_Song(Context context,List<TrackSongModel.TrackSong>Songs,SendObj send){
        this.ListSong = Songs;
        this.context = context;
        this.Sendplay = send;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.extend_sub_find_list_song,parent,false);
        return new Extend_Sub_Find_List_Song.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int Index = position;
        holder.Items_Find_Song_Name.setText(ListSong.get(position).getSongName());
        holder.Items_Find_Song_Artist.setText(ListSong.get(position).getArtistName());
        holder.Items_Find_Song_Items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idSong = ListSong.get(Index).getSongId();
                ArrayList<TrackSongModel.TrackSong> LSong = (ArrayList<TrackSongModel.TrackSong>) ListSong;
                Sendplay.Myobject(idSong,LSong);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListSong.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Items_Find_Song_Name,Items_Find_Song_Artist;
        LinearLayout Items_Find_Song_Items;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Items_Find_Song_Name = itemView.findViewById(R.id.Items_Find_Song_Name);
            Items_Find_Song_Artist = itemView.findViewById(R.id.Items_Find_Song_Artist);
            Items_Find_Song_Items = itemView.findViewById(R.id.Items_Find_Song_Items);
        }
    }
}
