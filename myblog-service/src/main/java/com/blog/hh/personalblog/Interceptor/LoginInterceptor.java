package com.blog.hh.personalblog.Interceptor;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
/**
*author huangh
*date 2019/6/2 9:47
*description 登陆验证
*/
public class LoginInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws IOException {
    HttpSession session = request.getSession();
    Object loginUser = session.getAttribute("loginUser");
    if(!StringUtils.isEmpty(loginUser)){
      return true;
    }
//    response.sendRedirect("/");
    return false;
  }
}
