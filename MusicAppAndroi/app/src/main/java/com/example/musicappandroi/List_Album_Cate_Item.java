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

public class List_Album_Cate_Item extends RecyclerView.Adapter<List_Album_Cate_Item.MyViewHolder> {
List<AlbumModel.Album>ListAlbum;
DetailListSong detail;
public List_Album_Cate_Item(List<AlbumModel.Album> albums , DetailListSong dt){
    this.ListAlbum =albums;
    this.detail = dt;
}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_album_cate_item,parent,false);
        return new List_Album_Cate_Item.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    final int index = position;
    holder.Cate_Album_Item_Name.setText(ListAlbum.get(position).getAlbumName());
        Picasso.get().load(ListAlbum.get(position).getAlbumImage()).into(holder.Cate_Album_Item_Image);
        holder.Cate_Album_Listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Methods methods =getRetrofit().create(Methods.class);
                Call<TrackSongModel> call = methods.GetSongByAlbum(ListAlbum.get(index).getAlbumId());
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
       return ListAlbum.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
LinearLayout Cate_Album_Listen;
ImageView Cate_Album_Item_Image;
TextView Cate_Album_Item_Name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Cate_Album_Item_Image = itemView.findViewById(R.id.Cate_Album_Item_Image);
            Cate_Album_Item_Name = itemView.findViewById(R.id.Cate_Album_Item_Name);
            Cate_Album_Listen = itemView.findViewById(R.id.Cate_Album_Listen);
        }
    }
}
