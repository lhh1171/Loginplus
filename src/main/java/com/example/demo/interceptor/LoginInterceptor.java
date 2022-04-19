package com.example.demo.interceptor;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    private static   LoginInterceptor loginInterceptor;

    @PostConstruct
    public void init(){
        loginInterceptor=this;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        //判断当前请求地址是否登录地址
        if(uri.contains("login") || uri.contains("toLoginPage")) {
            //登录请求，直接放行
            return true;
        } else {
                //说明已经登录，放行
                //从request中获取所有的Cookie
                Cookie[] cookies = request.getCookies();
                String tel = null;
                String password =null;
                if (cookies!=null){
                    for(Cookie cookie:cookies){
                        String cookieName = cookie.getName();
                        String cookieValue = cookie.getValue();
                        if ("tel".equals(cookieName)){
                            tel = cookieValue;
                        }else if ("password".equals(cookieName)){
                            password = cookieValue;
                        }
                    }
                }
                //没有用户登录过，或者cookie过期
                if (tel == null||password==null){
                    response.sendRedirect("/uuuu.html");
                    return false;
                } else {
                    //检验密码是否正确，防止伪造cookie
                    return (password).equals((loginInterceptor.userService.findPasswordByTel(tel)));
                }
        }

    }

}
