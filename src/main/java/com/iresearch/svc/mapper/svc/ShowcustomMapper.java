package com.iresearch.svc.mapper.svc;

import com.iresearch.svc.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShowcustomMapper {

    /**
     * @param  showname
     * @norm   norm
     * @return
     */
    public Scshow getShow(@Param("showname") String showname, @Param("norm") Norm norm);
    /**
     * @param  showname
     * @norm   norm
     * @return
     */
    public List<Scshow> getSshow(@Param("showname") String showname, @Param("norm") Norm norm);

    /**
     * @param  showname
     * @norm   norm
     * @return
     */
    public List<Showbranddt> getShowbranddt(@Param("showname") String showname, @Param("norm") Norm norm);
}
