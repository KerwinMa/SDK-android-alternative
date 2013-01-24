package com.quickblox.sdk.interfaces;

import java.util.List;

public interface IUserCallBack {
	void onUserCreated(String account, Integer applicationId, IUser userObject);
	void onUserUpdated(String account, Integer applicationId, IUser userObject);
	void onUserDeleted(String account, Integer applicationId);
	void onUserList(String account, Integer applicationId, List<IUser> userList);

}
