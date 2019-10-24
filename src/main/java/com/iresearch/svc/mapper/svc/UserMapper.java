package com.iresearch.svc.mapper.svc;

import com.iresearch.svc.bean.SysUser;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    SysUser findByUserName(@Param("username") String username);
}
