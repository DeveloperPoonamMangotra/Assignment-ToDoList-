package com.example.roomlistpractice.framework.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.roomlistpractice.domain.dao.TaskDao;
import com.example.roomlistpractice.domain.entity.TaskEntity;

@Database(entities = {TaskEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}