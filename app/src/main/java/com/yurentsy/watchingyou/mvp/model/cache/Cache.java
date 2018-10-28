package com.yurentsy.watchingyou.mvp.model.cache;

import android.database.Observable;

import com.yurentsy.watchingyou.mvp.model.entity.User;

public interface Cache {
    void putUser(User user);

    Observable<User> getUser(String id);
}
