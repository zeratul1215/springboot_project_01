package com.tianhong.reggi.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.tianhong.reggi.common.BaseContext;
import com.tianhong.reggi.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //matcher
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.get uri
        //2.judge whether we need to process the request
        //3.if not, dofilter
        //4.if yes, if logedin, dofilter
        //if not, let them login
        String requestURI = request.getRequestURI();
        String[] urls = new String[]{//URIs dont need to be processed
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        //2.judge whether need to process
        boolean check = check(urls,requestURI);

        if(check){
            filterChain.doFilter(request,response);
        }
        else {
            //need process
            if(request.getSession().getAttribute("employee") != null){
                Long empID = (Long)request.getSession().getAttribute("employee");
                BaseContext.setCurrentId(empID);
                filterChain.doFilter(request,response);
            }
            else {
                //如果未登录，通过输出流想客户端传响应数据
                log.info("need to login");
                response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));
            }
        }
    }

    //路径匹配，检查是否需要放行
    public boolean check(String urls[], String requestURI){
        for(String url : urls){
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match)
                return match;
        }
        return false;
    }
}
