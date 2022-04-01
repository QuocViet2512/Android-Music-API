namespace MUSICAPI.Entity
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Playlist")]
    public partial class Playlist
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Playlist()
        {
            DetailPlaylists = new HashSet<DetailPlaylist>();
        }

        public int PlaylistId { get; set; }

        [StringLength(100)]
        public string PlaylistName { get; set; }

        [StringLength(255)]
        public string PlaylistImage { get; set; }

        [StringLength(255)]
        public string PlaylistIcon { get; set; }

        public Guid? UserId { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DetailPlaylist> DetailPlaylists { get; set; }

        public virtual UserApp UserApp { get; set; }
    }
}
