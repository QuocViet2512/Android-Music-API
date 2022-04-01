using MUSICAPI.Models;
using MUSICAPI.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;

namespace MUSICAPI.Controllers
{
    public class TrackSongAPIController : ApiController
    {
        TrackSongService sv = new TrackSongService();
        [HttpGet]
        public async Task<IHttpActionResult> getSongList()
        {        
            return Ok(new { status = true, data = sv.getListTrackSong() });
        }
        [HttpPost]
        public async Task<IHttpActionResult> SearchSong(string songname)
        {
            try
            {
                return Ok(new { status = true, data = sv.SearchSong(songname) });
            }
            catch(Exception e) { return Ok(new { status = false, msg = e.ToString() }); }
  
        }

        [HttpPost]
        public async Task<IHttpActionResult> ListSongByAlbumID(string albumid)
        {
            try
            {
                return Ok(new { status = true, data = sv.ListSongByAlbumID(albumid)});
            }
            catch (Exception e) { return Ok(new { status = false, msg = e.ToString() }); }

        }

        [HttpPost]
        public async Task<IHttpActionResult> ListSongByArtistID(string artistid)
        {
            try
            {
                return Ok(new { status = true, data = sv.ListSongByArtistID(artistid)});
            }
            catch (Exception e) { return Ok(new { status = false, msg = e.ToString() }); }

        }

        [HttpPost]
        public async Task<IHttpActionResult> ListSongByGenreID(string genreid)
        {
            try
            {
                return Ok(new { status = true, data = sv.ListSongByGenreID(genreid) });
            }
            catch (Exception e) { return Ok(new { status = false, msg = e.ToString() }); }

        }
        [HttpPost]
        public async Task<IHttpActionResult> ListSongByPlaylistID(string playlistid)
        {
            try
            {
                return Ok(new { status = true, data = sv.ListSongByPlaylistID(playlistid) });
            } catch (Exception e)
            {
                return Ok(new
                {
                    status = false,
                    msg = e.ToString()

                });
            }
        }
    }
  
}
