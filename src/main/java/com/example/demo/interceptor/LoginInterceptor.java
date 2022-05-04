package com.example.demo.interceptor;
import com.alibaba.fastjson.JSON;
import com.example.demo.Util.UserThreadLocal;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private static   LoginInterceptor loginInterceptor;

    @PostConstruct
    public void init(){
        loginInterceptor=this;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        String uri = request.getRequestURI();
        //判断当前请求地址是否登录地址
        if(uri.contains("login") || uri.contains("toIndexPage")) {
            //登录请求，直接放行
            return true;
        } else {
            if (token==null){
                response.sendRedirect("/uuuu.html");
                return false;
            }else {
                String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
                String password= JSON.parseObject(userJson,String.class);
                if (password==null){
                    return false;
                }
                //登录验证成功，放行
                //我希望在controller中 直接获取用户的信息 怎么获取?
                UserThreadLocal.put(password);
                return true;
            }
        }
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //如果不删除 ThreadLocal中用完的信息 会有内存泄漏的风险
        System.out.println("remove the user>>>>");
        UserThreadLocal.remove();
    }

}
