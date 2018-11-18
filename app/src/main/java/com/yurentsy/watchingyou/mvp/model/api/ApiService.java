package com.yurentsy.watchingyou.mvp.model.api;

import com.yurentsy.watchingyou.mvp.model.entity.Person;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("employees")
    Observable<List<Person>> getPersons();
}
