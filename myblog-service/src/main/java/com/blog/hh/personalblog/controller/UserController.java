package com.blog.hh.personalblog.controller;

import com.blog.hh.personalblog.dto.UserDto;
import com.blog.hh.personalblog.enums.BlogEnum;
import com.blog.hh.personalblog.exception.BlogException;
import com.blog.hh.personalblog.result.Result;
import com.blog.hh.personalblog.result.ResultVo;
import com.blog.hh.personalblog.service.UserService;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import jdk.nashorn.internal.ir.ReturnNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
*date 2018/12/28 16:50
*description 用户模块
*/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private RedisTemplate redisTemplate;

  /**
  *date 2018/12/28 18:13
  *description 登陆
  */
  @PostMapping("/login")
  public Result login(@RequestBody @Valid UserDto userDto , BindingResult result,HttpServletRequest request){
    if (result.hasErrors()){
      return ResultVo.error(result.getFieldError().getDefaultMessage());
    }
    try {
      userService.login(userDto);
      HttpSession session = request.getSession();
      session.setAttribute("loginUser", userDto);
    }catch (BlogException e){
      return ResultVo.error(e.getMessage());
    }
    return ResultVo.success();
  }
  /**
  *date 2019/6/5 20:59
  *description
  */
  @RequestMapping("/logout")
  public Result logout(HttpServletRequest request){
    HttpSession session = request.getSession();
    session.removeAttribute("loginUser");
    if (!StringUtils.isEmpty(session.getAttribute("loginUser"))){
       return ResultVo.error(BlogEnum.LOGOUT_ERROR);
     }
    return ResultVo.success();
}

  /**
  *date 2018/12/28 18:19
  *description 访问主页时获取用户信息
  */
  @GetMapping("/getUser")
  public Result getUser(){
    UserDto userDto = userService.getUser();
    return ResultVo.success(userDto);
  }

  @GetMapping("/checkLogin")
  public Result isLogin(HttpServletRequest request){
    Object loginUser = request.getSession().getAttribute("loginUser");
    if (StringUtils.isEmpty(loginUser)){
      return ResultVo.error(BlogEnum.NOPE_LOGIN);
    }
    return ResultVo.success();

  }
}