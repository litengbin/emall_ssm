package com.ltb.emall.util;

/**
 * ClassName: Page
 * Description: 分页类
 * User: litengbin
 * Date: 2018/2/13 12:44
 * Version: 1.0.0
 */
public class Page {
    private int start;//列表第一个
    private int count;//显示个数
    private int total;//总数
    private String param;
    private static final int defaultCount = 9;//默认显示个数

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Page() {
        this.count = defaultCount;
    }

    public Page(int start, int count) {
        this.start = start;
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPage() {
        /**
         * @author      litengbin
         * @method      getTotalPage
         * @param       []
         * @return      int
         * @date        2018/2/13 21:26
         * @version     1.0.0
         * @description 总页数
         */
        int totalPage;
        if (0 == total % count) {
            totalPage = total / count;
        } else {
            totalPage = total / count + 1;
        }
        if (0 == totalPage) {
            totalPage = 1;
        }
        return totalPage;
    }

    public int getLast() {
        /**
         * @author      litengbin
         * @method      getLast
         * @param       []
         * @return      int
         * @date        2018/2/13 21:26
         * @version     1.0.0
         * @description 最后一页开始第一个
         */
        int last;
        if (0 == total % count) {
            last = total - count;
        } else {
            last = total - total % count;
        }
        last = last < 0 ? 0 : last;
        return last;
    }

    public boolean isHasPreviouse() {
        /**
         * @author      litengbin
         * @method      isHasPreviouse
         * @param       []
         * @return      boolean
         * @date        2018/2/13 21:26
         * @version     1.0.0
         * @description 是否有前一个
         */
        if (0 == start) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isHasNext() {
        /**
         * @author      litengbin
         * @method      isHasNext
         * @param       []
         * @return      boolean
         * @date        2018/2/13 21:26
         * @version     1.0.0
         * @description 是否有后一个
         */
        if (start == getLast()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", count=" + count +
                ", total=" + total +
                ", param='" + param + '\'' +
                ", getStart()=" + getStart() +
                ", getCount()=" + getCount() +
                ", getTotal()=" + getTotal() +
                ", getLast()=" + getLast() +
                ", getParam()=" + getParam() +
                ", isHasPrevious()=" + isHasPreviouse() +
                ", isHasNext()=" + isHasNext() +
                ", getTotalPage()=" + getTotalPage() +
                '}';
    }
}
