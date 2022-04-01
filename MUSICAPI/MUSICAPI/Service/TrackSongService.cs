using MUSICAPI.Entity;
using MUSICAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MUSICAPI.Service
{
    public class TrackSongService
    {
        DB data = new DB();
        public List<TrackSongObject> getListTrackSong()
        {
            List<TrackSong> songs = data.TrackSongs.ToList();
            return ConvertListSong(songs);
        }


        public List<TrackSongObject> SearchSong(string songname)
        {
            var Find = data.TrackSongs.Where(p => p.SongName.Contains(songname)).ToList();
            return ConvertListSong(Find);
        }

        public List<TrackSongObject> ListSongByAlbumID(string AlbumID)
        {
            var Collect = data.TrackSongs.Where(p => p.AlbumId.ToString().Equals(AlbumID)).ToList();
            return ConvertListSong(Collect);
        }

        public List<TrackSongObject> ListSongByArtistID(string ArtistID)
        {
            var Collect = data.TrackSongs.Where(p => p.ArtistId.ToString().Equals(ArtistID)).ToList();
            return ConvertListSong(Collect);
        }

        public List<TrackSongObject> ListSongByGenreID(string GenreID)
        {
            var Collect = data.TrackSongs.Where(p => p.GenreId.ToString().Equals(GenreID)).ToList();
            return ConvertListSong(Collect);
        }

        public List<TrackSongObject> ListSongByPlaylistID(string PlaylistID)
        {
            var Collect = data.DetailPlaylists.Where(p => p.PlaylistId.ToString().Equals(PlaylistID)).ToList();
            var ListSong = new List<TrackSong>();
            foreach(DetailPlaylist item in Collect)
            {
                ListSong.Add(item.TrackSong);
            }

            return ConvertListSong(ListSong);
        }

        public List<TrackSongObject> ConvertListSong(List<TrackSong> songs)
        {
        
            List<TrackSongObject> SOBJ = new List<TrackSongObject>();
            foreach (TrackSong item in songs)
            {
                TrackSongObject objs = new TrackSongObject();
                objs.SongId = item.SongId;
                objs.SongImage = item.SongImage;
                objs.SongName = item.SongName;
                objs.SongLocation = item.SongLocation;
                objs.SongDuration = item.SongDuration;
                objs.SongDate = item.SongDate.Value.ToShortDateString();
                objs.AlbumName = item.AlbumId == null ? "Unknown" : item.Album.AlbumName;
                objs.ArtistName = item.ArtistId == null ? "Unknown" : item.Artist.ArtistName;
                objs.GenreName = item.GenreId == null ? "Unknown" : item.Genre.GenreName;
                SOBJ.Add(objs);

            }
            return SOBJ;
        }
    }
}