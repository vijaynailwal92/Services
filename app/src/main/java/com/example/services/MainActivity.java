package com.example.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.services.LocalService.LocalServiceActivity;
import com.example.services.LocalServiceUsingIbinder.LocalServiceActivity1;
import com.example.services.RemoteServiceUsingMessenger.RemoteBindingActivity;
import com.example.services.Service.ServcieLifeCycle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void view_click(View view) {
        switch (view.getId()) {
            case R.id.service_lifecycle:
                startActivity(new Intent(getApplicationContext(), ServcieLifeCycle.class));
                break;
            case R.id.localbinding:
                startActivity(new Intent(getApplicationContext(), LocalServiceActivity.class));
                break;

            case R.id.local_binding_usingIbinder:
                startActivity(new Intent(getApplicationContext(), LocalServiceActivity1.class));
                break;
            case R.id.remotebinding:
                startActivity(new Intent(getApplicationContext(), RemoteBindingActivity.class));
                break;

        }
    }

}
