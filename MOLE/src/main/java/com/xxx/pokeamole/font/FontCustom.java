package com.xxx.pokeamole.font;

import android.content.Context;
import android.graphics.Typeface;

/**
 * @author xXx
 * @date 2018/4/18.
 */
public class FontCustom {
    private static String fontUrl = "FontCustom.ttf";
    private static Typeface tf;

    /**
     * 设置字体
     */
    public static Typeface setFont(Context context)
    {
        if (tf == null)
        {
            //给它设置你传入的自定义字体文件，再返回回来
            tf = Typeface.createFromAsset(context.getAssets(), fontUrl);
        }
        return tf;
    }
}
