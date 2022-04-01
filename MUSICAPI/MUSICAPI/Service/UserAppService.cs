using MUSICAPI.Entity;
using MUSICAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MUSICAPI.Service
{
    public class UserAppService
    {
        DB data = new DB();
        public PutInfoUserObject Login(LoginObject login)
        {
            var Check = data.UserApps.FirstOrDefault(p => p.UserPhone.Equals(login.UserPhone) && p.UserPassword.Equals(login.UserPassword));
            if(Check != null)
            {
                PutInfoUserObject info = new PutInfoUserObject();
                info.UserId = Check.UserId.ToString();
                info.UserPassword = Check.UserPassword;
                info.UserPhone = Check.UserPhone;
                info.UserName = Check.UserName;
                return info;
            }
            else { return null; }
           

        }

        public bool SignUp(SignUpObject signup)
        {
            var Check = data.UserApps.FirstOrDefault(p => p.UserPhone.Equals(signup.UserPhone));
            if (Check == null)
            {
                UserApp newuser = new UserApp();
                newuser.UserId = Guid.NewGuid();
                newuser.UserPhone = signup.UserPhone;
                newuser.UserPassword = signup.UserPassword;
                newuser.UserName = signup.UserName;
                data.UserApps.Add(newuser);
                data.SaveChanges();
                return true;

            }
            else
            {
                return false;
            }
        }
       

    }
}