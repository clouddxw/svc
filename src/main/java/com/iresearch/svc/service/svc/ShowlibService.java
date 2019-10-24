package com.iresearch.svc.service.svc;

import com.iresearch.svc.bean.Libshowbd;
import com.iresearch.svc.bean.Norm;
import com.iresearch.svc.bean.Showsimilar;
import com.iresearch.svc.constant.RedisContrasts;
import com.iresearch.svc.mapper.svc.ShowlibMapper;
import com.iresearch.svc.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowlibService {
    @Autowired
    private RedisUtils redisUtils;
    @Resource
    private ShowlibMapper showlibMapper;
    private long expire_time=RedisContrasts.expire_time;


    public List<Libshowbd> getShowbd(String key, String showname){
        List<Libshowbd> out=new ArrayList<>();
        if (redisUtils.hasKey(key)){
            out=(List<Libshowbd>)redisUtils.get(key);
        }else{
            out=showlibMapper.getShowbd(showname);
            redisUtils.set(key,out,expire_time);
        }

        return out;
    }

    public List<Showsimilar> getShowsimilar(String key, String showname,Norm norm){
        List<Showsimilar> out=new ArrayList<>();
        if (redisUtils.hasKey(key)){
            out=(List<Showsimilar>)redisUtils.get(key);
        }else{
            out=showlibMapper.getShowsimilar(showname,norm);
            redisUtils.set(key,out,expire_time);
        }

        return out;
    }

}
