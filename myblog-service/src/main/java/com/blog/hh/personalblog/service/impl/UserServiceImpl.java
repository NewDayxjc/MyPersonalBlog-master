package com.blog.hh.personalblog.service.impl;

import com.blog.hh.personalblog.dto.UserDto;
import com.blog.hh.personalblog.enums.BlogEnum;
import com.blog.hh.personalblog.exception.BlogException;
import com.blog.hh.personalblog.mapper.UserMapper;
import com.blog.hh.personalblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public void login(UserDto userDto) {
    UserDto userByName = userMapper.getUserByName(userDto.getUsername());
    /*查询的用户为空或密码不一致*/
    if (userByName == null||!userByName.getPassword().equals(userDto.getPassword())){
      throw new BlogException(BlogEnum.LOGIN_ERROR);
    }
  }

  @Override
  @Cacheable(value = "getUser")
  public UserDto getUser() {
    UserDto user = userMapper.getUser();
    return user;
  }
}
