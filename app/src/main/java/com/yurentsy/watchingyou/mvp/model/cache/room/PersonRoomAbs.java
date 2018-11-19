package com.yurentsy.watchingyou.mvp.model.cache.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.yurentsy.watchingyou.mvp.model.entity.Person;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class PersonRoomAbs extends RoomDatabase {
    public abstract PersonRoomDao photoDao();
}
