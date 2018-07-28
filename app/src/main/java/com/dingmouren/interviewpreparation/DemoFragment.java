package com.dingmouren.interviewpreparation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by dingmouren on 2018/7/28.
 */
@SuppressLint("ValidFragment")
public class DemoFragment extends Fragment {
    private static final String TAG = DemoFragment.class.getName();
    private TextView mTv;
    private String title;

    public DemoFragment(String title) {
        this.title = title;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG,"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_demo,container,false);
        mTv = view.findViewById(R.id.tv);
        mTv.setText(title);
        return view;
    }

    public void setFragment(Fragment fragment){
        getChildFragmentManager().beginTransaction().replace(R.id.frame_fragment,fragment).commit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG,"onActivityCreated "+mTv.getText());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG,"onStart "+mTv.getText());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"onResume "+mTv.getText());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG,"onPause "+mTv.getText());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,"onStop "+mTv.getText());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG,"onDestroyView ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG,"onDetach");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e(TAG,"setUserVisibleHint:"+isVisibleToUser+mTv.getText());
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e(TAG,"onHiddenChanged:"+hidden+mTv.getText());
    }
}
