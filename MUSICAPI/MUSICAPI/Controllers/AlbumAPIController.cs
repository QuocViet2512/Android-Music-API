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
    public class AlbumAPIController : ApiController
    {
       
        AlbumService sv = new AlbumService();
        [HttpGet]
        public async Task<IHttpActionResult> getAlbumList()
        {
            try
            {
                return Ok(new {status = true , data = sv.getAlbumList()});
            }catch(Exception e)
            {
                return Ok(new { status = false, msg = e.ToString() });
            }
        }
    }
}
