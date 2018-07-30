package com.dingmouren.interviewpreparation.z_classes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

/**
 * Created by dingmouren on 2018/7/30.
 * 用Alarm构建长期运行在后台的定时任务
 */

public class LongRuningService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("长期运行的任务:"+i);
                }
            }
        }).start();

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int delayTime = 5 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + delayTime;
        Intent intent1 = new Intent(this,LongRuningService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this,0,intent1,0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pendingIntent);

        return super.onStartCommand(intent, flags, startId);
    }
}
