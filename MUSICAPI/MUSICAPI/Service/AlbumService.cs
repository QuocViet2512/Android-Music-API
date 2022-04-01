using MUSICAPI.Entity;
using MUSICAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MUSICAPI.Service
{
    public class AlbumService
    {
        DB data = new DB();
        public List<AlbumObject> getAlbumList()
        {
            var Albums = data.Albums.ToList();
            var LAOBJ = new List<AlbumObject>();
            foreach(Album item in Albums)
            {
                AlbumObject obj = new AlbumObject();
                obj.AlbumId = item.AlbumId;
                obj.AlbumImage = item.AlbumImage;
                obj.AlbumName = item.AlbumName;
                obj.ALbumDate = item.ALbumDate.Value.ToShortDateString();
                obj.ArtistName = item.ArtistId == null ? "Unknown" : item.Artist.ArtistName;
                obj.GenreName = item.GenreId == null ? "Unknown" : item.Genre.GenreName;
                LAOBJ.Add(obj);
    
            }

            return LAOBJ; 
        }
    }
}