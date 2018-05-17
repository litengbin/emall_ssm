package com.ltb.emall.controller;

import com.github.pagehelper.PageHelper;
import com.ltb.emall.comparator.*;
import com.ltb.emall.pojo.*;
import com.ltb.emall.service.*;
import com.ltb.emall.util.PaymentUtil;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ClassName: ForeController
 * Description: 首页控制类
 * User: litengbin
 * Date: 2018/2/19 0:57
 * Version: 1.0.0
 */
@Controller
@RequestMapping("")
public class ForeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderService orderService;
    @Autowired
    ReviewService reviewService;

    @RequestMapping("forehome")
    public String home(Model model) {
        /**
         * @author litengbin
         * @method home
         * @param       [model]
         * @return java.lang.String
         * @date 2018/2/19 19:35
         * @version 1.0.0
         * @description 主页
         */
        List<Category> cs = categoryService.list();
        List<Product> ps = productService.list(cs.get(cs.size() - 1).getId());
        productService.fill(cs);
        productService.fillByRow(cs);
        model.addAttribute("cs", cs);
        model.addAttribute("ps", ps);
        return "fore/home";
    }

    @RequestMapping("foreregister")
    public String register(Model model, User user) {
        /**
         * @author litengbin
         * @method register
         * @param       [model, user]
         * @return java.lang.String
         * @date 2018/2/19 19:35
         * @version 1.0.0
         * @description 注册
         */
        String name = user.getName();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist = userService.isExist(name);
        if (exist) {
            String m = "用户名已经被使用,不能使用";
            model.addAttribute("msg", m);
            model.addAttribute("user", null);
            return "fore/register";
        }
        userService.add(user);
        return "redirect:registerSuccessPage";
    }

    @RequestMapping("forelogin")
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) {
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name, password);
        if (null == user) {
            model.addAttribute("msg", "账号密码错误");
            return "fore/login";
        }
        session.setAttribute("user", user);
        return "redirect:forehome";
    }

    @RequestMapping("forelogout")
    public String logout(HttpSession session) {
        /**
         * @author litengbin
         * @method logout
         * @param       [session]
         * @return java.lang.String
         * @date 2018/2/19 20:13
         * @version 1.0.0
         * @description 用户退出
         */
        session.removeAttribute("user");
        return "redirect:forehome";
    }

    @RequestMapping("foreproduct")
    public String product(int pid, Model model) {
        /**
         * @author litengbin
         * @method product
         * @param       [pid, model]
         * @return java.lang.String
         * @date 2018/2/21 12:56
         * @version 1.0.0
         * @description 产品列表
         */
        Product p = productService.get(pid);
        List<ProductImage> productSingleImages = productImageService.list(p.getId(), ProductImageService.imageType_single);
        List<ProductImage> productDetailImages = productImageService.list(p.getId(), ProductImageService.imageType_detail);
        p.setProductSingleImages(productSingleImages);
        p.setProductDetailImages(productDetailImages);
        List<PropertyValue> pvs = propertyValueService.list(p.getId());
        List<Review> reviews = reviewService.list(p.getId());
        productService.setSaleAndReviewNumber(p);
        model.addAttribute("reviews", reviews);
        model.addAttribute("p", p);
        model.addAttribute("pvs", pvs);
        return "fore/product";
    }

    @RequestMapping("forecheckLogin")
    @ResponseBody
    public String checkLogin(HttpSession session) {
        /**
         * @author litengbin
         * @method checkLogin
         * @param       [session]
         * @return java.lang.String
         * @date 2018/2/21 12:57
         * @version 1.0.0
         * @description 检查登录
         */
        User user = (User) session.getAttribute("user");
        if (null != user)
            return "success";
        return "fail";
    }

    @RequestMapping("foreloginAjax")
    @ResponseBody
    public String loginAjax(@RequestParam("name") String name, @RequestParam("password") String password, HttpSession session) {
        /**
         * @author litengbin
         * @method loginAjax
         * @param       [name, password, session]
         * @return java.lang.String
         * @date 2018/2/21 12:57
         * @version 1.0.0
         * @description ajax登录
         */
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name, password);
        if (null == user) {
            return "fail";
        }
        session.setAttribute("user", user);
        return "success";
    }

    @RequestMapping("forecategory")
    public String category(int cid, String sort, Model model) {
        /**
         * @author litengbin
         * @method category
         * @param       [cid, sort, model]
         * @return java.lang.String
         * @date 2018/2/21 12:57
         * @version 1.0.0
         * @description 产品排序
         */
        Category c = categoryService.get(cid);
        productService.fill(c);
        productService.setSaleAndReviewNumber(c.getProducts());
        if (null != sort) {
            switch (sort) {
                case "review":
                    Collections.sort(c.getProducts(), new ProductReviewComparator());
                    break;
                case "date":
                    Collections.sort(c.getProducts(), new ProductDateComparator());
                    break;

                case "saleCount":
                    Collections.sort(c.getProducts(), new ProductSaleCountComparator());
                    break;

                case "price":
                    Collections.sort(c.getProducts(), new ProductPriceComparator());
                    break;

                case "all":
                    Collections.sort(c.getProducts(), new ProductAllComparator());
                    break;
            }
        }
        model.addAttribute("c", c);
        return "fore/category";
    }

    @RequestMapping("foresearch")
    public String search(String keyword, Model model) {
        /**
         * @author litengbin
         * @method search
         * @param       [keyword, model]
         * @return java.lang.String
         * @date 2018/2/21 14:05
         * @version 1.0.0
         * @description 搜索
         */
        PageHelper.offsetPage(0, 20);
        List<Product> ps = productService.search(keyword);
        productService.setSaleAndReviewNumber(ps);
        model.addAttribute("ps", ps);
        return "fore/searchResult";
    }

    @RequestMapping("forebuyone")
    public String buyone(int pid, int num, HttpSession session) {
        /**
         * @author litengbin
         * @method buyone
         * @param       [pid, num, session]
         * @return java.lang.String
         * @date 2018/2/21 15:09
         * @version 1.0.0
         * @description 立即购买
         */
        Product p = productService.get(pid);
        int oiid = 0;
        User user = (User) session.getAttribute("user");
        boolean found = false;
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        for (OrderItem oi : ois) {
            if (oi.getProduct().getId().intValue() == p.getId().intValue()) {
                oi.setNumber(oi.getNumber() + num);
                orderItemService.update(oi);
                found = true;
                oiid = oi.getId();
                break;
            }
        }
        if (!found) {
            OrderItem oi = new OrderItem();
            oi.setUid(user.getId());
            oi.setNumber(num);
            oi.setPid(pid);
            orderItemService.add(oi);
            oiid = oi.getId();
        }
        return "redirect:forebuy?oiid=" + oiid;
    }

    @RequestMapping("forebuy")
    public String buy(Model model, String[] oiid, HttpSession session) {
        /**
         * @author litengbin
         * @method buy
         * @param       [model, oiid, session]
         * @return java.lang.String
         * @date 2018/2/21 15:03
         * @version 1.0.0
         * @description 结算页面
         */
        List<OrderItem> ois = new ArrayList<>();
        float total = 0;
        for (String strid : oiid) {
            int id = Integer.parseInt(strid);
            OrderItem oi = orderItemService.get(id);
            total += oi.getProduct().getPromotePrice() * oi.getNumber();
            ois.add(oi);
        }
        session.setAttribute("ois", ois);
        model.addAttribute("total", total);
        return "fore/buy";
    }

    @RequestMapping("foreaddCart")
    @ResponseBody
    public String addCart(int pid, int num, Model model, HttpSession session) {
        /**
         * @author litengbin
         * @method addCart
         * @param       [pid, num, model, session]
         * @return java.lang.String
         * @date 2018/2/21 15:10
         * @version 1.0.0
         * @description ajax加入购物车
         */
        Product p = productService.get(pid);
        User user = (User) session.getAttribute("user");
        boolean found = false;

        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        for (OrderItem oi : ois) {
            if (oi.getProduct().getId().intValue() == p.getId().intValue()) {
                oi.setNumber(oi.getNumber() + num);
                orderItemService.update(oi);
                found = true;
                break;
            }
        }
        if (!found) {
            OrderItem oi = new OrderItem();
            oi.setUid(user.getId());
            oi.setNumber(num);
            oi.setPid(pid);
            orderItemService.add(oi);
        }
        return "success";
    }

    @RequestMapping("forecart")
    public String cart(Model model, HttpSession session) {
        /**
         * @author litengbin
         * @method cart
         * @param       [model, session]
         * @return java.lang.String
         * @date 2018/2/21 15:11
         * @version 1.0.0
         * @description 查看购物车
         */
        User user = (User) session.getAttribute("user");
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        model.addAttribute("ois", ois);
        return "fore/cart";
    }

    @RequestMapping("forechangeOrderItem")
    @ResponseBody
    public String changeOrderItem(Model model, HttpSession session, int pid, int number) {
        /**
         * @author litengbin
         * @method changeOrderItem
         * @param       [model, session, pid, number]
         * @return java.lang.String
         * @date 2018/2/23 20:04
         * @version 1.0.0
         * @description ajax购物车调整订单项
         */
        User user = (User) session.getAttribute("user");
        if (null == user)
            return "fail";

        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        for (OrderItem oi : ois) {
            if (oi.getProduct().getId().intValue() == pid) {
                oi.setNumber(number);
                orderItemService.update(oi);
                break;
            }
        }
        return "success";
    }

    @RequestMapping("foredeleteOrderItem")
    @ResponseBody
    public String deleteOrderItem(Model model, HttpSession session, int oiid) {
        /**
         * @author litengbin
         * @method deleteOrderItem
         * @param       [model, session, oiid]
         * @return java.lang.String
         * @date 2018/2/23 20:06
         * @version 1.0.0
         * @description ajax购物车删除订单项
         */
        User user = (User) session.getAttribute("user");
        if (null == user)
            return "fail";
        orderItemService.delete(oiid);
        return "success";
    }

    @RequestMapping("forecreateOrder")
    public String createOrder(Model model, Order order, HttpSession session) {
        /**
         * @author litengbin
         * @method createOrder
         * @param       [model, order, session]
         * @return java.lang.String
         * @date 2018/2/24 15:43
         * @version 1.0.0
         * @description 生成订单
         */
        User user = (User) session.getAttribute("user");
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) +
                RandomUtils.nextInt(10000);
        order.setOrderCode(orderCode);
        order.setCreateDate(new Date());
        order.setUid(user.getId());
        order.setStatus(orderService.waitPay);
        List<OrderItem> ois = (List<OrderItem>) session.getAttribute("ois");
        float total = orderService.add(order, ois);
        for (OrderItem oi : ois) {
            int number = oi.getNumber();
            Product product = oi.getProduct();
            int inventory = product.getInventory();
            if (inventory >= number) {
                product.setInventory(inventory - number);
                productService.update(product);
            }
        }
        System.out.println(order.getId()+"aaa");
        return "redirect:forealipay?oid=" + order.getId() + "&total=" + total;
    }

    @RequestMapping("callback")
    public String callback(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
    {
        // 获得回调所有数据
        String p1_MerId = request.getParameter("p1_MerId");
        String r0_Cmd = request.getParameter("r0_Cmd");
        String r1_Code = request.getParameter("r1_Code");
        String r2_TrxId = request.getParameter("r2_TrxId");
        String r3_Amt = request.getParameter("r3_Amt");
        String r4_Cur = request.getParameter("r4_Cur");
        String r5_Pid = request.getParameter("r5_Pid");
        String r6_Order = request.getParameter("r6_Order");
        String r7_Uid = request.getParameter("r7_Uid");
        String r8_MP = request.getParameter("r8_MP");
        String r9_BType = request.getParameter("r9_BType");
        String rb_BankId = request.getParameter("rb_BankId");
        String ro_BankOrderId = request.getParameter("ro_BankOrderId");
        String rp_PayDate = request.getParameter("rp_PayDate");
        String rq_CardNo = request.getParameter("rq_CardNo");
        String ru_Trxtime = request.getParameter("ru_Trxtime");
        //身份校验 --- 判断是不是支付公司通知你
        String hmac = request.getParameter("hmac");
        String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
                "keyValue");

        // 自己对上面数据进行加密 --- 比较支付公司发过来hamc
        boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
                r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
                r8_MP, r9_BType, keyValue);

        if (isValid) {
            // 响应数据有效
            if (r9_BType.equals("1")) {
                // 浏览器重定向
//                String oid = r6_Order;
                Order order = orderService.get(r6_Order);

                order.setStatus(OrderService.waitDelivery);
                order.setPayDate(new Date());
                orderService.update(order);
                model.addAttribute("o", order);
                model.addAttribute("total",r8_MP);
                return "fore/payed";
            } else if (r9_BType.equals("2")) {
                // 服务器点对点 --- 支付公司通知你
                System.out.println("付款成功！");
                // 修改订单状态 为已付款
                // 回复支付公司
                response.getWriter().print("success");
            }
        } else {
            // 数据无效
            System.out.println("数据被篡改！");
        }
        return null;
    }

    @RequestMapping("ebaopayed")
    public String ebaopayed(HttpServletRequest request, int oid, float total, Model model)
    {
        // 获得 支付必须基本数据
        Order o = orderService.get(oid);
        String orderid = o.getOrderCode();
//        String money = total+"";
        String money = "0.01";
        // 银行
        String pd_FrpId = request.getParameter("pd_FrpId");
        System.out.println(pd_FrpId);

        // 发给支付公司需要哪些数据
        String p0_Cmd = "Buy";
        String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
        String p2_Order = orderid;
        String p3_Amt = money;
        String p4_Cur = "CNY";
        String p5_Pid = "";
        String p6_Pcat = "";
        String p7_Pdesc = "";
        // 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
        // 第三方支付可以访问网址
        String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("callback");
        String p9_SAF = "";
        String pa_MP = total+"";
        String pr_NeedResponse = "1";
        // 加密hmac 需要密钥
        String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
                "keyValue");
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
                p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
                pd_FrpId, pr_NeedResponse, keyValue);


        String url = "https://www.yeepay.com/app-merchant-proxy/node?pd_FrpId="+pd_FrpId+
                "&p0_Cmd="+p0_Cmd+
                "&p1_MerId="+p1_MerId+
                "&p2_Order="+p2_Order+
                "&p3_Amt="+p3_Amt+
                "&p4_Cur="+p4_Cur+
                "&p5_Pid="+p5_Pid+
                "&p6_Pcat="+p6_Pcat+
                "&p7_Pdesc="+p7_Pdesc+
                "&p8_Url="+p8_Url+
                "&p9_SAF="+p9_SAF+
                "&pa_MP="+pa_MP+
                "&pr_NeedResponse="+pr_NeedResponse+
                "&hmac="+hmac;

        //重定向到第三方支付平台
