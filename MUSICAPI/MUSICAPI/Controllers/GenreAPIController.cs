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
    public class GenreAPIController : ApiController
    {
        GenreService gv = new GenreService();
        [HttpGet]
        public async Task<IHttpActionResult> getGenreList()
        {
            try
            {
                return Ok(new { status = true, data = gv.getListGenre() });
            }
            catch (Exception e)
            {
                return Ok(new { status = false, msg = e.ToString() });
            }
        }
    }
}
