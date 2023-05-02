package com.edan.report.aop;

import com.edan.report.pojo.User;
import com.edan.report.tool.R;
import com.edan.report.utils.StringInspect;
import com.edan.report.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Component
@Aspect
@Slf4j
public class checkPower {
    //@Around(value = "execution(* com.edan.report.controller.UserController.*(..))")
    @Around("@annotation(com.edan.report.annotation.Check)")
    //方法有check注解
    public Object check(ProceedingJoinPoint joinPoint) throws Throwable
    {
        //获取参数
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            //如果参数是user的话
            if (arg instanceof User)
            {
                User user = (User) arg;
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                String token = attributes.getRequest().getHeader("Token");
                log.info("token{}",token);
                //token为空
                if (StringInspect.Blank(token))
                    return R.error("无权限");
                else
                {
                    String userId = TokenUtils.getUserId(token);
                    //null表示修改自己信息，因为前端传的就是自己token
                    String s = user.getUserId();
                    if (StringInspect.Blank(s))
                    {
                        user.setUserId(userId);
                    }
                    //token中id与修改用户的id一致有权限修改，也就是修改本人的信息的权限
                    if (userId.equals(user.getUserId()))
                    {

                        Object proceed = joinPoint.proceed(args);
                        return proceed;
                    }else
                    {
                        return R.error("无权限");
                    }
                }
            }
        }
        return null;
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Check check = signature.getMethod().getAnnotation(Check.class);
//        if (check==null)
//        {
//            return joinPoint.proceed(joinPoint.getArgs());
//        }
//        else
//        {
//
//        }
    }
}
