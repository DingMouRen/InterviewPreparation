package com.dingmouren.interviewpreparation;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private FragmentManager fragmentManager;
    private DemoFragment demoFragment1,demoFragment2,demoFragment3,demoFragment4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG,"返回栈id:"+getTaskId());
         demoFragment1 = new DemoFragment("1");
         demoFragment2 = new DemoFragment("2");
         demoFragment3 = new DemoFragment("3");
         demoFragment4 = new DemoFragment("4");
         fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frame,demoFragment1).addToBackStack("1").hide(demoFragment1).commit();
        fragmentManager.beginTransaction().add(R.id.frame,demoFragment2,"2").addToBackStack("2").hide(demoFragment2).commit();
        fragmentManager.beginTransaction().add(R.id.frame,demoFragment3,"3").addToBackStack("3").hide(demoFragment3).commit();
        fragmentManager.beginTransaction().add(R.id.frame,demoFragment4,"4").addToBackStack("4").hide(demoFragment4).commit();
        fragmentManager.beginTransaction().show(demoFragment1).commit();
        currentFragment = demoFragment1;
//        startActivity(new Intent(this,SecondActivity.class));
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }else {
            getContacts();
        }

    }

    private void getContacts() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        if (cursor != null){
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Log.e(TAG,"姓名:"+name+" 电话号码:"+phoneNum);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      switch (requestCode){
          case 1:
              if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                  getContacts();
              }
              break;
      }
    }

    private DemoFragment currentFragment;

    public void btn1(View view){
        fragmentManager.beginTransaction().hide(currentFragment).show(demoFragment1).commit();
        currentFragment = demoFragment1;
        Log.e(TAG,"回退栈中的数量:"+fragmentManager.getBackStackEntryCount());
    }
    public void btn2(View view){
        fragmentManager.beginTransaction().hide(currentFragment).show(demoFragment2).commit();
        currentFragment = demoFragment2;
        Log.e(TAG,"回退栈中的数量:"+fragmentManager.getBackStackEntryCount());
    }
    public void btn3(View view){
        fragmentManager.beginTransaction().hide(currentFragment).show(demoFragment3).commit();
        currentFragment = demoFragment3;
        Log.e(TAG,"回退栈中的数量:"+fragmentManager.getBackStackEntryCount());
    }
    public void btn4(View view){
        fragmentManager.beginTransaction().hide(currentFragment).show(demoFragment4).commit();
        currentFragment = demoFragment4;
        Log.e(TAG,"回退栈中的数量:"+fragmentManager.getBackStackEntryCount());
    }

    public void turnto(View view){
        startActivity(new Intent(this,ThreeActivity.class));
    }

    public void showNote(View view){

        Intent intent = new Intent(this,SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("通知")
                .setContentText("内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)/*s设置点击的PendingIntent*/
                .setAutoCancel(true) /*点击了通知后，自动删除通知UI*/
                .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))/*设置有通知的提示音*/
                .setVibrate(new long[]{0,1000,1000,1000})/*设置通知来时的振动*/
                .setLights(Color.GREEN,1000,1000)/*设置通知来时LED灯的闪烁*/
//                .setDefaults(NotificationCompat.DEFAULT_ALL)/*铃声 震动等默认效果*/
                .build();
        manager.notify(1,notification);
    }

    private int mNotificationId = 1;
    public void showNoteHigh(View view){
        Intent intent = new Intent(this,SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("通知")
                /*设置大图片*/
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.img_1)))
                /*设置长文本*/
                .setStyle(new NotificationCompat.BigTextStyle().bigText("好佛十大富豪i都好好的发顺丰hi hi都十分史蒂芬是哦哈佛i是覅欧文是否hi文化覅我hi发时候覅我hi发hi我搜房和我i和佛二五的加偶放假哦i奇偶i人给个Joe"))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)/*s设置点击的PendingIntent*/
                .setAutoCancel(true) /*点击了通知后，自动删除通知UI*/
                .setDefaults(NotificationCompat.DEFAULT_ALL)/*铃声 震动等默认效果*/
                .build();
        manager.notify(mNotificationId,notification);
        mNotificationId++;
    }

    public void startMyService(View view){
        Intent intent = new Intent(MainActivity.this,MyService.class);
        startService(intent);
    }

    public void stopMyService(View view){
        Intent intent = new Intent(MainActivity.this,MyService.class);
        stopService(intent);
    }

    private MyDownLoadService.DownLoadBinder downLoadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downLoadBinder = (MyDownLoadService.DownLoadBinder) service;
            downLoadBinder.startDownLoad();
            downLoadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    public void noteToService(View view){
        Intent bindIntent = new Intent(MainActivity.this,MyDownLoadService.class);
        bindService(bindIntent,connection,Context.BIND_AUTO_CREATE);
    }

    public void forgroundService(View view){
        Intent intent = new Intent(MainActivity.this,MyForegroundService.class);
        startService(intent);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e(TAG,"回退栈中的数量:"+fragmentManager.getBackStackEntryCount());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");

    }



    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
        Log.e(TAG,"回退栈中的数量:"+fragmentManager.getBackStackEntryCount());
        demoFragment1.setFragment(new TestFragment("test"));

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");

        Intent intent = new Intent(MainActivity.this,MyForegroundService.class);
        stopService(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }
}
