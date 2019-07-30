package com.iresearch.svc.controller;


import com.iresearch.svc.bean.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashSet;
import java.util.Set;

/**
 * 登录验证拦截
 *
 */
@Controller
@Component
public class LoginInterceptor_v extends HandlerInterceptorAdapter {

    //Logger log = Logger.getLogger(LoginInterceptor.class);
    Logger logger = LoggerFactory.getLogger(LoginInterceptor_v.class);

    /*@Autowired
    UserService userService;*/

    /*@Value("${IGNORE_LOGIN}")
    Boolean IGNORE_LOGIN;*/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String basePath = request.getContextPath();
        String path = request.getRequestURI();
        //logger.info("basePath:"+basePath);
//      log.info("path:"+path);

        if(!doLoginInterceptor(path, basePath) ){//是否进行登陆拦截
            return true;
        }


        boolean out=false;


        //如果登录了，会把用户信息存进session
        HttpSession session = request.getSession();
        LoginUser user =  (LoginUser) session.getAttribute("svcuser");
        //String passwd = request.getParameter("passwd");
        //logger.info(passwd);


        if(user==null){
            /*log.info("尚未登录，跳转到登录界面");
            response.sendRedirect(request.getContextPath()+"signin");*/

            String requestType = request.getHeader("X-Requested-With");
//          System.out.println(requestType);
            if(requestType!=null && requestType.equals("XMLHttpRequest") ){
                response.setHeader("sessionstatus","timeout");
//              response.setHeader("basePath",request.getContextPath());
                response.sendError(518, "session timeout.");
                response.getWriter().print("LoginTimeout");
                out = false;
            } else {
                //log.info("尚未登录，跳转到登录界面");
                response.sendRedirect("/login.html");
            }

            out = false;
        }else{
            String role=user.getRole();
            logger.info(role);
            if(role.equals("admin") || role.equals("vip")){
                logger.info("1");
                out = true;
            }else{
                out = false;
            }
        }
//      log.info("用户已登录,userName:"+userInfo.getSysUser().getUserName());
        return out;
    }

    /**
     * 是否进行登陆过滤
     * @param path
     * @param basePath
     * @return
     */
    private boolean doLoginInterceptor(String path,String basePath){
        path = path.substring(basePath.length());
        Set<String> notLoginPaths = new HashSet<>();
        //设置不进行登录拦截的路径：登录注册和验证码
        //notLoginPaths.add("/");
        notLoginPaths.add("/login.html**");
        notLoginPaths.add("/login**");
        notLoginPaths.add("/imp/css/**");
        notLoginPaths.add("/imp/js/**");
        notLoginPaths.add("/img/**");
        notLoginPaths.add("/vf/**");
        notLoginPaths.add("/VF/**");

        if(notLoginPaths.contains(path)) return false;
        return true;
    }
}