package com.blog.hh.personalblog.mapper;

import com.blog.hh.personalblog.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("select * from user where username=#{name}")
  UserDto getUserByName(String name);

  @Select("select * from user where id = 1")
  UserDto getUser();
}
