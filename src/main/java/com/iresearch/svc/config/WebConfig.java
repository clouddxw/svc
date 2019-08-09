package com.iresearch.svc.config;

import com.iresearch.svc.bean.LoginUser;
import com.iresearch.svc.controller.LoginInterceptor_n;
import com.iresearch.svc.controller.LoginInterceptor_v;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    //@Autowired
    //LogInterceptor logInterceptor;

    @Autowired
    LoginInterceptor_n loginInterceptor_n=new LoginInterceptor_n();
    LoginInterceptor_v loginInterceptor_v=new LoginInterceptor_v();

    /**
     * 不需要登录拦截的url:登录注册和验证码
     */
    final String[] notLoginInterceptPaths = {"/login/**","/login.html**","/imp/css/**","/imp/js/**","/img/**","/index/**","/vf/**","/VF/**","/lee/**","/Lee/**"};//"/", "/login/**", "/person/**", "/register/**", "/validcode", "/captchaCheck", "/file/**", "/contract/htmltopdf", "/questions/**", "/payLog/**", "/error/**" };
    final String[] nLoginInterceptPaths = {"/basic/**"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 日志拦截器
        //registry.addInterceptor(logInterceptor).addPathPatterns("/**");
        // 登录拦截器

        registry.addInterceptor(loginInterceptor_n).addPathPatterns(nLoginInterceptPaths).excludePathPatterns(notLoginInterceptPaths);
        registry.addInterceptor(loginInterceptor_v).addPathPatterns("/vip/**").excludePathPatterns(notLoginInterceptPaths);
        super.addInterceptors(registry);

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }
}