package com.android.lib.model;

public interface ProgressListener {
    void onProgress(long bytesRead, long contentLength, boolean done);
}
