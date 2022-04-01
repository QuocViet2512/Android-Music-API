package com.example.lib.Models;


import java.io.Serializable;
import java.util.List;

public class TrackSongModel implements Serializable
{

    private List<TrackSong> data;

    private String status;

    public List<TrackSong> getData ()
    {
        return data;
    }

    public void setData (List<TrackSong>  data)
    {
        this.data = data;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", status = "+status+"]";
    }
    public class TrackSong implements Serializable
    {
        private String SongId;

        private String SongDuration;

        private String GenreName;

        private String SongDate;

        private String AlbumName;

        private String ArtistName;

        private String SongLocation;

        private String SongName;

        private String SongImage;

        public String getSongId ()
        {
            return SongId;
        }

        public void setSongId (String SongId)
        {
            this.SongId = SongId;
        }

        public String getSongDuration ()
        {
            return SongDuration;
        }

        public void setSongDuration (String SongDuration)
        {
            this.SongDuration = SongDuration;
        }

        public String getGenreName ()
        {
            return GenreName;
        }

        public void setGenreName (String GenreName)
        {
            this.GenreName = GenreName;
        }

        public String getSongDate ()
        {
            return SongDate;
        }

        public void setSongDate (String SongDate)
        {
            this.SongDate = SongDate;
        }

        public String getAlbumName ()
        {
            return AlbumName;
        }

        public void setAlbumName (String AlbumName)
        {
            this.AlbumName = AlbumName;
        }

        public String getArtistName ()
        {
            return ArtistName;
        }

        public void setArtistName (String ArtistName)
        {
            this.ArtistName = ArtistName;
        }

        public String getSongLocation ()
        {
            return SongLocation;
        }

        public void setSongLocation (String SongLocation)
        {
            this.SongLocation = SongLocation;
        }

        public String getSongName ()
        {
            return SongName;
        }

        public void setSongName (String SongName)
        {
            this.SongName = SongName;
        }

        public String getSongImage ()
        {
            return SongImage;
        }

        public void setSongImage (String SongImage)
        {
            this.SongImage = SongImage;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [SongId = "+SongId+", SongDuration = "+SongDuration+", GenreName = "+GenreName+", SongDate = "+SongDate+", AlbumName = "+AlbumName+", ArtistName = "+ArtistName+", SongLocation = "+SongLocation+", SongName = "+SongName+", SongImage = "+SongImage+"]";
        }
    }
}
