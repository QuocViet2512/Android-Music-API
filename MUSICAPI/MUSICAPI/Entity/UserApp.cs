namespace MUSICAPI.Entity
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("UserApp")]
    public partial class UserApp
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public UserApp()
        {
            Playlists = new HashSet<Playlist>();
        }

        [Key]
        public Guid UserId { get; set; }

        [StringLength(18)]
        public string UserPhone { get; set; }

        [StringLength(50)]
        public string UserPassword { get; set; }

        [StringLength(100)]
        public string UserName { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Playlist> Playlists { get; set; }
    }
}
