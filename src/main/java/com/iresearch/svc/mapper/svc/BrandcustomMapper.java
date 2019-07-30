package com.iresearch.svc.mapper.svc;

import com.iresearch.svc.bean.Brandshowdt;
import com.iresearch.svc.bean.Norm;
import com.iresearch.svc.bean.Scshow;
import com.iresearch.svc.bean.Showbranddt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandcustomMapper {

    /**
     * @param brandname
     * @return
     * @norm norm
     */
    public List<Brandshowdt> getBrandshowdt(@Param("brandname") String brandname, @Param("norm") Norm norm);
}

