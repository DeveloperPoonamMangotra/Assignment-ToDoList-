package com.example.roomlistpractice.presentation.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomlistpractice.R;
import com.example.roomlistpractice.domain.entity.TaskEntity;
import com.example.roomlistpractice.presentation.util.ListOperations;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private MainActivity mainActivity;
    private ArrayList<TaskEntity> taskEntities;

    public MainAdapter(MainActivity mainActivity, ArrayList<TaskEntity> taskEntities){
        this.mainActivity = mainActivity;
        this.taskEntities = ListOperations.getInstance().sortList(taskEntities);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_task,parent,false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.setData();
    }

    @Override
    public int getItemCount() {
        return taskEntities.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private TextView descriptionTextView;
        private CheckBox doneCheckBox;
        private ConstraintLayout constraintLayout;

        @SuppressLint("CutPasteId")
        MainViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTV);
            descriptionTextView = itemView.findViewById(R.id.descriptionTV);
            doneCheckBox = itemView.findViewById(R.id.checkbox);
            constraintLayout = itemView.findViewById(R.id.task_layout);
        }

        void setData(){
            TaskEntity taskEntity = taskEntities.get(getAdapterPosition());
            titleTextView.setText(taskEntity.getTaskName());
            descriptionTextView.setText(taskEntity.getTaskDescription());
            doneCheckBox.setChecked(taskEntity.getTaskCompleted());

            if (taskEntity.getTaskCompleted())
                constraintLayout.setBackgroundColor(mainActivity.getResources().getColor(R.color.colorLightGray));
            else
                constraintLayout.setBackgroundColor(mainActivity.getResources().getColor(R.color.colorWhite));

            doneCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                taskEntity.setTaskCompleted(isChecked);
                mainActivity.updateTask(taskEntity);
            });
        }
    }
}
