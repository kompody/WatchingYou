package com.yurentsy.watchingyou.mvp.model.cache.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.yurentsy.watchingyou.mvp.model.entity.Person;

import java.util.List;


@Dao
public interface PersonRoomDao {
    @Query("SELECT * FROM person")
    List<Person> getList();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Person person);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Person> list);

    @Update
    void update(Person person);

    @Delete
    void delete(Person person);

    @Query("SELECT MAX(id) FROM person")
    int getMaxId();
}
