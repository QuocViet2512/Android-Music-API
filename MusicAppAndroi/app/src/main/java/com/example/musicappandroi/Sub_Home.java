package com.example.musicappandroi;

import static com.example.lib.RetrofitClient.getRetrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lib.InterfaceResponsitory.Methods;
import com.example.lib.Models.AlbumModel;
import com.example.lib.Models.GenreModel;
import com.example.lib.Models.TrackSongModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Sub_Home extends androidx.fragment.app.Fragment {
    private View view;

    //--------------------------------------------------//
    public  List<GenreModel.Genre> LGenres;
    public  List<TrackSongModel.TrackSong> LWTHs;
    public  List<AlbumModel.Album> LAlbums;
    private RecyclerView recyclerView_Genres;
    private RecyclerView recyclerView_WTH;
    private RecyclerView recyclerView_Albums;
    private RecyclerView recyclerView_Songs;


    //----------------------------------------------------//



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.sub_home,container,false);

       recyclerView_Genres = view.findViewById(R.id.RC_ViewGenre);
       recyclerView_WTH = view.findViewById(R.id.RC_ViewWTH);
       recyclerView_Albums = view.findViewById(R.id.RC_ViewAlbum);
       recyclerView_Songs = view.findViewById(R.id.RC_ViewSongs);
//new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false)
       recyclerView_WTH.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
       recyclerView_Genres.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
       recyclerView_Albums.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
       recyclerView_Songs.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        Thread t1 = new Thread(this::GetGenre);
        Thread t2 = new Thread(this::SongsWantToHear);
        Thread t3 = new Thread(this::GetAlbum);
        t1.start();
        t2.start();
        t3.start();
        return view;
    }


    //-------------------------Asynchoinous----------------//
    public void SongsWantToHear() {
        Methods methods =getRetrofit().create(Methods.class);
        Call<TrackSongModel> call = methods.GetTrackSong();
        call.enqueue(new Callback<TrackSongModel>() {
            @Override
            public void onResponse(Call<TrackSongModel> call, Response<TrackSongModel> response) {
                if(response.isSuccessful()){
                    LWTHs = response.body().getData();
                    recyclerView_WTH.setAdapter(new Extend_Sub_Home_WanToHear(LWTHs, new SendObj() {
                        @Override
                        public void Myobject(String idSong, ArrayList<TrackSongModel.TrackSong> ListSong) {
                            ((MainActivity) getActivity()).Play_A_Song(idSong,ListSong);
                        }
                    }));
                    recyclerView_Songs.setAdapter(new Extend_Sub_Home_Song_Items((MainActivity) getActivity(), LWTHs, new SendObj() {
                        @Override
                        public void Myobject(String idSong, ArrayList<TrackSongModel.TrackSong> ListSong) {
                          ((MainActivity) getActivity()).Play_A_Song(idSong,ListSong);

                        }
                    }));
                }
                else {return;}
            }
            @Override
            public void onFailure(Call<TrackSongModel> call, Throwable t) {
            }
        });
    }

    public void GetGenre() {
        Methods methods =getRetrofit().create(Methods.class);
        Call<GenreModel> call = methods.getGenre();
       call.enqueue(new Callback<GenreModel>() {
           @Override
           public void onResponse(Call<GenreModel> call, Response<GenreModel> response) {
               if(response.isSuccessful()){
                   LGenres = response.body().getData();
                   recyclerView_Genres.setAdapter(new Extend_Sub_Home_Genre(LGenres, new DetailListSong() {
                       @Override
                       public void DetailCate(int Option, List<TrackSongModel.TrackSong> Songs) {
                           ((MainActivity) getActivity()).OpenDetail(1,Songs);
                       }
                   }));
               }
           }
           @Override
           public void onFailure(Call<GenreModel> call, Throwable t) {
           }
       });
    }

    public void GetAlbum(){
        Methods methods =getRetrofit().create(Methods.class);
        Call<AlbumModel> call = methods.getAlbum();
        call.enqueue(new Callback<AlbumModel>() {
            @Override
            public void onResponse(Call<AlbumModel> call, Response<AlbumModel> response) {
                if(response.isSuccessful()){
                    LAlbums = response.body().getData();
                    recyclerView_Albums.setAdapter(new Extend_Sub_Home_Album(LAlbums, new DetailListSong() {
                        @Override
                        public void DetailCate(int Option, List<TrackSongModel.TrackSong> Songs) {
                            ((MainActivity) getActivity()).OpenDetail(Option,Songs);
                        }
                    }));
                }
            }
            @Override
            public void onFailure(Call<AlbumModel> call, Throwable t) {
            }
        });
    }
}
