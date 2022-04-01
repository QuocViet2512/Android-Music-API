package com.example.lib.Models;

import java.util.List;

public class GenreModel
{
    private List<Genre> data;

    private String status;

    public List<Genre>getData ()
    {
        return data;
    }

    public void setData (List<Genre> data)
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

    public class Genre
    {
        private String GenreName;

        private String GenreId;

        private String GenreDescription;

        private String GenreImage;

        public String getGenreName ()
        {
            return GenreName;
        }

        public void setGenreName (String GenreName)
        {
            this.GenreName = GenreName;
        }

        public String getGenreId ()
        {
            return GenreId;
        }

        public void setGenreId (String GenreId)
        {
            this.GenreId = GenreId;
        }

        public String getGenreDescription ()
        {
            return GenreDescription;
        }

        public void setGenreDescription (String GenreDescription)
        {
            this.GenreDescription = GenreDescription;
        }

        public String getGenreImage ()
        {
            return GenreImage;
        }

        public void setGenreImage (String GenreImage)
        {
            this.GenreImage = GenreImage;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [GenreName = "+GenreName+", GenreId = "+GenreId+", GenreDescription = "+GenreDescription+", GenreImage = "+GenreImage+"]";
        }
    }

}