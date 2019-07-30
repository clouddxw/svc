package com.iresearch.svc.mapper.svc;

import com.iresearch.svc.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShowcompMapper {
    List<Showname>  getLshowname(Norm norm);
    List<Brandname>  getLbrandname(Norm norm);
    /**
     * @param  showname
     * @norm   norm
     * @return
     */
    public Show getOneshow(@Param("showname") String showname, @Param("norm")Norm norm);
    /**
     * @param  brandname
     * @norm   norm
     * @return
     */
    public Brandcomp getBrandcomp(@Param("brandname") String brandname, @Param("norm")Norm norm);
}
