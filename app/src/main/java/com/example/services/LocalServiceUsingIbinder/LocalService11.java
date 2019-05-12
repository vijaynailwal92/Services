package com.example.services.LocalServiceUsingIbinder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

public class LocalService11 extends Service {
    private int mRandomNumber;
    private boolean mIsRandomGeneratorOn;
    private final int MIN = 0;
    private final int MAX = 100;
    public static final int GET_COUNT = 0;

    //this will return the myservice instance
    // Binder is abstract class which implements Ibinder Interface
    class MyServiceBinder extends Binder {
        public LocalService11 getService() {
            return LocalService11.this;
        }
    }

    private IBinder mBinder = new MyServiceBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("LocalService11.java ", "In OnBind");
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e("LocalService11.java ", "In OnReBind");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("LocalService11.java ", "Service Started");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomNumberGenerator();
        Log.e("LocalService11.java ", "Service Destroyed");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("LocalService11.java ", "In onStartCommend, thread id: " + Thread.currentThread().getId());
        mIsRandomGeneratorOn = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                startRandomNumberGenerator();
            }
        }).start();
        return START_STICKY;
    }

    private void startRandomNumberGenerator() {
        while (mIsRandomGeneratorOn) {
            try {
                Thread.sleep(1000);
                if (mIsRandomGeneratorOn) {
                    mRandomNumber = new Random().nextInt(MAX) + MIN;
                    Log.e("LocalService11.java ", "Thread id: " + Thread.currentThread().getId() + ", Random Number: " + mRandomNumber);
                }
            } catch (InterruptedException e) {
                Log.e("LocalService11.java ", "Thread Interrupted");
            }

        }
    }

    private void stopRandomNumberGenerator() {
        mIsRandomGeneratorOn = false;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("LocalService11.java ", "In onUnbind");
        return super.onUnbind(intent);
    }

    public int getRandomNumber() {
        return mRandomNumber;
    }
}
