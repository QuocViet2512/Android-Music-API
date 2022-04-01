package com.example.lib.Models;


import java.io.Serializable;
import java.util.List;

public class ArtistModel implements Serializable
{
    private List<Artist> data;

    private String status;

    public List<Artist> getData ()
    {
        return data;
    }

    public void setData (List<Artist> data)
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
    public class Artist implements Serializable
    {
        private String ArtistId;

        private String ArtistName;

        private String ArtistCountry;

        private String ArtistImage;

        public String getArtistId ()
        {
            return ArtistId;
        }

        public void setArtistId (String ArtistId)
        {
            this.ArtistId = ArtistId;
        }

        public String getArtistName ()
        {
            return ArtistName;
        }

        public void setArtistName (String ArtistName)
        {
            this.ArtistName = ArtistName;
        }

        public String getArtistCountry ()
        {
            return ArtistCountry;
        }

        public void setArtistCountry (String ArtistCountry)
        {
            this.ArtistCountry = ArtistCountry;
        }

        public String getArtistImage ()
        {
            return ArtistImage;
        }

        public void setArtistImage (String ArtistImage)
        {
            this.ArtistImage = ArtistImage;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [ArtistId = "+ArtistId+", ArtistName = "+ArtistName+", ArtistCountry = "+ArtistCountry+", ArtistImage = "+ArtistImage+"]";
        }
    }
}
