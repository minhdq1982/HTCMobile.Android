package com.tvo.htc.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

/**
 * Created by ThinhNH on 9/30/2016.
 */
public class ImageLoader {
    public static void loadImage(Context context, ImageView imageView, String url) {
        loadImageObject(context, imageView, url);
    }

    public static void loadImage(Context context, ImageView imageView, int placeholder,
                                 String url) {
        loadImageObject(context, imageView, placeholder, url, true);
    }

    public static void loadImage(Context context, ImageView imageView, int placeholder, Object url) {
        loadImageObject(context, imageView, placeholder, url, true);
    }

    public static void loadImage(Context context, ImageView imageView, int placeholder,
                                 String url, boolean skipMemory) {
        loadImageObject(context, imageView, placeholder, url, skipMemory);
    }

    public static void loadImage(Context context, ImageView imageView,
                                 int drawableRes) {
        loadImageObject(context, imageView, drawableRes);
    }

    public static void loadImage(Context context, ImageView imageView, int placeholder,
                                 int drawableRes) {
        loadImageObject(context, imageView, placeholder, drawableRes, true);
    }

    public static void loadImage(Context context, ImageView imageView, File file) {
        loadImageObject(context, imageView, file);
    }

    public static void loadImage(Context context, ImageView imageView, int placeholder,
                                 File file) {
        loadImageObject(context, imageView, placeholder, file, true);
    }

    public static void loadImage(Context context, ImageView imageView, byte[] byteData) {
        loadImageObject(context, imageView, byteData);
    }

    public static void loadImage(Context context, ImageView imageView, int placeholder,
                                 byte[] byteData) {
        loadImageObject(context, imageView, placeholder, byteData, true);
    }

    public static void loadImage(Context context, ImageView imageView, Bitmap bitmap) {
        loadImageObject(context, imageView, bitmap);
    }

    private static void loadImageObject(Context context, ImageView imageView, Object obj) {
        Glide.with(context.getApplicationContext()).load(obj)
//                .asBitmap()
//                .override(MediaUtils.THUMBNAIL_SIZE, MediaUtils.THUMBNAIL_SIZE)
//                .signature(Utils.generateCacheKey(activity, data, true))
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(placeholder)
//                .error(placeholder)
//                .listener(new PhotoLoaderListener(progressBar))
                .into(imageView);
    }

    private static void loadImageObject(Context context, ImageView imageView, int placeholder,
                                        Object obj, boolean skipMemory) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(placeholder);
        requestOptions.error(placeholder);
        requestOptions.skipMemoryCache(true);
        Glide.with(context.getApplicationContext())
                .setDefaultRequestOptions(requestOptions)
                .load(obj)
//                .asBitmap()
//                .override(MediaUtils.THUMBNAIL_SIZE, MediaUtils.THUMBNAIL_SIZE)
//                .signature(Utils.generateCacheKey(activity, data, true))
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(placeholder)
//                .error(placeholder)
//                .listener(new PhotoLoaderListener(progressBar))
                .into(imageView);
    }
}
