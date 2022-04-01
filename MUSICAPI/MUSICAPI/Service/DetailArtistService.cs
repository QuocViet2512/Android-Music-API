using MUSICAPI.Entity;
using MUSICAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MUSICAPI.Service
{
    public class DetailArtistService
    {
        DB data = new DB();
        public List<DetailArtistObject> getListDetailArtist()
        {
            List<DetailArtist> detailAt = data.DetailArtists.ToList();
            List<DetailArtistObject> DAOBJ = new List<DetailArtistObject>();
            foreach (DetailArtist item in detailAt)
            {
                DetailArtistObject dabjs = new DetailArtistObject();
                dabjs.DetailArtistId = item.DetailArtistId;
                dabjs.ArtistName = item.ArtistId == null ? "Unknown" : item.Artist.ArtistName;
                dabjs.GenreName = item.GenreId == null ? "Unknown" : item.Genre.GenreName;
                DAOBJ.Add(dabjs);

            }
            return DAOBJ;
        }
    }
}