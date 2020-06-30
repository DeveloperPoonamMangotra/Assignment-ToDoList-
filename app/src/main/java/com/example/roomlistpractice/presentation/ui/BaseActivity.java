package com.example.roomlistpractice.presentation.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.roomlistpractice.R;
import com.kaopiz.kprogresshud.KProgressHUD;

public class BaseActivity extends AppCompatActivity {

    private KProgressHUD progressLoader ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void showLoader(){
        progressLoader = KProgressHUD.create(this)
                .setLabel(getResources().getString(R.string.loading))
                .show();
    }

    public void hideLoader(){
        if (progressLoader!=null)
            progressLoader.dismiss();
    }
}
