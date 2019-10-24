package com.iresearch.svc.service;

import com.iresearch.svc.bean.MyUserDetails;
import com.iresearch.svc.bean.SysUser;
import com.iresearch.svc.constant.RedisContrasts;
import com.iresearch.svc.mapper.svc.UserMapper;
import com.iresearch.svc.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component("MyUserDetailService")
@Slf4j
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisUtils redisUtils;
    private long expire_time=RedisContrasts.expire_time;


    @Override
    public UserDetails loadUserByUsername(String s) {

        log.info("0_"+s);
        SysUser user;
        String key="authuser_"+s;
        if(redisUtils.hasKey(key)){
            user=(SysUser)redisUtils.get(key);
        }else{
            user = userMapper.findByUserName(s);//数据库查询 看用户是否存在
            if(user != null){
                redisUtils.set(key,user,expire_time);
            }

        }


        if (user == null){
            throw new UsernameNotFoundException("user not exist!");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 中间逻辑省略。。。。。。
        MyUserDetails userDetails = new MyUserDetails(user, grantedAuthorities);
        return userDetails;

    }
}
