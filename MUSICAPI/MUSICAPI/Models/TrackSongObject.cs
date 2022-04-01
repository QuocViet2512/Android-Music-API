using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MUSICAPI.Models
{
    public class TrackSongObject
    {

        public int? SongId { get; set; }

        public string ArtistName { get; set; }

        public string AlbumName { get; set; }

        public string GenreName { get; set; }

        public string SongName { get; set; }

        public string SongDate { get; set; }

        public double? SongDuration { get; set; }

        public string SongLocation { get; set; }

        public string SongImage { get; set; }
    }
}