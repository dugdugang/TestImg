package com.huidu.testimg.proxy.loadimg;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;


/**
 * Author: duguang
 * Date 2017/12/8 0008.
 */

public class LoadImg implements ImgLoad {
    private static LoadImg mInstance;
    private ImgLoad imgLoad;
    public final static String BASE_URL = "http://122.114.162.108/upload/";

    private LoadImg() {

    }

    public synchronized static LoadImg getInstance() {
        if (mInstance == null) {
            synchronized (LoadImg.class) {
                if (mInstance == null) {
                    mInstance = new LoadImg();
                }
            }
        }
        return mInstance;
    }

    public void init() {
        imgLoad = new GlideImpl();
    }

    public void loadNoBase(Context context, String  url, ImageView imgv){
        imgLoad.load(context,BASE_URL+ url, imgv);

    }

    @Override
    public void load(Context context, Object o, ImageView imgv) {
        Log.i("M-TAG",o+"");
        imgLoad.load(context, o, imgv);
    }

    @Override
    public void loadCache(Context context, Object o, ImageView imgv) {
        imgLoad.loadCache(context, o, imgv);
    }

    @Override
    public void load(Context context, Object o, ImageView imgv, float thumbnail) {
        imgLoad.load(context, o, imgv, thumbnail);
    }

    @Override
    public void load(Context context, String url, ImageView imgv, float thumbnail) {
        imgLoad.load(context,  url, imgv, thumbnail);

    }

    @Override
    public void loadCircleImg(Context context, Object o, ImageView imgv) {
        imgLoad.loadCircleImg(context,o,imgv);
    }

    @Override
    public void loadNoDefault(Context context, Object o, ImageView imgv, float thumbnail) {
        imgLoad.loadNoDefault(context,o,imgv,thumbnail);

    }

}
