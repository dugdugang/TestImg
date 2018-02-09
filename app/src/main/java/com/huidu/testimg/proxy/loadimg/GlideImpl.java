package com.huidu.testimg.proxy.loadimg;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;

import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.huidu.testimg.R;


import java.security.MessageDigest;

/**
 * Author: duguang
 * Date 2017/12/8 0008.
 */

public class GlideImpl implements ImgLoad {

    private RequestOptions options = new RequestOptions()
            .placeholder(R.drawable.icon_default_img)
            .centerCrop()
            .skipMemoryCache(true)
            .priority(Priority.HIGH);

    private RequestOptions optionCircles = new RequestOptions()
//            .placeholder(R.drawable.icon_default_img)
            .centerCrop()
            .priority(Priority.HIGH);
    private RequestOptions optionNoDefault = new RequestOptions()
//            .placeholder(R.drawable.icon_default_img)
            .centerCrop()
            .priority(Priority.HIGH);


    @Override
    public void load(final Context context, Object o, final ImageView imgv) {
//        Glide.with(context)
//                .load(o)
////                .listener(new )
//                .apply(options)
//                .into();
//        GlideApp.with(context)
//                .load(o)
//                .transform(new Transformation<Bitmap>() {
//                    @Override
//                    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
//
//                    }
//
//                    @NonNull
//                    @Override
//                    public Resource<Bitmap> transform(@NonNull Context context, @NonNull Resource<Bitmap> resource, int outWidth, int outHeight) {
//                        Bitmap bitmap=resource.get();
//
//                        Log.i("TAG","b:"+bitmap.getWidth()+" "+bitmap.get);
//                        return null;
//                    }
//                })
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        Log.i("M-TAG","listener: "+ resource.getIntrinsicWidth() + " " + resource.getIntrinsicHeight());
//                        return true;
//                    }
//                })
//                .into(imgv);
        imgv.setImageResource(R.drawable.icon_default_img);
        GlideApp.with(context)
                .asBitmap()
                .load(o)
//                .listener(new RequestListener<Bitmap>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
//                        Log.i("M-TAG","b:"+resource.getWidth()+" "+resource.getHeight());
//                        return true;
//                    }
//                })
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Log.i("M-TAG", resource.getWidth() + " " + resource.getHeight());
                        ViewGroup.LayoutParams params = imgv.getLayoutParams();
                        float ratio=((float) resource.getHeight())/resource.getWidth();
                        Resources resources = context.getResources();
                        DisplayMetrics dm = resources.getDisplayMetrics();
                        float density = dm.density;
                        int width = dm.widthPixels;
                        int height = (int) (width*ratio);
                        params.width = width;
                        params.height = height;

                        imgv.setLayoutParams(params);
                        Log.i("M-TAG","iv: "+imgv.getWidth()+" "+imgv.getHeight());
                        imgv.setImageBitmap(resource);
                    }
                });


    }

    @Override
    public void loadCache(Context context, Object o, ImageView imgv) {
//        imgv.getParent();
//      ViewGroup.LayoutParams params= imgv.getLayoutParams();
//        params.width

        Glide.with(context)
                .load(o)
                .apply(options)
                .into(imgv);

    }

    @Override
    public void load(Context context, Object o, ImageView imgv, float thumbnail) {
        Glide.with(context)
                .load(o)
                .thumbnail(thumbnail)
                .apply(options)
                .into(imgv);
    }

    @Override
    public void load(Context context, String url, ImageView imgv, float thumbnail) {
        Glide.with(context)
                .load(url)
                .thumbnail(thumbnail)
                .apply(options)
                .into(imgv);
    }

    @Override
    public void loadCircleImg(Context context, Object o, ImageView imgv) {
        Glide.with(context)
                .load(o)
                .apply(optionCircles)
                .into(imgv);
    }

    @Override
    public void loadNoDefault(Context context, Object o, ImageView imgv, float thumbnail) {
        Glide.with(context)
                .load(o)
                .apply(optionNoDefault)
                .into(imgv);
    }


}
