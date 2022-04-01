using MUSICAPI.Entity;
using MUSICAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MUSICAPI.Service
{
    public class GenreService
    {
        DB data = new DB();
        public List<GenreObject> getListGenre()
        {
            List<Genre> genre = data.Genres.ToList();
            List<GenreObject> GOBJ = new List<GenreObject>();
            foreach (Genre item in genre)
            {
                GenreObject gbjs = new GenreObject();
                gbjs.GenreId = item.GenreId;
                gbjs.GenreName = item.GenreName;
                gbjs.GenreImage = item.GenreImage;
                gbjs.GenreDescription = item.GenreDescription;
                GOBJ.Add(gbjs);

            }
            return GOBJ;
        }
    }
}