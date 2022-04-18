package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录控制器
 *
 * @author pan_junbiao
 **/
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    /**
     * 首页
     */
    @RequestMapping("/toIndexPage")
    public String toIndexPage() {
        //跳转至登录页面
        return "index.html";
    }

    /**
     * 登录页面
     */
    @RequestMapping("/")
    public String toLoginPage() {
        //跳转至登录页面
        return "uuuu.html";
    }

    /**
     * 登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response,String tel, String password) {
        if ((password).equals((userService.findPasswordByTel(tel)))){
            /*最好可以用md5加密一下*/
            Cookie cookie1 = new Cookie("tel",tel);
            Cookie cookie2 = new Cookie("password",password);
            //设置有效时间--->10天
            cookie1.setMaxAge(10*24*60*60);
            cookie2.setMaxAge(10*24*60*60);
            //设置关联路径
            cookie1.setPath(request.getContextPath());
            cookie2.setPath(request.getContextPath());
            //发送Cookie给浏览器
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            System.out.println("index.html");
            return "redirect:toIndexPage";
        } else {
            return "error";
        }
    }

    /**
     * 登出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request) {
        //删除cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            String cookieName = cookie.getName();
            if ("tel".equals(cookieName)||"password".equals(cookieName)){
                cookie.setMaxAge(0);
            }
        }
        //重定向到登录页面
        return toLoginPage();
    }
}