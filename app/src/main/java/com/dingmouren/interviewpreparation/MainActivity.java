package com.dingmouren.interviewpreparation;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
