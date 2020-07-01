package com.liuhang.mybatis.entity;

import java.io.Serializable;
import java.util.List;

public class QueryVo implements Serializable {

    private User user;
    // 如果还有其他的查询条件，就可以一并封装进来

    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
