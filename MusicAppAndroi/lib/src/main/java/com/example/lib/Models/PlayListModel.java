package com.example.lib.Models;

import java.io.Serializable;
import java.util.List;

public class PlayListModel {
    private List<Playlist> data;

    private String status;

    public List<Playlist> getData ()
    {
        return data;
    }

    public void setData (List<Playlist> data)
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
    public class Playlist implements Serializable{
        private String PlaylistName;

        private String PlaylistIcon;

        private String PlaylistId;

        private String UserName;

        private String PlaylistImage;

        public String getPlaylistName ()
        {
            return PlaylistName;
        }

        public void setPlaylistName (String PlaylistName)
        {
            this.PlaylistName = PlaylistName;
        }

        public String getPlaylistIcon ()
        {
            return PlaylistIcon;
        }

        public void setPlaylistIcon (String PlaylistIcon)
        {
            this.PlaylistIcon = PlaylistIcon;
        }

        public String getPlaylistId ()
        {
            return PlaylistId;
        }

        public void setPlaylistId (String PlaylistId)
        {
            this.PlaylistId = PlaylistId;
        }

        public String getUserName ()
        {
            return UserName;
        }

        public void setUserName (String UserName)
        {
            this.UserName = UserName;
        }

        public String getPlaylistImage ()
        {
            return PlaylistImage;
        }

        public void setPlaylistImage (String PlaylistImage)
        {
            this.PlaylistImage = PlaylistImage;
        }


    }
}
