package com.example.musicappandroi;

import static com.example.lib.RetrofitClient.getRetrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lib.InterfaceResponsitory.Methods;
import com.example.lib.Models.PlayListModel;
import com.example.lib.Models.TrackSongModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sub_Playlist extends Fragment {
    RecyclerView RC_PL;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.sub_playlist,container,false);
        RC_PL= view.findViewById(R.id.RC_PL);
        RC_PL.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
        getdataPlaylist();
        return view;
    }
    public void getdataPlaylist(){
        String user = MainActivity.getUser()==null?"":MainActivity.getUser().getUserId();
        Methods methods =getRetrofit().create(Methods.class);
        Call<PlayListModel> call = methods.GetPlaylistByUserID(user);
        call.enqueue(new Callback<PlayListModel>() {
            @Override
            public void onResponse(Call<PlayListModel> call, Response<PlayListModel> response) {
                if(response.isSuccessful()){
                    PlayListModel md = response.body();
                    RC_PL.setAdapter(new Extend_Sub_Playlist(md.getData(), new DetailListSong() {
                        @Override
                        public void DetailCate(int Option, List<TrackSongModel.TrackSong> Songs) {
                            ((MainActivity)getActivity()).OpenDetail(Option,Songs);
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<PlayListModel> call, Throwable t) {

            }
        });
    }
}
