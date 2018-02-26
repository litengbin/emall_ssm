package com.ltb.emall.interceptor;

import com.ltb.emall.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: LoginInterceptor
 * Description: 登录拦截器
 * User: litengbin
 * Date: 2018/2/23 18:23
 * Version: 1.0.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * @author litengbin
         * @method preHandle
         * @param       [request, response, handler]
         * @return boolean
         * @date 2018/2/23 18:31
         * @version 1.0.0
         * @description 在业务处理器处理请求之前被调用
         *                如果返回false
         *                从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
         *                如果返回true
         *                执行下一个拦截器,直到所有的拦截器都执行完毕
         *                再执行被拦截的Controller
         *                然后进入拦截器链,
         *                从最后一个拦截器往回执行所有的postHandle()
         *                接着再从最后一个拦截器往回执行所有的afterCompletion()
         */
        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String[] noNeedAuthPage = new String[]{
                "home",
                "checkLogin",
                "register",
                "loginAjax",
                "login",
                "product",
                "category",
                "search"};
        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri, contextPath);
        if (uri.startsWith("/fore")) {
            String method = StringUtils.substringAfterLast(uri, "/fore");
            if (!Arrays.asList(noNeedAuthPage).contains(method)) {
                User user = (User) session.getAttribute("user");
                if (null == user) {
                    response.sendRedirect("loginPage");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /**
         * @author litengbin
         * @method postHandle
         * @param       [request, response, handler, modelAndView]
         * @return void
         * @date 2018/2/23 18:29
         * @version 1.0.0
         * @description 在业务处理器处理请求执行完成后, 生成视图之前执行的动作
         *               可在modelAndView中加入数据，比如当前时间
         */
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /**
         * @author litengbin
         * @method afterCompletion
         * @param       [request, response, handler, ex]
         * @return void
         * @date 2018/2/23 18:31
         * @version 1.0.0
         * @description 在DispatcherServlet完全处理完请求后被调用, 可用于清理资源等
         *               当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
         */
        super.afterCompletion(request, response, handler, ex);
    }
}
