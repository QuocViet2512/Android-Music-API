using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MUSICAPI.Models
{
    public class PlayListObject
    {
        public int PlaylistId { get; set; }

        
        public string PlaylistName { get; set; }


        public string PlaylistImage { get; set; }

        public string PlaylistIcon { get; set; }

        public string  UserName { get; set; }
    }
}