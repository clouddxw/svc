package com.iresearch.svc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
    Integer uid;
    String username;
    String name;
    String password;
    String salt;
    String state;

    public  String getCredentialsSalt(){
        return this.username+this.salt;
    }
}
