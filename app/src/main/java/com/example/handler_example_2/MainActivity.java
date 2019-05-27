package com.example.handler_example_2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    MyThread mythread;
    @BindView(R.id.btnsend)
    Button btnsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mythread = new MyThread();
        mythread.start();
    }

    @OnClick(R.id.btnsend)
    public void onSendClicked() {
        Log.d("msg", Thread.currentThread().getId() + " Thread");

        mythread.handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("msg", Thread.currentThread().getId() + " Thread");
            }
        });
    }
}

class MyThread extends Thread {

    Handler handler;

    public MyThread() {
        super("Background");
    }

    @Override
    public void run() {
        Looper.prepare();
        Log.d("msg", MyThread.currentThread().getId() + " Thread");
        handler = new Handler();
        Looper.loop();
    }
}
