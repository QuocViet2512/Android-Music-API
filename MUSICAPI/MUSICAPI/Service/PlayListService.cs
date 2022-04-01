using MUSICAPI.Entity;
using MUSICAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MUSICAPI.Service
{
    public class PlayListService
    {
        DB data = new DB();
        public List<PlayListObject> getPlayList()
        {
            var PlayList = data.Playlists.ToList();
            var PLOBJ = new List<PlayListObject>();
            foreach(var item in PlayList)
            {
                PlayListObject obj = new PlayListObject();
                obj.PlaylistId = item.PlaylistId;
                obj.PlaylistImage = item.PlaylistImage;
                obj.PlaylistName = item.PlaylistName;
                obj.PlaylistIcon = item.PlaylistIcon;
                obj.UserName = item.UserId == null ? "" : item.UserApp.UserName;
                PLOBJ.Add(obj);
            }
            return PLOBJ;
        }

        public List<PlayListObject> getPlayListByUserID(string id)
        {
            var PlayList = data.Playlists.Where(p=>p.UserId.ToString().Equals(id)).ToList();
            var PLOBJ = new List<PlayListObject>();
            foreach (var item in PlayList)
            {
                PlayListObject obj = new PlayListObject();
                obj.PlaylistId = item.PlaylistId;
                obj.PlaylistImage = item.PlaylistImage;
                obj.PlaylistName = item.PlaylistName;
                obj.PlaylistIcon = item.PlaylistIcon ==null?"":item.PlaylistIcon;
                obj.UserName = item.UserId == null ? "" : item.UserApp.UserName;
                PLOBJ.Add(obj);
            }
            return PLOBJ;
        }
    }
}