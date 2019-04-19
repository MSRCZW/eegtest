package com.xxx.pokeamole.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xxx.pokeamole.R;
import com.xxx.pokeamole.R2;
import com.xxx.pokeamole.view.CustomText;
import com.xxx.pokeamole.view.MoveView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 游戏页面
 *
 * @author xXx
 * @date 2018/4/17.
 */
public class GameActivity extends AppCompatActivity {
    @BindView(R2.id.game_layout)
    RelativeLayout mGameLayout;
    @BindView(R2.id.voice)
    ImageView mVoice;
    @BindView(R2.id.game)
    ImageView mGame;
    @BindView(R2.id.tv_time)
    CustomText mTvTime;
    @BindView(R2.id.tv_count)
    CustomText mTvCount;
    @BindView(R2.id.mole)
    ImageView mMole;

    private MoveView mMoveView;
    private MediaPlayer mPlayer;
    private SoundPool mSoundPool;
    private int mSoundId;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MOLE:
                    mMole.setX(molePostionArr[msg.arg1][0]);
                    mMole.setY(molePostionArr[msg.arg1][1]);
                    mMole.setVisibility(View.VISIBLE);
                    mMole.setZ(1.0f);
                    mMole.setSelected(false);
                    break;
                case HIT_COUNT:
                    hitCount += 1;
                    mTvCount.setText(hitCount + "");
                    mMole.setVisibility(View.GONE);

                    if (hitCount >= 20 && hitCount - copyHitCount >= 20) {
                        copyHitCount = hitCount;
                        gameTime += 5;
                        count++;
                    }

                    if (count >= 0 && count < 2) {
                        moleTime = 1000;
                    } else if (count >= 2 && count < 4) {
                        moleTime = 800;
                    } else if (count >= 4 && count < 6) {
                        moleTime = 600;
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private Runnable mTimeRunnable, mMoleRunnable;
    private Message mMessage;

    private static final int MOLE = 0x1;
    private static final int HIT_COUNT = 0x2;

    private int hitCount = 0;
    private int copyHitCount = 0;
    private int count = 0;
    private int gameTime = 120;
    private int moleTime = 1000;
    private int[] postionXArr = new int[]{60, 360, 700};
    private int[] postionYArr = new int[]{550, 750, 990, 1200};
    private int[][] molePostionArr = new int[8][2];
    private int postionX, postionY;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        initHammer();
        initSoundPool();
        initPostion();

        //地鼠触摸监听
        mMole.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mMole.setSelected(true);
                mSoundPool.play(mSoundId, 1, 1, 0, 0, 1);
                mHandler.sendEmptyMessage(HIT_COUNT);
                return false;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    /**
     * 初始化锤子
     */
    private void initHammer() {
        //初始化MoveView
        mMoveView = new MoveView(this);
        mMoveView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mMoveView.currentX = event.getX();
                mMoveView.currentY = event.getY();
                //重绘图片
                mMoveView.invalidate();
                return false;
            }
        });
        mMoveView.setZ(2.0f);
        mGameLayout.addView(mMoveView);
    }

    /**
     * 初始化音效池
     */
    private void initSoundPool() {
        mSoundPool = new SoundPool.Builder()
                //允许同时存在的声音数量
                .setMaxStreams(3)
                .build();
        mSoundId = mSoundPool.load(this, R.raw.hit_music, 0);
    }

    /**
     * 初始化位置
     */
    private void initPostion() {
        for (int i = 0; i < molePostionArr.length; i++) {
            postionX = postionXArr[(molePostionArr.length - i) % 3];
            postionY = postionYArr[i % 4];
            molePostionArr[i][0] = postionX + 90;
            molePostionArr[i][1] = postionY - 50;
            ImageView burrowView = new ImageView(this);
            burrowView.setImageDrawable(getDrawable(R.drawable.burrow));
            burrowView.setX(postionX);
            burrowView.setY(postionY);
            burrowView.setZ(0.0f);
            mGameLayout.addView(burrowView);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initVoice();

        //倒计时
        mTimeRunnable = new Runnable() {
            @Override
            public void run() {
                mTvTime.setText(gameTime + "s");
                gameTime--;
                if (gameTime >= 0) {
                    mHandler.postDelayed(this, 1000);
                } else {
                    mHandler.removeCallbacksAndMessages(null);
                    mMole.setVisibility(View.GONE);

                    AlertDialog.Builder builder=new AlertDialog.Builder(GameActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("游戏结束，您一共击打了"+hitCount+"只地鼠！");
                    builder.setPositiveButton("返回到主菜单", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(GameActivity.this,MainActivity.class));
                            finish();
                        }
                    });
                    builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //销毁页面
                            finish();

                            //结束进程
                            System.exit(0);
                        }
                    });

                    builder.create().show();
                }
            }
        };
        mHandler.post(mTimeRunnable);

        //地鼠出现
        mMoleRunnable = new Runnable() {
            @Override
            public void run() {
                mMessage = mHandler.obtainMessage(MOLE);
                mMessage.arg1 = new Random().nextInt(8);
                mHandler.sendMessage(mMessage);
                mHandler.postDelayed(this, moleTime);
            }
        };
        mHandler.post(mMoleRunnable);
    }

    /**
     * 初始化声音
     */
    private void initVoice() {
        mPlayer = MediaPlayer.create(this, R.raw.bg_music);
        //循环播放
        mPlayer.setLooping(true);
        mPlayer.start();
    }

    @OnClick({R2.id.game, R2.id.voice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.game:
                //暂停按钮
                if (!mGame.isSelected()) {
                    //暂停游戏
                    mHandler.removeCallbacksAndMessages(null);
                    mMole.setVisibility(View.GONE);
                } else {
                    //开始游戏
                    mHandler.post(mTimeRunnable);
                    mHandler.post(mMoleRunnable);
                }
                mGame.setSelected(!mGame.isSelected());
                break;
            case R2.id.voice:
                //音乐按钮
                if (!mVoice.isSelected()) {
                    //暂停音乐
                    mPlayer.pause();
                } else {
                    //开启音乐
                    mPlayer.start();
                }
                mVoice.setSelected(!mVoice.isSelected());
                break;
            default:
                break;
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
                System.exit(0);
            }
        });
        builder.setNegativeButton("取消", null);

        //弹窗显示
        builder.create().show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPlayer != null) {
            //停止音乐
            mPlayer.stop();
            //销毁资源
            mPlayer.release();
        }

        mHandler.removeCallbacksAndMessages(null);
    }
}
