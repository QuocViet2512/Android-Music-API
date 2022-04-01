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
    public class UserAppAPIController : ApiController
    {
        UserAppService sv = new UserAppService();
        [HttpPost]
        public async Task<IHttpActionResult> Login(LoginObject login)
        {
            PutInfoUserObject check =  sv.Login(login);
            if (check!= null){
                return Ok(new { status = true, data = check });
            }
            else
            {
                return Ok(new { status = false, msg = "Fail" });
            }
            
        }
        [HttpPost]
        public async Task<IHttpActionResult> SingUp(SignUpObject signup)
        {
            bool check = sv.SignUp(signup);
            return check == true ? Ok(new { status = true, msg = "Success" }) : Ok(new { status = false, msg = "Fail" });
        }
    }
}
