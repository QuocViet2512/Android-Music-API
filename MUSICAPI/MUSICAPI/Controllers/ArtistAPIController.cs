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
    public class ArtistAPIController : ApiController
    {
        ArtistService av = new ArtistService();
        [HttpGet]
        public async Task<IHttpActionResult> getArtistList()
        {
            try
            {
                return Ok(new { status = true, data = av.getListArtist() });
            }
            catch (Exception e)
            {
                return Ok(new { status = false, msg = e.ToString() });
            }           
        }

        [HttpPost]
        public async Task<IHttpActionResult> getArtInforBySongID(string songid)
        {
            try
            {
                return Ok(new { status = true, data = av.getInforArtistBySongID(songid) });
            }
            catch (Exception e)
            {
                return Ok(new { status = false, msg = e.ToString() });
            }
        }
    }
}
