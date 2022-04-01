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
import com.example.lib.Models.ArtistModel;
import com.example.lib.Models.TrackSongModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List_Artist_Cate_Item extends RecyclerView.Adapter<List_Artist_Cate_Item.MyViewHolder> {
    List<ArtistModel.Artist> ListArtist;
    DetailListSong detai;
    public List_Artist_Cate_Item(List<ArtistModel.Artist> arts , DetailListSong dt) {
        this.ListArtist= arts;
        this.detai= dt;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_artist_cate_item, parent, false);
        return new List_Artist_Cate_Item.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int index = position;
        holder.Cate_Artist_Item_Name.setText(ListArtist.get(position).getArtistName());
        Picasso.get().load(ListArtist.get(position).getArtistImage()).into(holder.Cate_Artist_Item_Image);
        holder.Cate_Artist_Listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Methods methods =getRetrofit().create(Methods.class);
                Call<TrackSongModel> call = methods.GetSongByArt(ListArtist.get(index).getArtistId());
                call.enqueue(new Callback<TrackSongModel>() {
                    @Override
                    public void onResponse(Call<TrackSongModel> call, Response<TrackSongModel> response) {
                        if(response.isSuccessful()) {
                            detai.DetailCate(1, response.body().getData());
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
      return ListArtist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout Cate_Artist_Listen;
        ImageView Cate_Artist_Item_Image;
        TextView Cate_Artist_Item_Name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Cate_Artist_Item_Image = itemView.findViewById(R.id.Cate_Artist_Item_Image);
            Cate_Artist_Item_Name = itemView.findViewById(R.id.Cate_Artist_Item_Name);
            Cate_Artist_Listen = itemView.findViewById(R.id.Cate_Artist_Listen);
        }
    }
}