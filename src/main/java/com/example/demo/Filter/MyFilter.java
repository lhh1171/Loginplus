//package com.example.demo.Filter;
//
//import com.example.demo.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@Slf4j
//@WebFilter(filterName = "myFilter", urlPatterns = {"/*"})
//public class MyFilter implements Filter {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        log.info("过滤器初始化");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
////        log.info("请求处理");
////        HttpServletResponse response = (HttpServletResponse) servletResponse;
////        HttpServletRequest request = (HttpServletRequest) servletRequest;
////        log.info("MyFilter, URL：{}", request.getRequestURI());
////        if (request.getRequestURI().contains("login")) {
////            filterChain.doFilter(servletRequest, servletResponse);
////        } else {
////            log.info("非法URL：{}", request.getRequestURI());
////            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
////            PrintWriter writer = response.getWriter();
////            writer.print("no access");
////        }
//    }
//
//    @Override
//    public void destroy() {
//        log.info("过滤器销毁");
//    }
//}