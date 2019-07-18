package com.blog.hh.personalblog.Vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
public class BaseVo {
  @TableField(exist = false)
  private int pageNum = 1;
  @TableField(exist = false)
  private int pageSize = 4;

  @TableField(exist = false)
  private int isOffset = 0;

  @TableField(exist = false)
  private String searchKey;
}
