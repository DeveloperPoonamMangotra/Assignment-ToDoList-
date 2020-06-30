package com.example.roomlistpractice.domain.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomlistpractice.domain.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM toDoList")
    List<TaskEntity> getAllTask();

    @Query("SELECT * FROM toDoList WHERE taskId IN (:userIds)")
    List<TaskEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM toDoList WHERE taskName LIKE :first AND " +
           "taskDescription LIKE :last LIMIT 1")
    TaskEntity findByName(String first, String last);

    @Insert
    void insertAll(TaskEntity... taskEntities);

    @Update
    void update(TaskEntity TaskEntity);

    @Delete
    void delete(TaskEntity TaskEntity);
}