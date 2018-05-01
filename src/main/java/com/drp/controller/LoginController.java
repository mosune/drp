package com.drp.controller;

import com.drp.util.UserUtil;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * FileName: LoginController
 *
 * @author gcg
 * @create 2017/12/29 9:50
 * Description: Login Controller
 * History:
 **/
@Log4j
@Controller
public class LoginController {

    @RequestMapping("index.do")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "login.do")
    public ModelAndView login(String userName, String password, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, password);
        try {
            SecurityUtils.getSubject().login(usernamePasswordToken);
            session.setAttribute("menus", UserUtil.getCurUser().getMenus());
            session.setAttribute("name", UserUtil.getCurName());
            session.setMaxInactiveInterval(60 * 60);
            mv.setViewName("/index");
        } catch (UnknownAccountException exception) {
            mv.addObject("msg", "用户不存在");
            mv.setViewName("/login");
        } catch (IncorrectCredentialsException exception) {
            mv.addObject("msg", "密码错误");
            mv.setViewName("/login");
        } catch (ExcessiveAttemptsException exception) {
            mv.addObject("msg", "账户已锁定，请10分钟之后再试");
            mv.setViewName("/login");
        } catch (LockedAccountException exception) {
            mv.addObject("msg", "账户已被冻结，请联系管理员");
            mv.setViewName("/login");
        }
        return mv;
    }

    @RequestMapping(value = "goLogin.do")
    public String goLogin() {
        return "/login";
    }

    @RequestMapping(value = "logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        SecurityUtils.getSubject().logout();
        return "/login";
    }

}
