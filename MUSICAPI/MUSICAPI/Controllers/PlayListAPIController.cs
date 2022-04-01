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
    public class PlayListAPIController : ApiController
    {
        PlayListService sv = new PlayListService();
        [HttpGet]
        public async Task<IHttpActionResult> getPlayList()
        {
            try
            {
                return Ok(new { status = true, data = sv.getPlayList() });
            }
            catch (Exception e)
            {
                return Ok(new { status = false, msg = e.ToString() });
            }
        }
        [HttpGet]
        public async Task<IHttpActionResult> getPlayListByUserID(string id)
        {
            try
            {
                return Ok(new { status = true, data = sv.getPlayListByUserID(id) });
            }
            catch (Exception e)
            {
                return Ok(new { status = false, msg = e.ToString() });
            }
        }

    }
   

}
