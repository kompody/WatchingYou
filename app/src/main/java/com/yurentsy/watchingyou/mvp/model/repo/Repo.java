package com.yurentsy.watchingyou.mvp.model.repo;

import android.database.Observable;

import com.yurentsy.watchingyou.mvp.model.entity.User;

public interface Repo {
    Observable<User> getUser(String id);
}
