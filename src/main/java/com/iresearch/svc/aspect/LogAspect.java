package com.iresearch.svc.aspect;


import com.iresearch.svc.bean.Logs;
import com.iresearch.svc.bean.MyUserDetails;
import com.iresearch.svc.bean.SysUser;
import com.iresearch.svc.mapper.svc.LogsMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Aspect
@Component
public class LogAspect {
    @Resource
    private LogsMapper logsMapper;


    @Pointcut("@annotation(com.iresearch.svc.aspect.SysServiceLog)")//自定义元注解
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws ParseException{
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveLog(point, time);
        return result;
    }

    public void saveLog(ProceedingJoinPoint joinPoint, long time) throws ParseException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Logs logs = new Logs();//对应的日志实体
        SysServiceLog logAnnotation = method.getAnnotation(SysServiceLog.class);//自定义元注解
        if (logAnnotation != null) {
            // 注解上的描述
            logs.setProject(logAnnotation.project());
            logs.setModel(logAnnotation.model());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        logs.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
//        Object[] args = joinPoint.getArgs();
//        // 请求的方法参数名称
//        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
//        String[] paramNames = u.getParameterNames(method);
//        if (args != null && paramNames != null) {
//            String params = "";
//            for (int i = 0; i < args.length; i++) {
//                params += "  " + paramNames[i] + ": " + args[i].toString();
//            }
//            logs.setParameter(params);
//        }
        try{
            MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//获取 Security对象
            logs.setName(user.getUsername());//也可以获取 session 等缓存
        }catch (Exception e){
            logs.setName("未认证");
        }
        logs.setConsuming((int) time );//操作时长  毫秒
        logs.setIp(request.getRemoteAddr());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        logs.setDate(sdf.parse(sdf.format(new Date())));
        logs.setTime(new Date());
        logsMapper.saveLogs(logs);//添加日志表
    }
}
