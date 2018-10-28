package com.yurentsy.watchingyou.mvp.model.repo;

import com.yurentsy.watchingyou.mvp.model.entity.User;

import io.reactivex.Observable;

public interface Repo {
    Observable<User> getUser(String id);
}
