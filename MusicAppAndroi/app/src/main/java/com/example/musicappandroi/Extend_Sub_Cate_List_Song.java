package com.example.musicappandroi;

import static com.example.lib.RetrofitClient.getRetrofit;


import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lib.InterfaceResponsitory.Methods;
import com.example.lib.Models.ArtistModel;
import com.example.lib.Models.TrackSongModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Extend_Sub_Cate_List_Song extends Fragment {



    /// Khai bao
    RecyclerView RC_ListSong_Cate;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =inflater.inflate(R.layout.extend_sub_cate_list_song, container, false);
        RC_ListSong_Cate = mView.findViewById(R.id.RC_ListSong_Cate);
        RC_ListSong_Cate.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
        GetData_SongCate();
        return mView;
    }
    public void GetData_SongCate(){
        Methods methods =getRetrofit().create(Methods.class);
        Call<TrackSongModel> call = methods.GetTrackSong();
        call.enqueue(new Callback<TrackSongModel>() {
            @Override
            public void onResponse(Call<TrackSongModel> call, Response<TrackSongModel> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    RC_ListSong_Cate.setAdapter(new List_Song_Cate_Item(response.body().getData(), new SendObj() {
                        @Override
                        public void Myobject(String idSong, ArrayList<TrackSongModel.TrackSong> ListSong) {
                            ((MainActivity)getActivity()).Play_A_Song(idSong,ListSong);
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<TrackSongModel> call, Throwable t) {

            }
        });
    }

}