package com.li.reggie.controller;

import com.li.reggie.common.R;
import com.li.reggie.entry.User;
import com.li.reggie.service.UserService;
import com.li.reggie.utils.SMSUtils;
import com.li.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author LI
 * @create 2022/6/30 21:59
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpServletRequest request){
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)){
            String code = ValidateCodeUtils.generateValidateCode4String(4);

            log.info("验证码为：{}",code);
            SMSUtils.sendMessage("阿里云短信测试","SMS_154950909",phone,code);
            request.getSession().setAttribute(phone,code);
            return R.success("手机验证码发送成功");
        }
        return R.error("短信发送失败");
    }
    @PostMapping("/login")
    public R<String> login(@RequestBody Map map, HttpSession session){

        log.info(map.toString());
        return R.error("登入失败");
    }

}

