/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers, if any.
 */

package com.wt.controller;

import com.google.common.base.Strings;

import com.alibaba.druid.util.StringUtils;
import com.wt.config.Const;
import com.wt.sys.service.IUserService;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import com.jtech.marble.shiro.ShiroUser;
import com.jtech.marble.shiro.ShiroUtil;

/**
 * <p>
 *     登录页面
 * </p>
 *
 * @author wt
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private final IUserService iUserService;
    @Autowired
    public HomeController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    /**
     * 初始化页面
     * @return
     */
    @GetMapping(value = {"/home", "/"})
    public String home(){
        return "/home";
    }

    /**
     * 用户登录请求
     *
     * @param username           登录账号
     * @param password           登录密码
     * @param remember           是否记住密码
     * @param redirectAttributes falsh  attribute
     * @return 登录失败返回当前界面 如果登录成功，则跳转主界面.
     */
    @PostMapping("/login/proving")
    public String login( @RequestParam("username") String username,
                         @RequestParam("password") String password,
                         RedirectAttributes redirectAttributes,
                         @RequestParam(value = "remember", required = false) String remember,
                         HttpServletRequest request){
        if (StringUtils.isEmpty(username)) {
            redirectAttributes.addFlashAttribute("message", "请输入用户名称");
            return "redirect:/home";
        }
        redirectAttributes.addFlashAttribute("username", username);

        if (StringUtils.isEmpty(password)) {
            redirectAttributes.addFlashAttribute("message", "请输入登录密码");
            return "redirect:/home";
        }
        boolean rememberMe = !Strings.isNullOrEmpty(remember) && BooleanUtils.toBoolean(remember);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();

        LOGGER.info("准备登陆用户 => {}", username);
        try {
            LOGGER.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            LOGGER.info("对用户[" + username + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            LOGGER.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "未知账户");
        } catch (IncorrectCredentialsException ice) {
            LOGGER.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "密码不正确");
        } catch (LockedAccountException lae) {
            LOGGER.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            LOGGER.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            // 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            LOGGER.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute(Const.MESSAGE, "用户名或密码不正确");
        }

        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            LOGGER.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            final ShiroUser user = ShiroUtil.getUser();
            Session session = currentUser.getSession();
            session.setAttribute("user", user.getId()); // 为了记录日志，所以将登录用户id放到session里
            return "redirect:/main";
        }
        return "redirect:/home";
    }

    /**
     * 登录成功后跳转主页面
     * @param model
     * @return
     */
    @GetMapping("/main")
    public String viewMain(Model model){
        final ShiroUser shiroUser = ShiroUtil.getUser();
        model.addAttribute("user", shiroUser);

        return "/mian";
    }


    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("用户退出登录");
            }
        }
        return "redirect:/login/proving";
    }


}
