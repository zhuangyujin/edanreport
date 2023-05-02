//package com.edan.report.config;
//
//import com.edan.report.annotation.Check;
//import com.edan.report.pojo.User;
//import com.edan.report.utils.TokenUtils;
//import org.springframework.core.MethodParameter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
//
////配置拦截器
//public class Intercept implements HandlerMethodArgumentResolver
//{
//
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        System.out.println(1);
//        //参数含有注解@Check要解析
//        if (parameter.hasParameterAnnotation(Check.class))
//            return true;
//        else
//            return false;
//    }
//
//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//       if (parameter.getParameterType() == User.class)
//       {
//           HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
//           String token = request.getHeader("token");
//           //获取token解析获取userId判断与要修改的userId是否一致，即判断修改的数据是否是本人信息
//           Long userId = TokenUtils.getUserId(token);
//       }
//        User user = new User();
//       user.setUserId("1");
//        return user;
//    }
//}