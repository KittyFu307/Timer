package com.example.a3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnStart=null;
    Button btnPause=null;
    Button btnStop=null;
    TextView title=null;
    TextView tvtime=null;
    TextView myTextView;
    EditText myEditText;


    //Timer
    private long currentSecond = 0;//Current milliseconds
    private long lastSecond=0;
    private int workout=0;
    private Handler mHandler = new Handler();
    public void startTime() {
        Toast.makeText(this, "begin", Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(mRunnable, 1000);
        btnStart.setEnabled(false);
        btnPause.setEnabled(true);
        btnStop.setEnabled(true);
    }
    public void pauseTime(){
        Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();
        mHandler.removeCallbacks(mRunnable);
        btnStart.setEnabled(true);
        btnPause.setEnabled(false);
        btnStop.setEnabled(false);
    }
    public void stopTime() {
        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
        mHandler.removeCallbacks(mRunnable);
        tvtime.setText(TimeUtil.getFormatHMS(0));
        btnStart.setEnabled(true);
        btnPause.setEnabled(false);
        btnStop.setEnabled(false);
        lastSecond=currentSecond;
        currentSecond=0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int orientation=getResources().getConfiguration().orientation;
        if(orientation== Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.main2);
        }else{
            setContentView(R.layout.activity_main);
            myEditText=findViewById(R.id.myEditview);
            myTextView=findViewById(R.id.myTextView);
            btnStop=findViewById(R.id.btnstop);

        }
        btnStart=findViewById(R.id.btnstart);
        btnPause=findViewById(R.id.btnpause);
        btnStop=findViewById(R.id.btnstop);
        title=findViewById(R.id.title);
        tvtime=findViewById(R.id.tvtime);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime();
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTime();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTime();
                title.setText("You spent "+TimeUtil.getFormatHMS(lastSecond)+" on "+myEditText.getText().toString()+" last time.");
            }
        });
    }
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            currentSecond = currentSecond + 1000;
            tvtime.setText(TimeUtil.getFormatHMS(currentSecond));
            mHandler.postDelayed(this, 1000);
        }
    };
}
