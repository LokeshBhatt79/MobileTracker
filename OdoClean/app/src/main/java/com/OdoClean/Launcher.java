package com.example.mayank.odoclean;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class Launcher extends AppCompatActivity{
    private Timer timer;
    private ProgressBar progressBar;
    private int i=0;
    //TextView textView;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        // textView=(TextView)findViewById(R.id.textView);
        //textView.setText("");

        //progressBar.setProgressTintList((ColorStateList.valueOf(Color.RED)));
        final long period = 100;
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //this repeats every 100 ms
                if (i<100){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //textView.setText(String.valueOf(i)+"%");
                        }
                    });
                    progressBar.setProgress(i);
                    i=i+5;
                }else{
                    //closing the timer
                    timer.cancel();
                    Intent intent =new Intent(Launcher.this,LoginActivity.class);
                    startActivity(intent);
                    // close this activity
                    finish();
                }
            }
        }, 0, period);
    }

}

