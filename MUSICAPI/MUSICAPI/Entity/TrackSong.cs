namespace MUSICAPI.Entity
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("TrackSong")]
    public partial class TrackSong
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public TrackSong()
        {
            DetailPlaylists = new HashSet<DetailPlaylist>();
        }

        [Key]
        public int SongId { get; set; }

        public int? ArtistId { get; set; }

        public int? AlbumId { get; set; }

        public int? GenreId { get; set; }

        [StringLength(100)]
        public string SongName { get; set; }

        public DateTime? SongDate { get; set; }

        public double? SongDuration { get; set; }

        [StringLength(255)]
        public string SongLocation { get; set; }

        [StringLength(255)]
        public string SongImage { get; set; }

        public virtual Album Album { get; set; }

        public virtual Artist Artist { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DetailPlaylist> DetailPlaylists { get; set; }

        public virtual Genre Genre { get; set; }
    }
}
