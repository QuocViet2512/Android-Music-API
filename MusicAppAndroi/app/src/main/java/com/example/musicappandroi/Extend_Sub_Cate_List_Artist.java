package com.example.musicappandroi;

import static com.example.lib.RetrofitClient.getRetrofit;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.lib.InterfaceResponsitory.Methods;
import com.example.lib.Models.ArtistModel;
import com.example.lib.Models.TrackSongModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Extend_Sub_Cate_List_Artist extends Fragment {


    /// Khai bao
    RecyclerView RC_ListArtist_Cate;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.extend_sub_cate_list_artist, container, false);
        RC_ListArtist_Cate = mView.findViewById(R.id.RC_ListArtist_Cate);
        RC_ListArtist_Cate.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
        GetData_ArtistCate();
        return mView;
    }


    public void GetData_ArtistCate(){
        Methods methods =getRetrofit().create(Methods.class);
        Call<ArtistModel> call = methods.getArtist();
        call.enqueue(new Callback<ArtistModel>() {
            @Override
            public void onResponse(Call<ArtistModel> call, Response<ArtistModel> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    RC_ListArtist_Cate.setAdapter(new List_Artist_Cate_Item(response.body().getData(),new DetailListSong() {
                        @Override
                        public void DetailCate(int Option, List<TrackSongModel.TrackSong> Songs) {
                            ((MainActivity)getActivity()).OpenDetail(Option,Songs);
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<ArtistModel> call, Throwable t) {

            }
        });
    }
}