package com.example.roomlistpractice.presentation.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.roomlistpractice.data.datasource.TaskDataSource;
import com.example.roomlistpractice.presentation.ui.detail.DetailViewModel;
import com.example.roomlistpractice.presentation.ui.home.MainViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final TaskDataSource mDataSource;

    public ViewModelFactory(TaskDataSource dataSource) {
        mDataSource = dataSource;
    }

    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mDataSource);
        }else if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            return (T) new DetailViewModel(mDataSource);
        }
        //noinspection unchecked
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
