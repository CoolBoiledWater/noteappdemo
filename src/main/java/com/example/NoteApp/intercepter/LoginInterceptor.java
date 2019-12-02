package com.example.NoteApp.intercepter;

        import com.example.NoteApp.entity.User;
        import com.example.NoteApp.util.RedisUtil;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Component;
        import org.springframework.web.servlet.HandlerInterceptor;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

/**
 * @author LiuZepeng
 * @date 2019-11-26 10:30
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*User user = (User) request.getSession().getAttribute("user");*/

        if (!redisUtil.hasKey("user")){
            response.sendRedirect("/static/errorpage/error.html");
            return false;
        }
        return true;
    }
}
