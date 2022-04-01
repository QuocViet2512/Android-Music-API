namespace MUSICAPI.Entity
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("DetailPlaylist")]
    public partial class DetailPlaylist
    {
        public int DetailPlaylistID { get; set; }

        public int? PlaylistId { get; set; }

        public int? SongId { get; set; }

        public virtual Playlist Playlist { get; set; }

        public virtual TrackSong TrackSong { get; set; }
    }
}
