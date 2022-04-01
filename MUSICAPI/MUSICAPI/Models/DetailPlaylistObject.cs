using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MUSICAPI.Models
{
    public class DetailPlaylistObject
    {
        public int? DetailPlaylistID { get; set; }

        public string PlaylistName { get; set; }

        public string SongName { get; set; }
    }
}