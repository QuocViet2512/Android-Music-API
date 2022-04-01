namespace MUSICAPI.Entity
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Album")]
    public partial class Album
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Album()
        {
            TrackSongs = new HashSet<TrackSong>();
        }

        public int AlbumId { get; set; }

        [StringLength(255)]
        public string AlbumName { get; set; }

        [StringLength(255)]
        public string AlbumImage { get; set; }

        public DateTime? ALbumDate { get; set; }

        public int? GenreId { get; set; }

        public int? ArtistId { get; set; }

        public virtual Artist Artist { get; set; }

        public virtual Genre Genre { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<TrackSong> TrackSongs { get; set; }
    }
}
