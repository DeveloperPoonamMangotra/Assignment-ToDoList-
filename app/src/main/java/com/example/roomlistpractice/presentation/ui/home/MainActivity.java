package com.example.roomlistpractice.presentation.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.roomlistpractice.data.repository.TaskRepository;
import com.example.roomlistpractice.databinding.ActivityMainBinding;
import com.example.roomlistpractice.domain.entity.TaskEntity;
import com.example.roomlistpractice.framework.db.AppDatabase;
import com.example.roomlistpractice.presentation.ui.BaseActivity;
import com.example.roomlistpractice.presentation.ui.detail.DetailActivity;
import com.example.roomlistpractice.presentation.util.ListOperations;
import com.example.roomlistpractice.presentation.util.ViewModelFactory;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends BaseActivity {

    private MainViewModel mMainViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "TaskDB").build();
        ViewModelFactory mViewModelFactory = new ViewModelFactory(new TaskRepository(db));
        mMainViewModel = new ViewModelProvider(this, mViewModelFactory).get(MainViewModel.class);

        handleUI();
    }

    private void handleUI() {
        new AgentAsyncTask(this, null).execute();

        activityMainBinding.searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                activityMainBinding.taskRecycler.setAdapter(new MainAdapter(MainActivity.this,ListOperations.getInstance().searchList(Objects.requireNonNull(mMainViewModel.arrayListLiveData.getValue()),s.toString())));
            }
        });

        activityMainBinding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, DetailActivity.class), 110);
            }
        });

    }

    public void updateTask(TaskEntity taskEntity){
        new AgentAsyncTask(this, taskEntity).execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110 && resultCode == RESULT_OK) {
            new AgentAsyncTask(this, null).execute();
        }
    }

    public class AgentAsyncTask extends AsyncTask<Integer, Void, Void> {

        //Prevent leak
        private WeakReference<Activity> weakActivity;
        private TaskEntity taskEntity;
        private Activity activity;

        public AgentAsyncTask(Activity activity, TaskEntity taskEntity) {
            weakActivity = new WeakReference<>(activity);
            this.taskEntity = taskEntity;
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            if (taskEntity == null)
                mMainViewModel.getList();
            else
                mMainViewModel.updateTask(taskEntity);
            return null;
        }

        @Override
        protected void onPostExecute(Void agentsCount) {
            Activity activity = weakActivity.get();
            if (activity == null) {
                return;
            }
            if (taskEntity == null)
            mMainViewModel.arrayListLiveData.observe(MainActivity.this, taskEntities -> {
                if (taskEntities.size() > 0) {
                    activityMainBinding.searchEditText.setVisibility(View.VISIBLE);
                    activityMainBinding.taskRecycler.setVisibility(View.VISIBLE);
                    activityMainBinding.taskRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                    activityMainBinding.taskRecycler.setAdapter(new MainAdapter(MainActivity.this, taskEntities));
                }
            });
            else{
                new AgentAsyncTask(activity, null).execute();
            }
        }
    }
}
