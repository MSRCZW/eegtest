package com.xxx.pokeamole.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.xxx.pokeamole.R;
import com.xxx.pokeamole.R2;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 初始页面
 *
 * @author xXx
 * @date 2018/4/17.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.btn_start, R2.id.btn_help, R2.id.btn_quit})
    public void onViewClicked(View view) {
        int i = view.getId();
        if(i == R.id.btn_start){
                //开始按钮

                startActivity(new Intent(this, GameActivity.class));
                finish();
        }
        else if (i == R.id.btn_help) {
            //帮助按钮
            startActivity(new Intent(this, HelpActivity.class));
        }else if(i == R.id.btn_quit){
                //退出按钮
                quitGame();
        }

    }

    /**
     * 返回键监听
     */
    @Override
    public void onBackPressed() {
        quitGame();
    }

    /**
     * 退出游戏弹窗
     */
    private void quitGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出游戏吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //销毁页面
                finish();

                //结束进程
                //System.exit(0);
            }
        });
        builder.setNegativeButton("取消", null);

        //弹窗显示
        builder.create().show();
    }
}
