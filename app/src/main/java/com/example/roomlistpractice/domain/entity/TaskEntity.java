package com.example.roomlistpractice.domain.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "toDoList")
public class TaskEntity {

    @PrimaryKey(autoGenerate = true)
    private int taskId;

    @ColumnInfo(name = "taskName")
    private String taskName;

    @ColumnInfo(name = "taskDescription")
    private String taskDescription;

    @ColumnInfo(name = "taskCompleted")
    private Boolean taskCompleted = false;


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Boolean getTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(Boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }


    @Override
    public String toString() {
        return "TaskEntity{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskCompleted=" + taskCompleted +
                '}';
    }
}
