package com.blog.hh.personalblog.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.blog.hh.personalblog.Vo.BaseVo;
import java.util.Date;
public class Article{
  private Integer id;
  private Integer user_id;
  private String content;
  private Integer cate_id;
  private Date upload_time;
  private String image_url;
  private Integer view;
  private Integer comment;
  private Integer status;
  private String markdown_content;

}
