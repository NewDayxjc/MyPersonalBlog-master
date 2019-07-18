package com.blog.hh.personalblog.dto;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.blog.hh.personalblog.Vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@TableName("category")
public class CategoryDto extends BaseVo implements Serializable{

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @NotNull(message = "名称不能为空")
  private String name;

  private Integer number;

  @JsonIgnore
  private Date uploadTime;

  @JsonIgnore
  private Integer status = 1;
}
