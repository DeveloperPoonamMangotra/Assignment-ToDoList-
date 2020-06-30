package com.example.roomlistpractice.presentation.ui.detail;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.roomlistpractice.data.repository.TaskRepository;
import com.example.roomlistpractice.databinding.ActivityDetailBinding;
import com.example.roomlistpractice.domain.entity.TaskEntity;
import com.example.roomlistpractice.framework.db.AppDatabase;
import com.example.roomlistpractice.presentation.ui.BaseActivity;
import com.example.roomlistpractice.presentation.util.ViewModelFactory;

import java.lang.ref.WeakReference;

public class DetailActivity extends BaseActivity {

    private ActivityDetailBinding activityDetailBinding;
    private DetailViewModel detailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(activityDetailBinding.getRoot());
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "TaskDB").build();
        ViewModelFactory mViewModelFactory = new ViewModelFactory(new TaskRepository(db));
        detailViewModel = new ViewModelProvider(this, mViewModelFactory).get(DetailViewModel.class);

        handleUI();
    }

    private void handleUI() {
        activityDetailBinding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskEntity taskEntity = new TaskEntity();
                taskEntity.setTaskCompleted(false);
                taskEntity.setTaskName(activityDetailBinding.titleEditText.getText().toString());
                taskEntity.setTaskDescription(activityDetailBinding.descEditText.getText().toString());
                new AgentAsyncTask(DetailActivity.this, taskEntity).execute();

            }
        });
        activityDetailBinding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    public class AgentAsyncTask extends AsyncTask<Integer, Void, Void> {

        //Prevent leak
        private WeakReference<Activity> weakActivity;
        private TaskEntity taskEntity;

        public AgentAsyncTask(Activity activity, TaskEntity taskEntity) {
            weakActivity = new WeakReference<>(activity);
            this.taskEntity = taskEntity;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            detailViewModel.addTask(taskEntity);
            Log.e("entity", taskEntity.toString());
            return null;
        }

        @Override
        protected void onPostExecute(Void agentsCount) {
            Activity activity = weakActivity.get();
            if (activity == null) {
                return;
            }
            detailViewModel.responseData.observe(DetailActivity.this, new Observer<DetailViewModel.ResponseData>() {
                @Override
                public void onChanged(DetailViewModel.ResponseData responseData) {
                    if (responseData.success == 1) {
                        showToast(responseData.message);
                        setResult(RESULT_OK);
                        finish();
                    } else
                        showToast(responseData.message);
                }
            });
        }
    }


}
