package com.iresearch.svc.service.svc;

import com.iresearch.svc.bean.Brand;
import com.iresearch.svc.bean.Brandshowdt;
import com.iresearch.svc.bean.Norm;
import com.iresearch.svc.constant.RedisContrasts;
import com.iresearch.svc.mapper.svc.BrandcustomMapper;
import com.iresearch.svc.redis.RedisUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandcustomService {

    @Autowired
    private RedisUtils redisUtils;
    @Resource
    private BrandcustomMapper brandcustomMapper;
    private long expire_time=RedisContrasts.expire_time;

    public List<Brandshowdt> getBrandshowdt(String key,String brandname, Norm norm){
        List<Brandshowdt> out=new ArrayList<>();

        if (redisUtils.hasKey(key)) {
            out =(List<Brandshowdt>)redisUtils.get(key);
        } else {
            out = brandcustomMapper.getBrandshowdt(brandname,norm);
            redisUtils.set(key,out,expire_time);
        }
        return out;
    }




}
