package com.iresearch.svc.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class SysUser_bak implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private List<SysRole> roles;
    private Collection<? extends GrantedAuthority> authorities;




    public SysUser_bak(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<SysRole> roles = getRoles();
        for(SysRole role : roles)
        {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean equals(Object rhs) {
        return rhs instanceof SysUser_bak ? this.username.equals(((SysUser_bak)rhs).username) : false;
    }

    public int hashCode() {
        return this.username.hashCode();
    }
}