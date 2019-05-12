package com.example.services.RemoteServiceUsingMessenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

public class RemoteService extends Service {
    private int mRandomNumber;
    private boolean mIsRandomGeneratorOn;
    private final int MIN=0;
    private final int MAX=100;
    public static final int GET_COUNT=0;

    private class RandomNumberRequestHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.e("RemoteService.java ","Message intercepted");
            switch (msg.what){
                case GET_COUNT: Message  messageSendRandomNumber=Message.obtain(null,GET_COUNT);
                    messageSendRandomNumber.arg1=getRandomNumber();
                    try{
                        Log.i("RemoteService.java ","Replaying with random number to requester");
                        msg.replyTo.send(messageSendRandomNumber);
                    }catch (RemoteException e){
                        Log.i("RemoteService.java ",""+e.getMessage());
                    }
            }
            super.handleMessage(msg);
        }
    }
    private Messenger randomNumberMessenger=new Messenger(new RandomNumberRequestHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("RemoteService.java ","In OnBind");
            return randomNumberMessenger.getBinder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e("RemoteService.java ","In OnReBind");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("RemoteService.java ","Service Started");
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomNumberGenerator();
        Log.e("RemoteService.java ","Service Destroyed");
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("RemoteService.java ","In onStartCommend, thread id: "+Thread.currentThread().getId());
        mIsRandomGeneratorOn =true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                startRandomNumberGenerator();
            }
        }).start();
        return START_STICKY;
    }

    private void startRandomNumberGenerator(){
        while (mIsRandomGeneratorOn){
            try{
                Thread.sleep(1000);
                if(mIsRandomGeneratorOn){
                    mRandomNumber =new Random().nextInt(MAX)+MIN;
                    Log.e("RemoteService.java ","Thread id: "+Thread.currentThread().getId()+", Random Number: "+ mRandomNumber);
                }
            }catch (InterruptedException e){
                Log.e("RemoteService.java ","Thread Interrupted");
            }

        }
    }

    private void stopRandomNumberGenerator(){
        mIsRandomGeneratorOn =false;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("RemoteService.java ","In onUnbind");
        return super.onUnbind(intent);
    }

    public int getRandomNumber(){
        return mRandomNumber;
    }
}
