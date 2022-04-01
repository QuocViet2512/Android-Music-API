package com.example.musicappandroi;

import static com.example.lib.RetrofitClient.getRetrofit;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lib.InterfaceResponsitory.Methods;
import com.example.lib.Models.GenreModel;
import com.example.lib.Models.TrackSongModel;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Extend_Sub_Home_Genre extends RecyclerView.Adapter<Extend_Sub_Home_Genre.MyViewHolder> {
public List<GenreModel.Genre> ListGenre;
DetailListSong detail;
    public Extend_Sub_Home_Genre(List<GenreModel.Genre> Genres,DetailListSong dt) {
        this.ListGenre=Genres;
        this.detail = dt;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.extend_sub_home_genre,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int index = position;
        Picasso.get().load(ListGenre.get(position).getGenreImage()).into(holder.ImgGenre);
        holder.NameGenre.setText(ListGenre.get(position).getGenreName());
        holder.Items_SH_Genre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Methods methods =getRetrofit().create(Methods.class);
                Call<TrackSongModel> call = methods.GetSongByGenre(ListGenre.get(index).getGenreId());
                call.enqueue(new Callback<TrackSongModel>() {
                    @Override
                    public void onResponse(Call<TrackSongModel> call, Response<TrackSongModel> response) {
                        if(response.isSuccessful()) {
                            detail.DetailCate(1, response.body().getData());
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
        return ListGenre.size();
    }

  class MyViewHolder extends RecyclerView.ViewHolder {
       public ImageView ImgGenre;
       public  TextView NameGenre;
       LinearLayout Items_SH_Genre;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ImgGenre = itemView.findViewById(R.id.ImageGenre);
            NameGenre = itemView.findViewById(R.id.NameGenre);
            Items_SH_Genre = itemView.findViewById(R.id.Items_SH_Genre);
        }

    }


}
