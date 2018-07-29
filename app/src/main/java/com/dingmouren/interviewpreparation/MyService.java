package com.dingmouren.interviewpreparation;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by dingmouren on 2018/7/29.
 */

public class MyService extends Service {

    private static final String TAG = MyService.class.getName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"onBind");
        return null;
    }

    /**
     * 服务创建时调用
     */
    @Override
    public void onCreate() {
        Log.e(TAG,"onCreate");
        super.onCreate();
    }

    /**
     * 每次服务启动时调用
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 服务销毁时调用
     */
    @Override
    public void onDestroy() {
        Log.e(TAG,"onDestroy");
        super.onDestroy();
    }
}
