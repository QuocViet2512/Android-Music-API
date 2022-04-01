namespace MUSICAPI.Entity
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class DB : DbContext
    {
        public DB()
            : base("name=DB")
        {
        }

        public virtual DbSet<C__EFMigrationsHistory> C__EFMigrationsHistory { get; set; }
        public virtual DbSet<Album> Albums { get; set; }
        public virtual DbSet<Artist> Artists { get; set; }
        public virtual DbSet<AspNetRoleClaim> AspNetRoleClaims { get; set; }
        public virtual DbSet<AspNetRole> AspNetRoles { get; set; }
        public virtual DbSet<AspNetUserClaim> AspNetUserClaims { get; set; }
        public virtual DbSet<AspNetUserLogin> AspNetUserLogins { get; set; }
        public virtual DbSet<AspNetUser> AspNetUsers { get; set; }
        public virtual DbSet<AspNetUserToken> AspNetUserTokens { get; set; }
        public virtual DbSet<DetailArtist> DetailArtists { get; set; }
        public virtual DbSet<DetailPlaylist> DetailPlaylists { get; set; }
        public virtual DbSet<Genre> Genres { get; set; }
        public virtual DbSet<Playlist> Playlists { get; set; }
        public virtual DbSet<TrackSong> TrackSongs { get; set; }
        public virtual DbSet<UserApp> UserApps { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Album>()
                .HasMany(e => e.TrackSongs)
                .WithOptional(e => e.Album)
                .WillCascadeOnDelete();

            modelBuilder.Entity<Artist>()
                .HasMany(e => e.DetailArtists)
                .WithOptional(e => e.Artist)
                .WillCascadeOnDelete();

            modelBuilder.Entity<Artist>()
                .HasMany(e => e.TrackSongs)
                .WithOptional(e => e.Artist)
                .WillCascadeOnDelete();

            modelBuilder.Entity<AspNetRole>()
                .HasMany(e => e.AspNetRoleClaims)
                .WithRequired(e => e.AspNetRole)
                .HasForeignKey(e => e.RoleId);

            modelBuilder.Entity<AspNetRole>()
                .HasMany(e => e.AspNetUsers)
                .WithMany(e => e.AspNetRoles)
                .Map(m => m.ToTable("AspNetUserRoles").MapLeftKey("RoleId").MapRightKey("UserId"));

            modelBuilder.Entity<AspNetUser>()
                .HasMany(e => e.AspNetUserClaims)
                .WithRequired(e => e.AspNetUser)
                .HasForeignKey(e => e.UserId);

            modelBuilder.Entity<AspNetUser>()
                .HasMany(e => e.AspNetUserLogins)
                .WithRequired(e => e.AspNetUser)
                .HasForeignKey(e => e.UserId);

            modelBuilder.Entity<AspNetUser>()
                .HasMany(e => e.AspNetUserTokens)
                .WithRequired(e => e.AspNetUser)
                .HasForeignKey(e => e.UserId);

            modelBuilder.Entity<Genre>()
                .HasMany(e => e.DetailArtists)
                .WithOptional(e => e.Genre)
                .WillCascadeOnDelete();

            modelBuilder.Entity<Playlist>()
                .HasMany(e => e.DetailPlaylists)
                .WithOptional(e => e.Playlist)
                .WillCascadeOnDelete();

            modelBuilder.Entity<TrackSong>()
                .HasMany(e => e.DetailPlaylists)
                .WithOptional(e => e.TrackSong)
                .WillCascadeOnDelete();

            modelBuilder.Entity<UserApp>()
                .Property(e => e.UserPhone)
                .IsUnicode(false);

            modelBuilder.Entity<UserApp>()
                .Property(e => e.UserPassword)
                .IsUnicode(false);

            modelBuilder.Entity<UserApp>()
                .HasMany(e => e.Playlists)
                .WithOptional(e => e.UserApp)
                .WillCascadeOnDelete();
        }
    }
}
