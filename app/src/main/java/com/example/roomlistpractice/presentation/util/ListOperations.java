package com.example.roomlistpractice.presentation.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.roomlistpractice.domain.entity.TaskEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListOperations {

    private static ListOperations listOperations;

    public static ListOperations getInstance(){
        if (listOperations==null)
            listOperations = new ListOperations();

        return listOperations;
    }

    public ArrayList<TaskEntity> sortList(ArrayList<TaskEntity> taskEntities){

        Collections.sort(taskEntities, (taskEntity1, taskEntity2) -> Boolean.compare(taskEntity1.getTaskCompleted(), taskEntity2.getTaskCompleted()));
       // Collections.reverse(taskEntities);
        return taskEntities;
    }

    public ArrayList<TaskEntity> searchList(ArrayList<TaskEntity> taskEntities, String title){
        ArrayList<TaskEntity> outputList = new ArrayList<>();
        for (TaskEntity model:
                taskEntities) {
           if (model.getTaskName().toLowerCase().contains(title.toLowerCase())){
               outputList.add(model);
           }
        }
        return outputList;
    }
}
