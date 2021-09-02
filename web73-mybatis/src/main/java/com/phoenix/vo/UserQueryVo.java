package com.phoenix.vo;

import com.phoenix.pojo.User;
import java.io.Serializable;
import java.util.List;

public class UserQueryVo implements Serializable {
    private static final long serialVersionUID = 6944260679745167502L;

    private List<Integer> idList;

    private Integer[] ids;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
