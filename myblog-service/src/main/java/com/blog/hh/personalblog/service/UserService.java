package com.blog.hh.personalblog.service;

import com.blog.hh.personalblog.dto.UserDto;

public interface UserService {
  void login(UserDto userDto);

  UserDto getUser();
}
