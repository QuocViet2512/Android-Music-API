package com.example.musicappandroi;

import static com.example.lib.RetrofitClient.getRetrofit;

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
import com.example.lib.Models.PlayListModel;
import com.example.lib.Models.TrackSongModel;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Method;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Extend_Sub_Playlist extends RecyclerView.Adapter<Extend_Sub_Playlist.MyViewHolder> {
    public List<PlayListModel.Playlist> LPL;
    DetailListSong detail;
    public Extend_Sub_Playlist(List<PlayListModel.Playlist> pl,DetailListSong dt){
        this.detail=dt;
        this.LPL=pl;
        Log.v("RP2", String.valueOf(LPL.size()));
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.extend_sub_playlist_pl,parent,false);
        return new Extend_Sub_Playlist.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int index = position;
        holder.PL_Name.setText(LPL.get(position).getPlaylistName());
        Picasso.get().load(LPL.get(position).getPlaylistImage()).into(holder.PL_Image);
        holder.PL_Items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Methods methods =getRetrofit().create(Methods.class);
                Call<TrackSongModel> call = methods.GetSongByPlaylist(LPL.get(index).getPlaylistId());
                call.enqueue(new Callback<TrackSongModel>() {
                    @Override
                    public void onResponse(Call<TrackSongModel> call, Response<TrackSongModel> response) {
                        if(response.isSuccessful()){

                            String a = MainActivity.getUser()==null? "_":MainActivity.getUser().getUserId();
                            detail.DetailCate(1,response.body().getData());
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
        return LPL.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView PL_Image;
        TextView PL_Name;
        LinearLayout PL_Items;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            PL_Items = itemView.findViewById(R.id.PL_Items);
            PL_Name = itemView.findViewById(R.id.PL_Name);
            PL_Image = itemView.findViewById(R.id.PL_Image);
        }
    }
}
