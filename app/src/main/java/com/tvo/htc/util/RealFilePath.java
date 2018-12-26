package com.tvo.htc.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.android.lib.util.Logger;
import com.tvo.htc.view.main.profile.edit.crop.CropFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Create by Ngocji on 11/20/2018
 **/


public class RealFilePath {
    /**
     * Method for return file path of Gallery image
     *
     * @param context
     * @param uri
     * @return path of the selected image file from gallery
     */
    static String nopath = "No File Selected!";
    public static String pathSaveImage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath() + File.separator + "HtcMobile" + File.separator;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {

        // check here to KITKAT or new version
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {

            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/"
                            + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                try {
                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"),
                            Long.valueOf(id));

                    return getDataColumn(context, contentUri, null, null);
                } catch (Exception e) {
                    String uriFormat = Uri.decode(uri.toString().replace("content://com.android.providers.downloads.documents/", ""));
                    if (uriFormat.contains(":")) {
                        String[] sp = uriFormat.split(":");
                        return sp[sp.length - 1];
                    } else {
                        return nopath;
                    }
                }
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection,
                        selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return nopath;
    }

    /**
     * Get the value of the data column for this Uri. This is <span id="IL_AD2"
     * class="IL_AD">useful</span> for MediaStore Uris, and other file-based
     * ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri,
                                       String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection,
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return nopath;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri
                .getAuthority());
    }

    /**
     * Get real uri from bitmap
     *
     * @param context
     * @param bitmap
     * @return Uri
     */
    public static File getRealFileFromBitmap(Context context, Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        try {
            File file = new File(pathSaveImage + "temp" + File.separator + "temp_camera_" + System.currentTimeMillis() + ".jpg");
            Logger.e("file: " + file.getPath());
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            deleteAllFileInFolder(file);
            file.createNewFile();
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());
            fo.flush();
            fo.close();
            return file;
        } catch (Exception e) {
            Logger.e("ERROR CAMERA: " + e.toString());
        }
        return null;
    }

    private static void deleteAllFileInFolder(File file) {
        File[] listFile = file.getParentFile().listFiles();

        for (int i = 0; i < listFile.length; i++) {
            Logger.e("Delete file: " + listFile[i].getPath());
            listFile[i].delete();
        }
    }

    public static File createNewFileCrop() throws IOException {
        File file = new File(pathSaveImage + "crop" + File.separator + "temp_crop_" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        deleteAllFileInFolder(file);
        file.createNewFile();
        return file;
    }

    public static void cropImage(Fragment fragment, Uri picUri, CropFragment.CallBack callBack) {
        try {
//            File file = createNewFileCrop();
            FragmentUtil.startFragmentNoTabbar(fragment.getContext(), CropFragment.newInstance(picUri.toString(),callBack), null);
//            Crop.of(picUri, Uri.fromFile(file))
//                    .withMaxSize(outputSize, outputSize)
////                    .asSquare()
//                    .withAspect(0,0)
//                    .start(fragment.getContext(), fragment, req);
//            Intent cropIntent = new Intent("com.android.camera.action.CROP");
//            // indicate image type and Uri
//            cropIntent.setDataAndType(picUri, "image/*");
//            // set crop properties here
//            cropIntent.putExtra("crop", true);
//            // indicate aspect of desired crop
////            cropIntent.putExtra("aspectX", outputSize);
////            cropIntent.putExtra("aspectY", outputSize);
//            // indicate output X and Y
//            cropIntent.putExtra("outputX", outputSize);
//            cropIntent.putExtra("outputY", outputSize);
//            cropIntent.putExtra("scale", false);
//            // retrieve data on return
//            try {
//                cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
//                cropIntent.putExtra("output", Uri.fromFile(file));
//            } catch (Exception e) {
//
//            }
//            cropIntent.putExtra("return-data", false);
////            cropIntent.setFlags(Intent.ACTION_OPEN_DOCUMENT)
//            // start the activity - we handle returning in onActivityResult
//            fragment.startActivityForResult(cropIntent, req);
        }
        // respond to users whose devices do not support the crop action
        catch (Exception e) {
            //todo show exception
            e.printStackTrace();
        }
    }

    public static String formatPath(String path) {
        if (!path.contains("file://")) {
            return "file://" + path;
        }
        return path;
    }

    public static String revertFormatPath(String path) {
        return path.replace("file://", "");
    }
}
