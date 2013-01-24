package com.quickblox.android.framework.base.net.tasks;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;

/**
 * User: Oleg Soroka
 * Date: 05.10.12
 * Time: 00:40
 */
public abstract class QueriesTask {

    private QBCallback callback;
    private Object context;

    public QueriesTask(QBCallback callback, Object context) {
        this.callback = callback;
        this.context = context;
    }

    public void completeTask(Result result) {
        if (callback != null) {
            if (context != null) {
                callback.onComplete(result, context);
            } else {
                callback.onComplete(result);
            }
        }
    }

    public abstract void performTask();

    //

    public QBCallback getCallback() {
        return callback;
    }

    public void setCallback(QBCallback callback) {
        this.callback = callback;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }
}