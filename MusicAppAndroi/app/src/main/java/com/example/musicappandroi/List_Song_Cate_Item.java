package com.example.musicappandroi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lib.Models.TrackSongModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class List_Song_Cate_Item extends RecyclerView.Adapter<List_Song_Cate_Item.MyViewHolder> {
List<TrackSongModel.TrackSong>ListSongCate;
SendObj sendplay;
public List_Song_Cate_Item(List<TrackSongModel.TrackSong> songs , SendObj send){
    this.ListSongCate =songs;
    this.sendplay = send;
}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_song_cate_item,parent,false);
        return new List_Song_Cate_Item.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    final int index = position;
    holder.Cate_Song_Item_Name.setText(ListSongCate.get(position).getSongName());
        Picasso.get().load(ListSongCate.get(position).getSongImage()).into(holder.Cate_Song_Item_Image);
        holder.Cate_Song_Listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idSong = ListSongCate.get(index).getSongId();
                ArrayList<TrackSongModel.TrackSong> LSong = (ArrayList<TrackSongModel.TrackSong>) ListSongCate;
                sendplay.Myobject(idSong,LSong);
            }
        });

    }

    @Override
    public int getItemCount() {
       return ListSongCate.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
LinearLayout Cate_Song_Listen;
ImageView Cate_Song_Item_Image;
TextView Cate_Song_Item_Name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Cate_Song_Item_Image = itemView.findViewById(R.id.Cate_Song_Item_Image);
            Cate_Song_Item_Name = itemView.findViewById(R.id.Cate_Song_Item_Name);
            Cate_Song_Listen = itemView.findViewById(R.id.Cate_Song_Listen);
        }
    }
}
