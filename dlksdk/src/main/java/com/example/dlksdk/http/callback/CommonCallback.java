package com.example.dlksdk.http.callback;

import android.util.Log;

import org.xutils.common.Callback;

/**
 * Created by 2 on 2018/12/25.
 */

public abstract class CommonCallback<T> implements Callback.CommonCallback<T> {
    @Override
    public void onSuccess(T result) {
        success(result);
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        Log.i(">>>>", "onError: "+ex.getMessage());
        Log.i(">>>>", "onError: "+ex.getLocalizedMessage());
        Log.i(">>>>", "onError: "+ex.toString());
        failure(ex.getMessage());
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
    protected abstract void success(T result);

    protected abstract void failure(String reason);

}
