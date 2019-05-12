package com.example.services.LocalServiceUsingIbinder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.services.R;

public class LocalServiceActivity1 extends AppCompatActivity {
    private static final String TAG = LocalServiceActivity1.class.getSimpleName();
    TextView textView;
    private LocalService11 myService;
    private boolean isServiceBound;
    private ServiceConnection serviceConnection;
    private Intent serviceIntent;
    private boolean mStopLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_service1);
        textView = (TextView) findViewById(R.id.textViewthreadCount);
        Log.i("LocalServiceActivity1.java ", "LocalServiceActivity1 thread id: " + Thread.currentThread().getId());
        serviceIntent = new Intent(getApplicationContext(), LocalService11.class);

    }


    public void view_click(View view) {
        switch (view.getId()) {

            case R.id.start_service:
                mStopLoop = true;
                startService(serviceIntent);
                break;
            case R.id.stop_servcie:
                stopService(serviceIntent);
                break;
            case R.id.buttonBindService:
                bindService();
                break;
            case R.id.buttonUnBindService:
                unbindService();
                break;
            case R.id.buttonGetRandomNumber:
                setRandomNumber();
                break;
        }
    }

    private void bindService() {
        if (serviceConnection == null) {
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    LocalService11.MyServiceBinder myServiceBinder = (LocalService11.MyServiceBinder) iBinder;
                    //this will get me the service intent
                    myService = myServiceBinder.getService();
                    isServiceBound = true;
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    isServiceBound = false;
                }
            };
        }

        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    private void unbindService() {
        if (isServiceBound) {
            unbindService(serviceConnection);
            isServiceBound = false;
        }
    }

    private void setRandomNumber() {
        if (isServiceBound) {
            textView.setText("Random number: " + myService.getRandomNumber());
        } else {
            textView.setText("Service not bound");
        }
    }
}

