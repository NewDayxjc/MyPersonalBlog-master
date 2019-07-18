package com.blog.hh.personalblog.time;

import com.blog.hh.personalblog.dto.CountDto;
import com.blog.hh.personalblog.service.ArticleService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
*author huangh
*date 2019/1/20 12:51
*description 定时任务
*/
@EnableScheduling
@Component
@Slf4j
public class Timer {

  @Autowired
  private ArticleService articleService;

  @Autowired
  private RedisTemplate redisTemplate;
  /**
   * 每天晚上21:50运行
   */
  @Scheduled(cron = "0 10 23 * * ?")
  public void autoSaveViews(){
    List<CountDto> articleViews = redisTemplate.opsForList().range("articleViews", 0, -1);
    if (articleViews!=null){
      articleService.updateViews(articleViews);
    }
  }

}
