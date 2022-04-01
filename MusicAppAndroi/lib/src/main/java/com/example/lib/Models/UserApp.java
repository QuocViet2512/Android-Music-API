package com.example.lib.Models;

import java.io.Serializable;

public class UserApp implements Serializable {

    private User data;

    private String status;

    public User getData ()
    {
        return data;
    }

    public void setData (User data)
    {
        this.data = data;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }
    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", status = "+status+"]";
    }

    public class User implements Serializable
    {
        private String UserName;

        private String UserPhone;

        private String UserId;

        private String UserPassword;

        public String getUserName ()
        {
            return UserName;
        }

        public void setUserName (String UserName)
        {
            this.UserName = UserName;
        }

        public String getUserPhone ()
        {
            return UserPhone;
        }

        public void setUserPhone (String UserPhone)
        {
            this.UserPhone = UserPhone;
        }

        public String getUserId ()
        {
            return UserId;
        }

        public void setUserId (String UserId)
        {
            this.UserId = UserId;
        }

        public String getUserPassword ()
        {
            return UserPassword;
        }

        public void setUserPassword (String UserPassword)
        {
            this.UserPassword = UserPassword;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [UserName = "+UserName+", UserPhone = "+UserPhone+", UserId = "+UserId+", UserPassword = "+UserPassword+"]";
        }
    }
}
