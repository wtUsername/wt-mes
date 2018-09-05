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

import com.alibaba.druid.util.StringUtils;
import com.wt.sys.service.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping(value = {"/home", "/"})
    public String home(){
        System.err.print(111);
        return "/home";
    }

    /**
     * 登录验证
     * @param username
     * @param password
     * @param redirectAttributes
     * @param request
     * @return
     */
    @PostMapping("/login/proving")
    public String login( @RequestParam("username") String username,
                         @RequestParam("password") String password,
                         RedirectAttributes redirectAttributes,
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
        boolean b = iUserService.selectByName(username,password);
        if(!b){
            return "redirect:/home";
        }
        return "/main";
    }


}
