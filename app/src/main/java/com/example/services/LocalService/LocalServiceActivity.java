package com.example.services.LocalService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.services.R;

public class LocalServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_service);
    }
    public void view_click(View view) {
        switch (view.getId()) {
            case R.id.buttonThreadStarter:
                startService(new Intent(getApplicationContext(), Localservice.class));
                break;
            case R.id.buttonStopthread:
                stopService(new Intent(getApplicationContext(), Localservice.class));
                break;

        }
    }
}
