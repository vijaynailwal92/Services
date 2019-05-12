package com.example.services.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }
    // is depricated
//    @Override
//    public void onStart(Intent intent, int startId) {
//        super.onStart(intent, startId);
//    }
//
    //mandatory override
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //when ever you start the service this method will execute.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("onStartCommand","thred id = "+Thread.currentThread().getId());
        //1)manually stop the service.
//        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("destroy ","service destroyed");
    }

}
