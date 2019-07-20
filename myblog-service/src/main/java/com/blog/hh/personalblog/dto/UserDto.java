package com.blog.hh.personalblog.dto;

import com.blog.hh.personalblog.domain.User;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
*description 用户传输对象
*/
@Data
public class UserDto extends User implements Serializable {

  @NotNull(message = "用户名不能为空")
  private String username;

  @NotNull(message = "密码不能为空")
  private String password;

  private String name;

  private String city;

  private String avatarUrl;

  private String githubUrl;

  private String wechatUrl;

  private String email;

  private String signature;

  private String musicUrl;
}
