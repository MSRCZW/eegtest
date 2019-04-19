package com.xxx.pokeamole.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.xxx.pokeamole.font.FontCustom;

/**
 * 自定义字体
 *
 * @author xXx
 * @date 2018/4/18.
 */
@SuppressLint("AppCompatCustomView")
public class CustomText extends TextView {

    public CustomText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //设置字体样式
        setTypeface(FontCustom.setFont(context));
    }
}
