package com.example.c.bnuzproject;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.hjm.bottomtabbar.BottomTabBar;
import com.github.pwittchen.neurosky.library.NeuroSky;
public class MainActivity extends AppCompatActivity implements OneFragment.OnFragmentInteractionListener,TwoFragment.OnFragmentInteractionListener {
    private BottomTabBar mBottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomTabBar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);
        mBottomTabBar
                .init(getSupportFragmentManager())//初始化方法，必须第一个调用；传入参数为V4包下的FragmentManager
                .setImgSize(50,50)//设置ICON图片的尺寸
                .setFontSize(8)//设置文字的尺寸
                .setTabPadding(4,6,4)//设置ICON图片与上部分割线的间隔、图片与文字的间隔、文字与底部的间隔
                .setChangeColor(Color.RED,Color.DKGRAY)//设置选中的颜色、未选中的颜色
                .addTabItem("第一项",R.mipmap.home, R.mipmap.home, OneFragment.class)//设置文字、一张图片、fragment
                .addTabItem("第二项", R.mipmap.yonghu, R.mipmap.yonghu,com.example.lenovo.myapplication.FragmentDemo.class)//设置文字、两张图片、fragment
                .isShowDivider(false)//设置是否显示分割线
                .setTabBarBackgroundColor(Color.WHITE)//设置底部导航栏颜色
                //.setTabBarBackgroundResource(R.mipmap.ic_launcher)//设置底部导航栏的背景图片【与设置底部导航栏颜色方法不能同时使用，否则会覆盖掉前边设置的颜色】
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {
                        Log.i("TGA", "位置：" + position + "      选项卡的文字内容：" + name);
                    }//添加选项卡切换监听

                })
                .setCurrentTab(0);//设置当前选中的Tab，从0开始

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this,"交流,角楼",Toast.LENGTH_LONG).show();
    }
}
