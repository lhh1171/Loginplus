package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Util.JWTUtils;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 登录控制器
 *
 * @author pan_junbiao
 **/
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private static final String slat = "mszlu!@#";
    /**
     * 首页
     */
    @RequestMapping("/index")
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
        return "/uuuu.html";
    }

    /**
     * 登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response, String tel, String password) throws IOException {
        if ((password).equals((userService.findPasswordByTel(tel)))){
            /*先用md5加密一下*/
            password = DigestUtils.md5Hex(password + slat);
//            JWTUtils.checkToken(tel);
            String token = JWTUtils.createToken(tel);
            redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(password),1, TimeUnit.DAYS);
//            response.setHeader("Authorization", token);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", token);
            return new ResponseEntity<>("ss",httpHeaders, HttpStatus.OK);
//            return "ss";
        } else {
//            return "error";
            return new ResponseEntity<>("error", HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * 登出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request) {
//        //删除cookie
//        Cookie[] cookies = request.getCookies();
        //cookie设置跨域，必须在同一个domain下
//        resp.setHeader("Access-Control-Allow-Credentials", "true");
//        当异步对象设置了withCredentials=true时，浏览器会保留下响应的Cookie等信息
//        $.ajax({
//           url: "http://localhost:8080/corscookie",
//          type: "GET",
//          xhrFields: {
//              withCredentials: true
//           },
//    crossDomain: true
//});
//        cookie.setDomain("localhost:8080");
//        for (Cookie cookie:cookies){
//            String cookieName = cookie.getName();
//            if ("tel".equals(cookieName)||"password".equals(cookieName)){
//                cookie.setMaxAge(0);
//            }
//        }
//        //重定向到登录页面
        return toLoginPage();
    }
}