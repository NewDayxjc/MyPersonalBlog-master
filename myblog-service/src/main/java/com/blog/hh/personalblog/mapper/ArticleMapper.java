package com.blog.hh.personalblog.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.blog.hh.personalblog.domain.Article;
import com.blog.hh.personalblog.dto.ArticleDto;
import java.util.List;
import javax.validation.Valid;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticleDto> {

  @Insert("insert into article(user_id,title,content,upload_time,cate_id,markdown_content) "
      + "values(#{userId},#{title},#{content},#{uploadTime},#{cateId},#{markdownContent})")
  @Options(keyColumn = "id" ,keyProperty = "id",useGeneratedKeys = true)
  Integer insert(ArticleDto article);

}
