package com.ltb.emall.interceptor;

import com.ltb.emall.pojo.Category;
import com.ltb.emall.pojo.OrderItem;
import com.ltb.emall.pojo.User;
import com.ltb.emall.service.CategoryService;
import com.ltb.emall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: OtherInterceptor
 * Description: 其他拦截器
 * User: litengbin
 * Date: 2018/2/23 19:13
 * Version: 1.0.0
 */
public class OtherInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        List<Category> cs = categoryService.list();
        session.setAttribute("cs", cs);
        String contextPath = session.getServletContext().getContextPath();
        session.setAttribute("contextPath", contextPath);
        User user = (User) session.getAttribute("user");
        int cartTotalItemNumber = 0;
        if (null != user) {
            List<OrderItem> ois = orderItemService.listByUser(user.getId());
            for (OrderItem oi : ois) {
                cartTotalItemNumber += oi.getNumber();
            }
        }
        session.setAttribute("cartTotalItemNumber", cartTotalItemNumber);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
