package com.huidu.testimg.proxy.loadimg;

import android.content.Context;
import android.widget.ImageView;

/**
 * Author: duguang
 * Date 2017/12/11 0011.
 */

public interface ImgLoad {
    void load(Context context, Object o, ImageView imgv);

    void loadCache(Context context, Object o, ImageView imgv);

    void load(Context context, Object o, ImageView imgv, float thumbnail);

    void load(Context context, String url, ImageView imgv, float thumbnail);

    void loadCircleImg(Context context, Object o, ImageView imgv);

    void loadNoDefault(Context context, Object o, ImageView imgv, float thumbnail);
}
