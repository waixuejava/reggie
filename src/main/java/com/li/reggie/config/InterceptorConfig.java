package com.li.reggie.config;

import com.alibaba.fastjson.JSON;
import com.li.reggie.common.BaseContext;
import com.li.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author LI
 * @create 2022/6/22 13:37
 */
@Slf4j
public class InterceptorConfig implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object employee = request.getSession().getAttribute("employee");
        if (employee==null){
            log.info("账号没登入，拦截成功");
            response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
            log.info("跳转成功");
            return false;
        }
        BaseContext.setCurrentId((Long) employee);
            return true;
    }


}
