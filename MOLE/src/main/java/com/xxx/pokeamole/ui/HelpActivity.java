package com.xxx.pokeamole.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xxx.pokeamole.R;
import com.xxx.pokeamole.R2;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 帮助页面
 *
 * @author xXx
 * @date 2018/4/17.
 */
public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.btn_back)
    public void onViewClicked() {
        finish();
    }
}
