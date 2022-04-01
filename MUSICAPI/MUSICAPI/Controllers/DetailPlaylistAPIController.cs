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
    public class DetailPlaylistAPIController : ApiController
    {
        DetailPlaylistService dplv = new DetailPlaylistService();
        [HttpGet]
        public async Task<IHttpActionResult> getdetailPLList()
        {
            try
            {
                return Ok(new { status = true, data = dplv.getListDetailPlaylist() });
            }
            catch (Exception e)
            {
                return Ok(new { status = false, msg = e.ToString() });
            }

        }
    }
}
