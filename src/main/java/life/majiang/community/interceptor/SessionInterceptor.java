package life.majiang.community.interceptor;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor{

    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie != null && cookie.equals(" ")) {
                if (cookie.getName().equals("token")) {
                    String cookieValue = cookie.getValue();
                    User user = userMapper.findByToken(cookieValue);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
            return true;
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
