package com.bwie.uu.max_banner;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private List<ImageView> list;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    private LinearLayout lin;
    private List<ImageView> addlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        lin = (LinearLayout) findViewById(R.id.lin);
        addView();


        addPaint();

        pager.setAdapter(new Myadapter(list, this));
        pager.setCurrentItem(Integer.MAX_VALUE / 2);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position % list.size();
                Log.e("*************", position + "");

                for (int i = 0; i < addlist.size(); i++) {

                    if (position == i) {
                        addlist.get(i).setImageResource(R.drawable.circle_drawable_pressed);
                    } else {
                        addlist.get(i).setImageResource(R.drawable.circle_drawable);
                    }

                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //触摸监听
        pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        handler.removeCallbacks(runnable);
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.postDelayed(runnable, 3000);
                        break;
                }
              return false;
            }
        });


        handler.postDelayed(runnable, 1000);


    }

    private void addView() {
        list = new ArrayList<>();
        ImageView v1 = new ImageView(this);
        Glide.with(this).load("http://img4.imgtn.bdimg.com/it/u=542745485,3998209059&fm=11&gp=0.jpg").into(v1);
        ImageView v2 = new ImageView(this);
        Glide.with(this).load("http://pic1.win4000.com/wallpaper/1/55f00045a31ea.jpg").into(v2);
        ImageView v3 = new ImageView(this);
        Glide.with(this).load("http://img04.tooopen.com/images/20131128/sy_49100228519.jpg").into(v3);
        ImageView v4 = new ImageView(this);
        Glide.with(this).load("http://img5.imgtn.bdimg.com/it/u=2989741498,1645297247&fm=11&gp=0.jpg").into(v4);
        ImageView v5 = new ImageView(this);
        Glide.with(this).load("http://img3.duitang.com/uploads/item/201506/29/20150629193742_hKmcR.jpeg").into(v5);
        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        list.add(v5);
    }

    private void addPaint() {
        addlist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(this);
            //设置默认选中远点
            if (i == 3) {
                imageView.setImageResource(R.drawable.circle_drawable_pressed);
            } else {
                imageView.setImageResource(R.drawable.circle_drawable);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(11, 11);
            params.setMargins(5, 0, 5, 0);
            lin.addView(imageView, params);
            addlist.add(imageView);
        }
    }




    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = pager.getCurrentItem();

            pager.setCurrentItem(currentItem + 1);
            Log.e("CCCCCCCCcc", currentItem + "");
            handler.postDelayed(this, 2000);
        }
    };


}
