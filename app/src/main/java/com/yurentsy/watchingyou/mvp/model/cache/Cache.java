package com.yurentsy.watchingyou.mvp.model.cache;

import com.yurentsy.watchingyou.mvp.model.entity.User;

import io.reactivex.Observable;

public interface Cache {
    void putUser(User user);

    Observable<User> getUser(String id);
}