//        response.sendRedirect(url);
        System.out.println("yes");
        return "redirect:"+url;
    }

    @RequestMapping("forepayed")
    public String payed(int oid, float total, Model model) {
        /**
         * @author litengbin
         * @method payed
         * @param       [oid, total, model]
         * @return java.lang.String
         * @date 2018/2/24 15:52
         * @version 1.0.0
         * @description 支付
         */
        Order order = orderService.get(oid);
        order.setStatus(OrderService.waitDelivery);
        order.setPayDate(new Date());
        orderService.update(order);
        model.addAttribute("o", order);
        return "fore/payed";
    }

    @RequestMapping("forebought")
    public String bought(Model model, HttpSession session) {
        /**
         * @author litengbin
         * @method bought
         * @param       [model, session]
         * @return java.lang.String
         * @date 2018/2/24 16:09
         * @version 1.0.0
         * @description 查看我的订单
         */
        User user = (User) session.getAttribute("user");
        List<Order> os = orderService.list(user.getId(), OrderService.delete);
        orderItemService.fill(os);
        model.addAttribute("os", os);
        return "fore/bought";
    }

    @RequestMapping("foreconfirmPay")
    public String confirmPay(Model model, int oid) {
        /**
         * @author litengbin
         * @method confirmPay
         * @param       [model, oid]
         * @return java.lang.String
         * @date 2018/2/24 16:10
         * @version 1.0.0
         * @description 确认收货
         */
        Order o = orderService.get(oid);
        orderItemService.fill(o);
        model.addAttribute("o", o);
        return "fore/confirmPay";
    }

    @RequestMapping("foreorderConfirmed")
    public String orderConfirmed(Model model, int oid) {
        /**
         * @author litengbin
         * @method orderConfirmed
         * @param       [model, oid]
         * @return java.lang.String
         * @date 2018/2/24 16:14
         * @version 1.0.0
         * @description 确认收货
         */
        Order o = orderService.get(oid);
        o.setStatus(OrderService.waitReview);
        o.setConfirmDate(new Date());
        orderService.update(o);
        return "fore/orderConfirmed";
    }

    @RequestMapping("forereview")
    public String review(Model model, int oid) {
        /**
         * @author litengbin
         * @method review
         * @param       [model, oid]
         * @return java.lang.String
         * @date 2018/2/24 16:29
         * @version 1.0.0
         * @description 产品评价
         */
        Order o = orderService.get(oid);
        orderItemService.fill(o);
        Product p = o.getOrderItems().get(0).getProduct();
        List<Review> reviews = reviewService.list(p.getId());
        productService.setSaleAndReviewNumber(p);
        model.addAttribute("p", p);
        model.addAttribute("o", o);
        model.addAttribute("reviews", reviews);
        return "fore/review";
    }

    @RequestMapping("foredoreview")
    public String doreview(Model model, HttpSession session, @RequestParam("oid") int oid, @RequestParam("pid") int pid, String content) {
        /**
         * @author litengbin
         * @method doreview
         * @param       [model, session, oid, pid, content]
         * @return java.lang.String
         * @date 2018/2/24 16:42
         * @version 1.0.0
         * @description 用户提交评价
         */
        Order o = orderService.get(oid);
        o.setStatus(OrderService.finish);
        orderService.update(o);
        Product p = productService.get(pid);
        content = HtmlUtils.htmlEscape(content);//转义
        User user = (User) session.getAttribute("user");
        Review review = new Review();
        review.setContent(content);
        review.setPid(pid);
        review.setCreateDate(new Date());
        review.setUid(user.getId());
        reviewService.add(review);
        return "redirect:forereview?oid=" + oid + "&showonly=true";
    }

    @RequestMapping("foredeleteOrder")
    @ResponseBody
    public String deleteOrder(Model model, int oid) {
        /**
         * @author litengbin
         * @method deleteOrder
         * @param       [model, oid]
         * @return java.lang.String
         * @date 2018/2/24 17:52
         * @version 1.0.0
         * @description 删除订单
         */
        Order o = orderService.get(oid);
        o.setStatus(OrderService.delete);
        orderService.update(o);
        return "success";
    }
}
