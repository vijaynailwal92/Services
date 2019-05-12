package com.example.services.Service;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.services.R;

public class ServcieLifeCycle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servcie_life_cycle);
        Log.e("onCreate", "thread id = " + Thread.currentThread().getId());
    }


    public void view_click(View view) {
        switch (view.getId()) {
            case R.id.startservice:
                startService(new Intent(getApplicationContext(), MyService.class));
                break;

            //2nd way to stop the service explicitly
            case R.id.stop_servcie:
                stopService(new Intent(getApplicationContext(), MyService.class));
                break;

        }
    }

//    1)onCreate
//    2)onStartCommand
//    3)onDestroy
}
