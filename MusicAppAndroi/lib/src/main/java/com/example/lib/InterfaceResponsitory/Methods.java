package com.example.lib.InterfaceResponsitory;



import com.example.lib.Models.AlbumModel;
import com.example.lib.Models.ArtistModel;
import com.example.lib.Models.GenreModel;
import com.example.lib.Models.Get_Infor_Artist_Model;
import com.example.lib.Models.LoginModel;
import com.example.lib.Models.PlayListModel;
import com.example.lib.Models.SignUpModel;
import com.example.lib.Models.SignUpResultModel;
import com.example.lib.Models.TrackSongModel;
import com.example.lib.Models.UserApp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Methods {
@GET("api/tracksongapi/getsonglist")
    Call<TrackSongModel> GetTrackSong();
@GET("api/genreapi/getgenrelist")
    Call<GenreModel> getGenre();
@GET("api/albumapi/getalbumlist")
    Call<AlbumModel> getAlbum();
@GET("api/artistapi/getartistlist")
    Call<ArtistModel> getArtist();
@POST("api/ArtistAPI/getArtInforBySongID")
    Call<Get_Infor_Artist_Model> GetInforArtist(@Query("songid") String id);
@POST("api/tracksongAPI/searchsong")
    Call<TrackSongModel> SearchTrackSong(@Query("songname") String songname);
@POST("api/tracksongapi/ListSongByAlbumID")
    Call<TrackSongModel> GetSongByAlbum(@Query("albumid") String albumid );
@POST("api/tracksongapi/ListSongByArtistID")
    Call<TrackSongModel> GetSongByArt(@Query("artistid") String artistid );
@POST("api/tracksongapi/ListSongByGenreID")
    Call<TrackSongModel> GetSongByGenre(@Query("genreid") String genreid);
@POST("api/UserAppAPI/Login")
    Call<UserApp> Login(@Body LoginModel login);
@POST("api/UserAppAPI/SingUp")
    Call<SignUpResultModel> SignUp(@Body SignUpModel sign);
@GET("api/playlistAPI/getPlayListByUserID")
    Call<PlayListModel> GetPlaylistByUserID(@Query("id") String user);
@POST("api/tracksongapi/ListSongByPlaylistID")
    Call<TrackSongModel> GetSongByPlaylist(@Query("playlistid") String plid);
}
