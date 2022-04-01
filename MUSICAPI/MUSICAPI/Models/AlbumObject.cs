using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MUSICAPI.Models
{
    public class AlbumObject
    {
        public int AlbumId { get; set; }

     
        public string AlbumName { get; set; }
        public string AlbumImage { get; set; }

        public string ALbumDate { get; set; }

        public string GenreName { get; set; }

        public string ArtistName { get; set; }

    }
}