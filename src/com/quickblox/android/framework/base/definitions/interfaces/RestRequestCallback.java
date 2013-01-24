/**
 * 
 */
package com.quickblox.android.framework.base.definitions.interfaces;

import com.quickblox.android.framework.base.net.rest.RestResponse;

/**
 * User: Igor Khomenko
 */
public interface RestRequestCallback {
	public void completedWithResponse(RestResponse response);
}
