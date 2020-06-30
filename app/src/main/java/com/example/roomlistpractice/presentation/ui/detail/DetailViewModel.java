package com.example.roomlistpractice.presentation.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomlistpractice.data.datasource.TaskDataSource;
import com.example.roomlistpractice.domain.entity.TaskEntity;

public class DetailViewModel extends ViewModel {

    private final TaskDataSource mDataSource;

    MutableLiveData<ResponseData> responseData = new MutableLiveData<>();

    public DetailViewModel(TaskDataSource dataSource){
        mDataSource = dataSource;
    }

    public void addTask(TaskEntity taskEntity) {
        if (isValid(taskEntity)){
            try {
                mDataSource.addTask(taskEntity);
                responseData.postValue(new ResponseData(1,"AddedSuccessfully"));
            }catch (Exception e){
                responseData.postValue(new ResponseData(0,"Something went wrong "+e.toString()));
            }
        }
    }

    private boolean isValid(TaskEntity taskEntity) {
        if (taskEntity.getTaskName().isEmpty()){
            responseData.postValue(new ResponseData(0,"Name should not be empty"));
            return false;
        }else if (taskEntity.getTaskDescription().isEmpty()){
            responseData.postValue(new ResponseData(0,"Description should not be empty"));
            return false;
        }else{
            return true;
        }
    }

    class ResponseData{
        int success;
        String message;

        public ResponseData(int success, String message) {
            this.success = success;
            this.message = message;
        }
    }
}
