package com.edan.report.config;



import com.alibaba.fastjson.JSONObject;
import com.edan.report.tool.R;
import com.edan.report.utils.StringInspect;
import com.edan.report.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//过滤器名字和拦截路径
//责任链模式
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器,支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        //不需要处理,注册
        String uris[]={"/user/register"};
        boolean check=check(uris,request.getRequestURI());
        if (check)
        {
            //放行
            filterChain.doFilter(request,response);
        }
        else
        {
            log.info("拦截判断有无该用户");
//            如果项目涉及到跨域,浏览器访问服务端会先后发送两次请求，
//            第一次请求为OPTIONS请求，作用是查看服务端是否支持跨域，该请求中不会携带我们需要的token信息；
//            如果支持跨域，那么就会发出第二次请求，这第二次请求才是我们常用的GET/POST请求，才会携带我们的token。
//            如果不支持跨域，那么第二次请求就不会发了。
            String method =request.getMethod();
            if (method.equals("OPTIONS"))
            {
                //放行
                filterChain.doFilter(request,response);
                return;
            }
            //获取Token
            String token = request.getHeader("Token");
            //如果没有Token请求头
            if (StringInspect.Blank(token))
            {
                response.getWriter().write(JSONObject.toJSONString(R.error("Please login")));
            }
            else
            {
                //检查请求头的Token是否合法
                boolean verify = TokenUtils.verify(token);
                if (verify)
                {
                    //放行
                    filterChain.doFilter(request,response);
                }
                else
                {
                    response.getWriter().write(JSONObject.toJSONString(R.error("Token非法")));
                }
            }
        }
    }
    //检查的路径
    private boolean check(String[] urls, String requestURI) {
        for (String url:urls)
        {
            boolean match=PATH_MATCHER.match(url,requestURI);
            if (match)
                return true;
        }
        return false;
    }
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
