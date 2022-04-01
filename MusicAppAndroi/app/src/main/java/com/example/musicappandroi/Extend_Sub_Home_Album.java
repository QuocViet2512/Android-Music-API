package com.example.musicappandroi;

import static com.example.lib.RetrofitClient.getRetrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lib.InterfaceResponsitory.Methods;
import com.example.lib.Models.AlbumModel;
import com.example.lib.Models.TrackSongModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Extend_Sub_Home_Album extends RecyclerView.Adapter<Extend_Sub_Home_Album.MyViewHolder> {
    public List<AlbumModel.Album> ListAlbums;
    DetailListSong detail;
    public Extend_Sub_Home_Album(List<AlbumModel.Album> Albums,DetailListSong dt){
        this.ListAlbums = Albums;
        this.detail=dt;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.extend_sub_home_album,parent,false);
        return new Extend_Sub_Home_Album.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int index = position;
        Picasso.get().load(ListAlbums.get(position).getAlbumImage()).into(holder.ImgAlbum);
        holder.NameAlbum.setText(ListAlbums.get(position).getAlbumName());
        holder.Items_SH_Album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Methods methods =getRetrofit().create(Methods.class);
                Call<TrackSongModel> call = methods.GetSongByAlbum(ListAlbums.get(index).getAlbumId());
                call.enqueue(new Callback<TrackSongModel>() {
                    @Override
                    public void onResponse(Call<TrackSongModel> call, Response<TrackSongModel> response) {
                        if(response.isSuccessful()){
                            List<TrackSongModel.TrackSong> Lsong = response.body().getData();
                            detail.DetailCate(1,Lsong);
                        }
                    }

                    @Override
                    public void onFailure(Call<TrackSongModel> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListAlbums.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ImgAlbum;
        TextView NameAlbum;
        LinearLayout Items_SH_Album;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ImgAlbum = itemView.findViewById(R.id.ImageAlbum);
            NameAlbum = itemView.findViewById(R.id.NameAlbum);
            Items_SH_Album = itemView.findViewById(R.id.Items_SH_Album);
        }
    }
}
