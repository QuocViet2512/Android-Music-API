using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MUSICAPI.Models
{
    public class GenreObject
    {
        public int? GenreId { get; set; }

        public string GenreName { get; set; }

        public string GenreDescription { get; set; }

        public string GenreImage { get; set; }
    }
}