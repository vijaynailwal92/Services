package com.example.services.RemoteServiceUsingMessenger;

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

import com.example.services.LocalServiceUsingIbinder.LocalServiceActivity1;
import com.example.services.R;

public class RemoteBindingActivity extends AppCompatActivity {
    private static final String TAG = LocalServiceActivity1.class.getSimpleName();
    TextView textView;
    private RemoteService myService;
    private boolean isServiceBound;
    private ServiceConnection serviceConnection;
    private Intent serviceIntent;
    private boolean mStopLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_binding);
        Log.e("LocalServiceActivity1.java ", "LocalServiceActivity1 thread id: " + Thread.currentThread().getId());
        serviceIntent = new Intent(getApplicationContext(), RemoteService.class);

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
        }
    }
}