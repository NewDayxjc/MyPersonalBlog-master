package com.blog.hh.personalblog.domain;

import com.blog.hh.personalblog.Vo.BaseVo;
import java.util.Date;

/**
 *date 2018/12/28 16:44
 *description 用户实体类
 */
public class User extends BaseVo {

  private String username;

  private String password;

  private Integer id;

  private String name;

  private String city;

  private String avatar_url;

  private String github_url;

  private String wechat_url;

  private String email;

  private String signature;

  private String music_url;

  private Date reg_time;

  private Integer status;

}
