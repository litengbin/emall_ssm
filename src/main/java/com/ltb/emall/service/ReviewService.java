package com.ltb.emall.service;

import com.ltb.emall.pojo.Review;

import java.util.List;

/**
 * ClassName: ReviewService
 * Description: 评价业务接口
 * User: litengbin
 * Date: 2018/2/19 20:25
 * Version: 1.0.0
 */
public interface ReviewService {
    public void add(Review review);

    public void update(Review review);

    public void delete(int id);

    public Review get(int id);

    public List<Review> list(int pid);

    public int count(int pid);

}
