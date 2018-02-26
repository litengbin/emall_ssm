package com.ltb.emall.interceptor;

import com.ltb.emall.pojo.AdminUser;
import com.ltb.emall.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * ClassName: AdminLoginInterceptor
 * Description: 管理员登录拦截器
 * User: litengbin
 * Date: 2018/2/25 22:59
 * Version: 1.0.0
 */
public class AdminLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String[] noNeedAuthPage = new String[]{
                "order_delivery", "home", "register"
        };
        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri, contextPath);
        if (uri.startsWith("/admin_")) {
            String method = StringUtils.substringAfterLast(uri, "/admin_");
            if (!Arrays.asList(noNeedAuthPage).contains(method)) {
                AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
                if (null == adminUser) {
                    response.sendRedirect("adminloginPage");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
