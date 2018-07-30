package com.dingmouren.interviewpreparation.z_classes;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by dingmouren on 2018/7/29.
 */

public class MyDownLoadService extends Service {

    private static final String TAG = "MyDownLoadService";

    private DownLoadBinder downLoadBinder = new DownLoadBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return downLoadBinder;
    }

    class DownLoadBinder extends Binder{
        private static final String TAG = "DownLoadBinder";

        public void startDownLoad(){
            Log.e(TAG,"startDownLoad--线程:"+Thread.currentThread().getName());
        }

        public int getProgress(){
            Log.e(TAG,"getProgress");
            return 1;
        }
    }
}
