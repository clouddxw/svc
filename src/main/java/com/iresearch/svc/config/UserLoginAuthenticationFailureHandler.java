package com.iresearch.svc.config;




import com.google.gson.Gson;
import com.iresearch.svc.mapper.svc.UserMapper;
import com.iresearch.svc.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by linziyu on 2019/2/9.
 *
 * 用户认证失败处理类
 */

@Component("UserLoginAuthenticationFailureHandler")
@Slf4j
public class UserLoginAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {



    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // log.info("{}","认证失败");


        // log.info("{}",exception.getMessage());

        // String username = (String) request.getAttribute("username");


        JsonData jsonData = null;
        if(exception.getMessage().equals("user not exist!")){
            jsonData = new JsonData(403,"密码错误");
        }else if(exception.getMessage().equals("Bad credentials")){
            jsonData = new JsonData(402,"用户不存在");
        }

//        if(exception.getMessage().equals("vip online over")){
//            jsonData = new JsonData(600,"当前用户可同时在线人数20人，已超限，登录失败！！！");
//        }else if(exception.getMessage().equals("basic online over")){
//            jsonData = new JsonData(600,"普通用户可同时在线人数1人，已超限，登录失败！！！</br>可升级至VIP用户,可同时在线20人,还可享受更多尊贵特权！</br>账号申请及升级请联系：IRS.SVC@iresearch.com.cn 或咨询相关销售同事！");
//        }




        String json = new Gson().toJson(jsonData);//包装成Json 发送的前台
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        out.write(json);
        out.flush();
        out.close();


//        if(exception.getMessage().equals("Bad credentials")){
//            jsonData = new JsonData(403,"密码错误");
//            // String user_name =userService.findByUserNameAttemps(username);
//            // if (user_name == null){
//            //     String time = DateUtil.getTimeToString();
//            //     UserLoginAttempts userLoginAttempts = new UserLoginAttempts(username,1,time);
//            //     userService.saveAttempts(userLoginAttempts);
//            // }
//
//
//            // if(userService.getAttempts(username) == 1){
//            //     String time = DateUtil.getTimeToString();
//            //     userService.setAttempts(username,time);
//            //     jsonData = new JsonData(403,"密码错误,你还有2次机会进行登录操作");
//            // }
//            // else if(userService.getAttempts(username) == 3){
//            //     User user = userService.findByUserName(username);
//            //     userService.LockUser(user.getId());
//            //     jsonData = new JsonData(403,"最后一次尝试登陆失败，你已经被冻结了");
//            // }
//            // else if (userService.getAttempts(username) ==2 ){
//            //     String time = DateUtil.getTimeToString();
//            //     userService.setAttempts(username,time);
//            //     jsonData = new JsonData(403,"密码错误,你还有1次机会进行登录操作");
//            // }
//
//
//        }






    }
}