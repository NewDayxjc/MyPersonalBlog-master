package com.blog.hh.personalblog.result;

import lombok.Data;

/**
*description 返回对象实体
*/
@Data
public class Result<T> {

  private String message;
  private int code;
  private T data;

}
