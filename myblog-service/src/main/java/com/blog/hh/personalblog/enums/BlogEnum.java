package com.blog.hh.personalblog.enums;

import lombok.Getter;

/**
*author huangh
*date 2018/12/28 17:33
*description 博客枚举类 枚举了各种请求各种状态
*/
@Getter
public enum BlogEnum {

  LOGIN_ERROR(501,"用户名或密码不存在"),
  CATEGORY_EXIST(502,"已存在分类名"),
  NOPE_LOGIN(503,"未登录"),
  PARAM_EMPTY(504, "请求参数为空"),
  LOGOUT_ERROR(505,"注销失败"),
  UPLOAD_SUCCESS(204,"上传成功")
  ;

  private Integer code;
  private String message;

  BlogEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}
