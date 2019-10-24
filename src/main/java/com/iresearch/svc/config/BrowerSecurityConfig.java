package com.iresearch.svc.config;


import com.iresearch.svc.bean.SysUser;
import com.iresearch.svc.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;


@Configuration//标识为配置类
@EnableWebSecurity//启动Spring Security的安全管理
@EnableGlobalMethodSecurity(securedEnabled = true)
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {



    private final static BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();


    @Bean
    public PasswordEncoder passwordEncoder(){//密码加密类
        return  new BCryptPasswordEncoder();
    }


    @Bean
    public MyUserDetailService myUserDetailService(){
        return new MyUserDetailService();
    }


    @Autowired
    private UserLoginAuthenticationFailureHandler userLoginAuthenticationFailureHandler;//验证失败的处理类

    @Autowired
    private UserLoginAuthenticationSuccessHandler userLoginAuthenticationSuccessHandler;//验证成功的处理类




    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/login**","/login.html**","/imp/**","/img/**","/vf/**","/api/vf/**","/lee/**","/api/lee/**","/vf_mi_img/**","/lee_mi_proimg/**")//静态资源等不需要验证
                .permitAll()//不需要身份认证
                .antMatchers("/svc/basic/**","/svc/**","/api/svc/index/**","/api/svc/basic/**").hasAnyRole("SVCBASIC","SVCADMIN","SVCVIP")//只有管理员才能访问
                .antMatchers("/svc/**","/api/svc/**").hasAnyRole("SVCADMIN","SVCVIP","SVCBASIC")
                .anyRequest().authenticated();//其他路径必须验证身份

        http.csrf().disable()
                .formLogin()
                .loginPage("/login.html")//登录页面，加载登录的html页面
                .loginProcessingUrl("/logining")//发送Ajax请求的路径
                .usernameParameter("username")//请求验证参数
                .passwordParameter("password")//请求验证参数
                .failureHandler(userLoginAuthenticationFailureHandler)//验证失败处理
                .successHandler(userLoginAuthenticationSuccessHandler)//验证成功处理
                .permitAll()
                .and()
                .headers().frameOptions().disable();//登录页面无需设置验证

        http.sessionManagement().maximumSessions(20)
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry())
                .expiredUrl("/login.html?stat=onlineover");


        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);





    }


    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {


        auth.userDetailsService(myUserDetailService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return ENCODER.encode(charSequence);
            }

            //密码匹配，看输入的密码经过加密与数据库中存放的是否一样
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return ENCODER.matches(charSequence,ENCODER.encode(s));
            }
        });
    }


}