package com.iresearch.svc.controller;

import com.iresearch.svc.bean.LoginUser;
import com.iresearch.svc.mapper.svc.LoginMapper;
import com.iresearch.svc.redis.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;


@Component
@RestController
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired(required = false)
    private LoginMapper loginmapper;
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("login")
    @ResponseBody
    public Object logincheck(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException,IOException,NullPointerException {
        HttpSession session=request.getSession();
        String name=new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
        String passwd=new String(request.getParameter("passwd").getBytes("ISO-8859-1"),"UTF-8");
//        LoginUser getuser=new LoginUser();
//        getuser.setName(name);
//        getuser.setPasswd(passwd);
        String userkey="user_"+name;
        LoginUser user=null;
        class Checkres{
            String res;
            int diff;
            java.sql.Date enddate;
            String role;
            public String getRes() {
                return res;
            }
            public void setRes(String res) {
                this.res = res;
            }
            public int getDiff() {
                return diff;
            }
            public void setDiff(int diff) {
                this.diff = diff;
            }
            public java.sql.Date getEnddate() {
                return enddate;
            }
            public void setEnddate(java.sql.Date enddate) {
                this.enddate = enddate;
            }
            public String getRole(){return role;}
            public void setRole(String role){this.role = role;}

        }
        Checkres checkres=new Checkres();
        String gpasswd=null;




        //session.setAttribute("ip",request.getRemoteAddr());
        if(redisUtils.hasKey(userkey)){
            user=(LoginUser)redisUtils.get(userkey);
            int diff=diffnow(user.getEnddate());
            if (diff>30){
                if(passwd.equals(user.getPasswd())){
                    session.setAttribute("svcuser",user);
                    checkres.setRes("Success");
                    checkres.setRole(user.getRole());
                }else{
                    checkres.setRes("pwdFalse");
                    //res="pwdFalse";
                }

            }else if(diff>0 && diff<=30){
                if(passwd.equals(user.getPasswd())){
                    session.setAttribute("svcuser",user);
                    checkres.setRes("SuccessTw");
                    checkres.setEnddate(user.getEnddate());
                    checkres.setDiff(diff);
                    checkres.setRole(user.getRole());
                }else{
                    checkres.setRes("pwdFalse");
                    //res="pwdFalse";
                }

            }else{
                if(passwd.equals(user.getPasswd())){
                    session.setAttribute("svcuser",user);
                    checkres.setRes("Te");
                    checkres.setEnddate(user.getEnddate());
                    checkres.setDiff(diff);
                    checkres.setRole(user.getRole());
                }else{
                    checkres.setRes("pwdFalse");
                }

            }

        }else{


            user=loginmapper.getUser(name);
            if(user==null){
                checkres.setRes("nameFalse");
            }else{
                redisUtils.set(userkey,user,3600);
                int diff=diffnow(user.getEnddate());
                if (diff>30){
                    if(passwd.equals(user.getPasswd())){
                        session.setAttribute("svcuser",user);
                        checkres.setRes("Success");
                        checkres.setRole(user.getRole());
                    }else{
                        checkres.setRes("pwdFalse");
                        //res="pwdFalse";
                    }

                }else if(diff>0 && diff<=30){
                    if(passwd.equals(user.getPasswd())){
                        session.setAttribute("svcuser",user);
                        checkres.setRes("SuccessTw");
                        checkres.setEnddate(user.getEnddate());
                        checkres.setDiff(diff);
                        checkres.setRole(user.getRole());
                    }else{
                        checkres.setRes("pwdFalse");
                        //res="pwdFalse";
                    }

                }else{
                    if(passwd.equals(user.getPasswd())){
                        session.setAttribute("svcuser",user);
                        checkres.setRes("Te");
                        checkres.setEnddate(user.getEnddate());
                        checkres.setDiff(diff);
                        checkres.setRole(user.getRole());
                    }else{
                        checkres.setRes("pwdFalse");
                    }
                }

            }
        }
        logger.info(checkres.toString());
        return checkres;
    }

    public int diffnow(java.sql.Date date){
        Date now=new Date();
        Date date1=new Date(date.getTime());
        int a = (int) ((date1.getTime() - now.getTime()) / (1000*3600*24));
        return  a;
    }

//    @RequestMapping("login")
//    public void login(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException,IOException,NullPointerException {
//        HttpSession session=request.getSession();
//        String name=new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
//        String passwd=new String(request.getParameter("passwd").getBytes("ISO-8859-1"),"UTF-8");
////        LoginUser getuser=new LoginUser();
////        getuser.setName(name);
////        getuser.setPasswd(passwd);
//        String userkey="user_"+name;
//        LoginUser user;
//        String res;
//        //session.setAttribute("ip",request.getRemoteAddr());
//        if(redisUtils.hasKey(userkey)){
//            user=(LoginUser)redisUtils.get(userkey);
//        }else{
//            user=loginmapper.getUser(name);
//            redisUtils.set(userkey,user,3600);
//        }
//        if(passwd.equals(user.getPasswd())){
//            session.setAttribute("svcuser",user);
//            response.sendRedirect("index.html");
//        }else{
//            response.sendRedirect("login.html");
//        }
//    }

}
