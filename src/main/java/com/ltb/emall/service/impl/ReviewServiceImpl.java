package com.ltb.emall.service.impl;

import com.ltb.emall.mapper.ReviewMapper;
import com.ltb.emall.pojo.Review;
import com.ltb.emall.pojo.ReviewExample;
import com.ltb.emall.pojo.User;
import com.ltb.emall.service.ReviewService;
import com.ltb.emall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ReviewServiceImpl
 * Description: review业务接口实现类
 * User: litengbin
 * Date: 2018/2/19 20:27
 * Version: 1.0.0
 */
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    UserService userService;

    @Override
    public void add(Review review) {
        /**
         * @author litengbin
         * @method add
         * @param       [review]
         * @return void
         * @date 2018/2/19 20:28
         * @version 1.0.0
         * @description 新增评价
         */
        reviewMapper.insert(review);
    }

    @Override
    public void update(Review review) {
        /**
         * @author litengbin
         * @method update
         * @param       [review]
         * @return void
         * @date 2018/2/19 20:28
         * @version 1.0.0
         * @description 修改评价
         */
        reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public void delete(int id) {
        /**
         * @author litengbin
         * @method delete
         * @param       [id]
         * @return void
         * @date 2018/2/19 20:29
         * @version 1.0.0
         * @description 删除评价
         */
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Review get(int id) {
        /**
         * @author litengbin
         * @method get
         * @param       [id]
         * @return com.ltb.emall.pojo.Review
         * @date 2018/2/19 20:29
         * @version 1.0.0
         * @description 获取评价
         */
        return reviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Review> list(int pid) {
        /**
         * @author litengbin
         * @method list
         * @param       [pid]
         * @return java.util.List<com.ltb.emall.pojo.Review>
         * @date 2018/2/19 20:38
         * @version 1.0.0
         * @description 获取评价列表
         */
        ReviewExample example = new ReviewExample();
        example.createCriteria().andPidEqualTo(pid);
        example.setOrderByClause("id desc");
        List<Review> rs = reviewMapper.selectByExample(example);
        setUser(rs);
        return rs;
    }

    public void setUser(List<Review> reviews) {
        /**
         * @author litengbin
         * @method setUser
         * @param       [reviews]
         * @return void
         * @date 2018/2/19 20:38
         * @version 1.0.0
         * @description set用户
         */
        for (Review review : reviews) {
            setUser(review);
        }
    }

    private void setUser(Review review) {
        /**
         * @author litengbin
         * @method setUser
         * @param       [review]
         * @return void
         * @date 2018/2/19 20:38
         * @version 1.0.0
         * @description set用户
         */
        int uid = review.getUid();
        User user = userService.get(uid);
        review.setUser(user);
    }

    @Override
    public int count(int pid) {
        /**
         * @author litengbin
         * @method count
         * @param       [pid]
         * @return int
         * @date 2018/2/19 20:39
         * @version 1.0.0
         * @description 获取产品评价数量
         */
        return list(pid).size();
    }
}
