package com.iresearch.svc.mapper.svc;


import com.iresearch.svc.bean.LoginUser;

public interface LoginMapper {
    LoginUser getUser(String name);
}


