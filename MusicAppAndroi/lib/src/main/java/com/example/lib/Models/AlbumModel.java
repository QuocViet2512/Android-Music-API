package com.example.lib.Models;


import java.io.Serializable;
import java.util.List;

public class AlbumModel implements Serializable {
    private List<Album> data;

    private String status;

    public List<Album> getData ()
    {
        return data;
    }

    public void setData (List<Album> data)
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
    public class Album
    {
        private String AlbumImage;

        private String GenreName;

        private String AlbumId;

        private String AlbumName;

        private String ArtistName;

        private String ALbumDate;

        public String getAlbumImage ()
        {
            return AlbumImage;
        }

        public void setAlbumImage (String AlbumImage)
        {
            this.AlbumImage = AlbumImage;
        }

        public String getGenreName ()
        {
            return GenreName;
        }

        public void setGenreName (String GenreName)
        {
            this.GenreName = GenreName;
        }

        public String getAlbumId ()
        {
            return AlbumId;
        }

        public void setAlbumId (String AlbumId)
        {
            this.AlbumId = AlbumId;
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

        public String getALbumDate ()
        {
            return ALbumDate;
        }

        public void setALbumDate (String ALbumDate)
        {
            this.ALbumDate = ALbumDate;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [AlbumImage = "+AlbumImage+", GenreName = "+GenreName+", AlbumId = "+AlbumId+", AlbumName = "+AlbumName+", ArtistName = "+ArtistName+", ALbumDate = "+ALbumDate+"]";
        }
    }
}
