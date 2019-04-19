package com.example.bai.calculate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView tvCorrect;
    private TextView tvFault;
    private TextView tvSkip;
    private TextView tvScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reslut);

        Intent i=getIntent();
        tvCorrect=(TextView) findViewById(R.id.correct_text);
        tvCorrect.setText(i.getStringExtra("correct"));

        tvFault=(TextView) findViewById(R.id.fault_text);
        tvFault.setText(i.getStringExtra("fault"));

        tvSkip=(TextView) findViewById(R.id.skip_text);
        tvSkip.setText(i.getStringExtra("skip"));

        tvScore=(TextView) findViewById(R.id.score_text);
        tvScore.setText(i.getStringExtra("score"));

    }
}
