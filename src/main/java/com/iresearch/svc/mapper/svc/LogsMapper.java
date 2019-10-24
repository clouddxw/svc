package com.iresearch.svc.mapper.svc;

import com.iresearch.svc.bean.Logs;
import com.iresearch.svc.bean.Norm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogsMapper {
    void saveLogs(Logs logs);
}
