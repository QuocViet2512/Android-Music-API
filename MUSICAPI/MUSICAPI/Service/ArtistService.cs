using MUSICAPI.Entity;
using MUSICAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MUSICAPI.Service
{
    public class ArtistService
    {
        DB data = new DB();
        public List<AstistObject> getListArtist()
        {
            List<Artist> artist = data.Artists.ToList();
            return ConvertListArtist(artist); 
        }

        public AstistObject getInforArtistBySongID(string SongID)
        {
            Artist ArtInfor = data.TrackSongs.FirstOrDefault(p => p.SongId.ToString().Equals(SongID)).Artist;
            AstistObject abjs = new AstistObject();
            abjs.ArtistId = ArtInfor.ArtistId;
            abjs.ArtistName = ArtInfor.ArtistName;
            abjs.ArtistImage = ArtInfor.ArtistImage;
            abjs.ArtistCountry = ArtInfor.ArtistCountry;
            return abjs;
        }

        public List<AstistObject> ConvertListArtist(List<Artist> arts)
        {
            List<AstistObject> AOBJ = new List<AstistObject>();
            foreach (Artist item in arts)
            {
                AstistObject abjs = new AstistObject();
                abjs.ArtistId = item.ArtistId;
                abjs.ArtistName = item.ArtistName;
                abjs.ArtistImage = item.ArtistImage;
                abjs.ArtistCountry = item.ArtistCountry;
                AOBJ.Add(abjs);
            }
            return AOBJ;
        }
    }
   
}