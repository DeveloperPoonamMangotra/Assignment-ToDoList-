package com.example.roomlistpractice.data.repository;

import com.example.roomlistpractice.data.datasource.TaskDataSource;
import com.example.roomlistpractice.domain.entity.TaskEntity;
import com.example.roomlistpractice.framework.db.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements TaskDataSource {

    private final AppDatabase appDatabase;

    public TaskRepository(AppDatabase appDatabase) {
       this.appDatabase = appDatabase;
    }

    @Override
    public List<TaskEntity> getTaskList(){
        final List<TaskEntity> allTask = appDatabase.taskDao().getAllTask();
        return allTask;
    }

    @Override
    public void addTask(TaskEntity taskEntity) {
        appDatabase.taskDao().insertAll(taskEntity);
    }

    @Override
    public void updateTask(TaskEntity taskEntity) {
        appDatabase.taskDao().update(taskEntity);
    }

}
