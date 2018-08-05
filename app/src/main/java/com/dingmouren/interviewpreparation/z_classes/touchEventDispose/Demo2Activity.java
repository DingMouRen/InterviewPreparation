package com.dingmouren.interviewpreparation.z_classes.touchEventDispose;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingmouren.interviewpreparation.R;

/**
 * Created by dingmouren
 * email: naildingmouren@gmail.com
 * github: https://github.com/DingMouRen
 * 外部拦截法
 * 外层横向滑动，内层竖向滑动，只需要修改onInterceptTouchEvent就可以了
 */

public class Demo2Activity extends AppCompatActivity {
    private RecyclerView mRecycler_1,mRecycler_2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event_dispose_demo2);

        initView();
    }

    private void initView() {
        mRecycler_1 = findViewById(R.id.recycler_1);
        mRecycler_2 = findViewById(R.id.recycler_2);

        mRecycler_1.setLayoutManager(new LinearLayoutManager(this));
        mRecycler_1.setAdapter(new MyAdapter());

        mRecycler_2.setLayoutManager(new LinearLayoutManager(this));
        mRecycler_2.setAdapter(new MyAdapter());

    }

    /**
     * 事件处理的伪代码表示
     */
  /*  public boolean dispatchTouchEvent(MotionEvent event){
        boolean consume = false;*//*是否消耗事件*//*
        if (onIntercetpTouchEvent(event)){
            consume = onTouchEvent(event);
        }else {
            consume = child.dispatchTouchEvent(event);
        }
        return consume;
    }*/

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_touch_1,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.tv.setText(i+"");
        }

        @Override
        public int getItemCount() {
            return 60;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView tv;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
            }
        }
    }
}
