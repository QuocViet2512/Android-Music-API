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
    public class DetailArtistAPIController : ApiController
    {
        DetailArtistService dav = new DetailArtistService();
        [HttpGet]
        public async Task<IHttpActionResult> getdetailATList()
        {
            try
            {
                return Ok(new { status = true, data = dav.getListDetailArtist() });
            }
            catch (Exception e)
            {
                return Ok(new { status = false, msg = e.ToString() });
            }

        }
    }
}
