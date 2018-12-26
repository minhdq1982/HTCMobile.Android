package com.tvo.htc.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.android.lib.util.Logger;
import com.tvo.htc.BuildConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;

import okhttp3.ResponseBody;

public class FileUtils {
    private static String PATH_EXTERNAL = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator + "HTC Mobile";
    public static String PATH_CATALOG = PATH_EXTERNAL + File.separator + "Catalog" + File.separator;
    public static String PATH_CALCULATE_INTEREST_RATE = PATH_EXTERNAL + File.separator + "CalculateInterestRate" + File.separator;

    private static final int KB = 1024;
    private static final int MB = KB * KB;

    public static String saveCatalog(ResponseBody responseBody, String name) throws Exception {
        return saveFile(responseBody, PATH_CATALOG, name);
    }

    public static String saveCalculateInterestRate(ResponseBody responseBody, String name) throws Exception {
        return saveFile(responseBody, PATH_CALCULATE_INTEREST_RATE, name);
    }

    public static String convertCurrentProgress(long bytesRead, long contentLength) {
        try {
            int valuePercent = contentLength >= MB ? MB : KB;
            float current = (bytesRead * 1f) / valuePercent;
            float count = (contentLength * 1f) / valuePercent;
            if (valuePercent == KB) {
                return (int) current + "/" + (int) count + "kb";
            } else {
                return String.format("%.2f", current) + "/" + String.format("%2f", count) + "m";
            }
        } catch (Exception e) {
            Logger.e("ERROR FORMAT: " + e);
        }
        return "Downloading...";
    }

    public static void goToFolderCatalog(Context context) {
        try {
            openPath(context, PATH_CATALOG, "*/*");
        } catch (Exception e) {
            onErrorActivity(context);
        }
    }

    public static void gotoFolderCalculateInterestRate(Context context) {
        try {
            openPath(context, PATH_CALCULATE_INTEREST_RATE, "*/*");
        } catch (Exception e) {
            onErrorActivity(context);
        }
    }

    public static void openFile(Context context, String path) {
        try {
            openPath(context, path);
        } catch (Exception e) {
            Logger.e("Error open file: " + e);
            try {
                openMarketSearch(context, path);
            } catch (Exception e1) {
                Toast.makeText(context, "No Activity Found!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public static String getFileName(int prefix, String link) {
        if (link.isEmpty()) {
            return prefix + "_" + prefix + ".pdf";
        } else {
            if (link.contains("/")) {
                String[] sp = link.split("/");
                return prefix + "_" + sp[sp.length - 1];
            } else {
                return prefix + "_" + link.replace("https://", "").replace("http://", "");
            }
        }
    }

    public static boolean hasCatalogExists(int prefix, String link) {
        return hasFileExists(PATH_CATALOG, prefix, link);
    }

    public static String getPathCatalog(int prefix, String link) {
        return new File(PATH_CATALOG + File.separator + getFileName(prefix, link)).getPath();
    }

    private static File createFile(String path, String name) throws Exception {
        File file = new File(path + name);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }

        return file;
    }

    private static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    private static String saveFile(ResponseBody body, String path, String name) throws Exception {
        File file = createFile(path, name);
        try {
            Logger.e("PathSave: ---> " + file.getPath());
            InputStream in = null;
            FileOutputStream out = null;
            in = body.byteStream();
            out = new FileOutputStream(file);
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return file.getPath();
    }

    private static boolean hasFileExists(String path, int prefix, String link) {
        File file = new File(path + File.separator + getFileName(prefix, link));
        return file.exists();
    }


    private static void openPath(Context context, String path) throws Exception {
        openPath(context, path, getMimeType(path));
    }

    private static void openPath(Context context, String path, String mimeType) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", new File(path));
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, mimeType);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(path), mimeType);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    private static void openMarketSearch(Context context, String path) throws Exception {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=" + getMimeType(path)));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private static void onErrorActivity(Context context) {
        Toast.makeText(context, "No Activity Found!", Toast.LENGTH_SHORT).show();
    }


    public static String getFileNameCalculate() {
        Calendar cal = Calendar.getInstance();
        return "Tu_van_tra_gop_" + cal.get(Calendar.HOUR) + "_" + cal.get(Calendar.MINUTE) + "-" + cal.get(Calendar.DAY_OF_MONTH) + "_" + (cal.get(Calendar.MONTH) + 1) + ".pdf";
    }

    public static void deleteFile(String pathSave) {
        try {
            Logger.e("Delete file: " + pathSave);
            File file = new File(pathSave);
            if (!file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            Logger.e("Errorr delete file: " + e);
        }
    }
}
