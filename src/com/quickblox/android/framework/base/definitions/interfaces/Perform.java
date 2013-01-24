/**
 *
 */
package com.quickblox.android.framework.base.definitions.interfaces;

import com.quickblox.android.framework.base.definitions.QBCallback;

/**
 * User: Igor Khomenko
 */
public interface Perform {
    public void performAsyncWithCallback(QBCallback delegate);

    public void performAsyncWithCallback(QBCallback delegate, Object context);
}