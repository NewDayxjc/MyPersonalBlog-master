package com.blog.hh.personalblog.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.blog.hh.personalblog.Vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@TableName("article")
public class ArticleDto extends BaseVo implements Serializable  {
  /*文章id*/
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  /*所属用户id*/
  private Integer userId;
  /*文章名称*/
  @NotNull(message = "文章标题不能为空")
  private String title;
  /*文章内容*/
  private String content;

  @NotNull(message = "markdown格式的内容不能为空")
  private String markdownContent;
  /*分类id*/
  @NotNull(message = "分类id不能为空")
  private Integer cateId;
  @TableField(exist = false)
  private Integer haveImg=1;

  /*上传时间*/
  @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
  private Date uploadTime;

  /*分类名*/
  @TableField(exist = false)
  private String categoryName;

  /*文章阅读量*/
  private Integer view = 0;

  private Integer status = 1;

}
