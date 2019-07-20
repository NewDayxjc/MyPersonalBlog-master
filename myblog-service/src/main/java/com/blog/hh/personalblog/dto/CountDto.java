package com.blog.hh.personalblog.dto;


import com.blog.hh.personalblog.Vo.BaseVo;
import lombok.Data;

/**
*description 阅读量
*/
@Data
public class CountDto extends BaseVo {
    private Integer count;
    private Integer articleId;
}
