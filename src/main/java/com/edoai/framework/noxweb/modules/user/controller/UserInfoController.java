package com.edoai.framework.noxweb.modules.user.controller;


import com.edoai.framework.noxweb.common.extension.api.Result;
import com.edoai.framework.noxweb.modules.user.entity.UserInfo;
import com.edoai.framework.noxweb.modules.user.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Nox
 * @since 2019-04-19
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    private IUserInfoService userInfoService;

    private ResourceBundleMessageSource messageSource;

    @Autowired
    public UserInfoController(IUserInfoService userInfoService, ResourceBundleMessageSource messageSource) {
        this.userInfoService = userInfoService;
        this.messageSource = messageSource;
    }

    @GetMapping("/list")
    public Result<List<UserInfo>> list() {
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println("locale = " + locale);
        return Result.ok(userInfoService.list());
    }

}
