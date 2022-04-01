using MUSICAPI.Entity;
using MUSICAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MUSICAPI.Service
{
    public class DetailPlaylistService
    {
        DB data = new DB();
        public List<DetailPlaylistObject> getListDetailPlaylist()
        {
            List<DetailPlaylist> detailPL = data.DetailPlaylists.ToList();
            List<DetailPlaylistObject> DPLOBJ = new List<DetailPlaylistObject>();
            foreach (DetailPlaylist item in detailPL)
            {
                DetailPlaylistObject dplbjs = new DetailPlaylistObject();
                dplbjs.DetailPlaylistID = item.DetailPlaylistID;
                dplbjs.PlaylistName = item.PlaylistId == null ? "Unknown" : item.Playlist.PlaylistName;
                dplbjs.SongName = item.SongId == null ? "Unknown" : item.TrackSong.SongName;
                DPLOBJ.Add(dplbjs);

            }
            return DPLOBJ;
        }
    }
}