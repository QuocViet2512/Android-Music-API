using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MUSICAPI.Models
{
    public class AstistObject
    {
        public int? ArtistId { get; set; }

        public string ArtistName { get; set; }

        public string ArtistCountry { get; set; }

        public string ArtistImage { get; set; }
    }
}