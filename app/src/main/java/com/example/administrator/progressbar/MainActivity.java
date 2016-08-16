package com.example.administrator.progressbar;

import android.os.Bundle;

import com.example.administrator.progressbar.app.StarterActivity;

public class MainActivity extends StarterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        showProgressLoading("对话框呀");
//        showUnBackProgressLoading("不可取消对话框");
    }

    @Override
    protected void setupViews() {
    }
}
