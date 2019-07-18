package com.blog.hh.personalblog.exception;

import com.blog.hh.personalblog.enums.BlogEnum;
import lombok.Getter;
import lombok.Setter;

/**
*author huangh
*date 2018/12/28 17:32
*description 个人博客
*/
@Getter
@Setter
public class BlogException extends RuntimeException{

  private Integer code;

  public BlogException(Integer code,String message) {
    super(message);
    this.code = code;
  }

  public BlogException(BlogEnum blogEnum){
    super(blogEnum.getMessage());
    this.code = blogEnum.getCode();
  }

}
