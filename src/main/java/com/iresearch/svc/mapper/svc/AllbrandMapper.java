package com.iresearch.svc.mapper.svc;

import com.iresearch.svc.bean.*;
import com.iresearch.svc.redis.RedisUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface AllbrandMapper {

    List<Brand> getAllbrand(Norm norm);
    List<Brand> getAllbrand_Basic(Norm norm);
    /**
     *
     * @param brand
     * @param norm
     * @return
     */
    public List<Brandtrend> getBrandtrend(@Param("brand") String brand, @Param("norm")Norm norm);
    /**
     *
     * @param brand
     * @param norm
     * @return
     */
    public List<Brandtrend> getBrandtrend_Basic(@Param("brand") String brand, @Param("norm")Norm norm);
    Brandtype getBrandtype(String brand);
    /**
     *
     * @param brand
     * @param norm
     * @return
     */
    public List<Brandshow> getBrandshow(@Param("brand") String brand, @Param("norm")Norm norm);
    /**
     *
     * @param brand
     * @param norm
     * @return
     */
    public List<Brandshow> getBrandshow_Basic(@Param("brand") String brand, @Param("norm")Norm norm);


}
