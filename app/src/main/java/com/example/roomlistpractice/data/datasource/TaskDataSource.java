package com.example.roomlistpractice.data.datasource;

import com.example.roomlistpractice.domain.entity.TaskEntity;

import java.util.List;

public interface TaskDataSource {

    List<TaskEntity> getTaskList();

    void addTask(TaskEntity taskEntity);

    void updateTask(TaskEntity taskEntity);

}
