package com.iresearch.svc.config;



import com.google.gson.Gson;
import com.iresearch.svc.bean.SysUser;
import com.iresearch.svc.utils.JsonData;
import com.iresearch.svc.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by linziyu on 2019/2/9.
 *
 * 用户认证成功处理类
 */

@Component("UserLoginAuthenticationSuccessHandler")
@Slf4j
public class UserLoginAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    SessionRegistry sessionRegistry;




    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
        JsonData jsonData = null;

        List<SessionInformation> allSessions = sessionRegistry.getAllSessions(authentication.getPrincipal(), false);
        log.info(allSessions.size()+"");
        if(authentication.getAuthorities().toString().indexOf("ROLE_SVCBASIC")>0 && allSessions.size()>=1){
            String  currentSessionId = allSessions.get(allSessions.size()-1).getSessionId();
                for (SessionInformation sessionInformation : allSessions) {
                    if(sessionInformation.getSessionId()!=currentSessionId){
                    sessionInformation.expireNow();
                    }
                }

        }


        if(authentication.getAuthorities().toString().indexOf("ROLE_SVCBASIC")>0){
            jsonData = new JsonData(201,"认证OK");
        }else{
            jsonData = new JsonData(200,"认证OK");
        };
        String json = new Gson().toJson(jsonData);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        out.write(json);
        out.flush();
        out.close();
    }
}