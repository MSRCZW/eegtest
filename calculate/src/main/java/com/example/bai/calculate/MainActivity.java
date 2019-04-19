package com.example.bai.calculate;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.CountDownTimer;


import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener{

    private TextView tvNum;
    private TextView trTimes;
    private TextView tvScore;
    private TextView tvEquation;//算式
    private EditText edResult;
    private Button btnOk;
    private int score;//分数
    private int a;
    private int b;
    private int sum;
    private String str;
    private int t;//正确的题数
    private int f;//错误的题数
    private int skip;//略过的题数
    private TextView cdTime;
    private boolean timer_a;
    AlertDialog finish_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        tvNum = (TextView) findViewById(R.id.text_num);
        trTimes = (TextView) findViewById(R.id.true_times);
        tvScore = (TextView) findViewById(R.id.text_Score);
        tvEquation = (TextView) findViewById(R.id.equation_text);
        edResult = (EditText)findViewById(R.id.edit_result);
        btnOk = (Button) findViewById(R.id.button_ok);
        btnOk.setOnClickListener(this);

        edResult.clearFocus();//失去焦点
        a = 0;
        b = 0;
        str = "";
        t = 0;
        f = 0;
        skip = 0;
        score = 0;
        timer_a = false;
        cdTime = (TextView) findViewById(R.id.cd_time);
    }

    //点击确定按钮时的事件
    @Override
    public void onClick(View v) {

        if (timer_a == true)
        {
            if ((edResult.getText().toString()).equals(Integer.toString(sum))) {
                Toast.makeText(this,"答案正确",Toast.LENGTH_SHORT).show();
                t++;
                score++;
            }
            else {
                Toast.makeText(this,"答案错误",Toast.LENGTH_SHORT).show();
                f++;
                score-=2;
            }
        }

        if (timer_a == false) {
            timer.start();
            timer_a = true;
            tvNum.setText("");
            Toast.makeText(this,"测试开始",Toast.LENGTH_SHORT).show();
        }

        edResult.setText(null);

        RandomEquation();

        trTimes.setText("正确的次数：" + Integer.toString(t));
        tvScore.setText("分数：" + Integer.toString(score));
    }

    //生成随机算式
    public void RandomEquation(){
        a = new Random().nextInt(400) + 100;
        b = new Random().nextInt(400) + 100;
        sum = a + b;
        str = Integer.toString(a) + " + " + Integer.toString(b) + " = ";
        tvEquation.setText(str);
    }

    //略过按钮点击时的事件
    public void skipClick(View v){
        RandomEquation();
        skip++;
    }

    //数字按钮点击时的事件
    public void numOnclick(View v) {
        String str = edResult.getText().toString();
        edResult.setText(str+((Button)v).getText());
    }

    //删除按钮点击时的事件
    public void deleteOnclick(View v) {
        String str = edResult.getText().toString();
        edResult.setText(str.substring(0,str.length() - 1));
    }

    //计时器
    public void restart(View v) {
        timer.start();
    }

    private CountDownTimer timer = new CountDownTimer(1800000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            cdTime.setText("剩余时间：" + ((millisUntilFinished / 1000)/60) + "分" + ((millisUntilFinished / 1000)%60) + "秒");
        }

        //倒计时结束后
        @Override
        public void onFinish() {

            //弹出测试结果窗口
            Intent i = new Intent();
            i.setClass(MainActivity.this, ResultActivity.class);
            i.putExtra("correct",Integer.toString(t));
            i.putExtra("fault",Integer.toString(f));
            i.putExtra("skip",Integer.toString(skip));
            i.putExtra("score",Integer.toString(score));
            startActivity(i);

            cdTime.setEnabled(true);
            cdTime.setText("测试完成，点击确定重新开始测试");
            finish_dialog = new AlertDialog.Builder(MainActivity.this).setTitle("测试结果").setMessage("本次测试你答对了"+Integer.toString(t)+"道题").setIcon(R.mipmap.ic_launcher).setPositiveButton("确定",null).create();
            finish_dialog.show();
            t = 0;
            timer_a = false;


        }
    };

}
