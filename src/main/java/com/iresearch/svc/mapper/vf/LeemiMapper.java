package com.iresearch.svc.mapper.vf;


import com.iresearch.svc.bean.LeeproductItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeemiMapper {
    List<LeeproductItem> getItems(@Param("date") String date,
                                  @Param("type") String type,
                                  @Param("industry_en") String industry_en,
                                  @Param("industry2") String industry2);


}
