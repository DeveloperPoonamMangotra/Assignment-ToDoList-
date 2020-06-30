package com.example.roomlistpractice.presentation.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomlistpractice.data.datasource.TaskDataSource;
import com.example.roomlistpractice.domain.entity.TaskEntity;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    private final TaskDataSource mDataSource;

    public MainViewModel(TaskDataSource dataSource) {
        mDataSource = dataSource;
    }

    MutableLiveData<ArrayList<TaskEntity>> arrayListLiveData = new MutableLiveData<>();
    MutableLiveData<ArrayList<TaskEntity>> searchListLiveData = new MutableLiveData<>();

    void getList() {
        try {
            arrayListLiveData.postValue(new ArrayList<TaskEntity>(mDataSource.getTaskList()));
        } catch (Exception e) {
            Log.e("Exceptions", e.toString());
        }
    }

    void updateTask(TaskEntity taskEntity) {
        try {
            mDataSource.updateTask(taskEntity);
        } catch (Exception e) {
            Log.e("Exceptions", e.toString());
        }
    }


}
