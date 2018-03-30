package com.drp.controller;

import com.drp.data.entity.User;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
    public String login(String userName, String password, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, password, true);
        // usernamePasswordToken.setRememberMe(true);
        try {
            SecurityUtils.getSubject().login(usernamePasswordToken);
            log.info(((User)(SecurityUtils.getSubject().getPrincipal())).getUserName() + " login success");
            Session session = SecurityUtils.getSubject().getSession();
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            if (savedRequest != null) {
                return "redirect:" + savedRequest.getRequestURI().substring(savedRequest.getRequestURI().indexOf("/", 1));
            } else {
                mv.addObject("id", session.getId());
                mv.setViewName("index");
                return "redirect:index.jsp";
            }
            // BeanUtils
        } catch (UnknownAccountException exception) {
            return "redirect:/goLogin.do";
        } catch (IncorrectCredentialsException exception) {
            return "redirect:/goLogin.do";
        } catch (ExcessiveAttemptsException exception) {
            return "redirect:/goLogin.do";
        }
    }

    @RequestMapping(value = "goLogin.do")
    public String goLogin() {
        return "/login";
    }

    @RequestMapping(value = "logout.do")
    public void logout() {
        SecurityUtils.getSubject().logout();
        System.out.println("logout success");
    }

    @RequestMapping(value = "authorization.do")
    public String authorization() {
        return "/authorization.jsp";
    }

}
