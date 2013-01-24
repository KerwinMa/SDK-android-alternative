package com.quickblox.sdk.policy.interfaces;

public interface ISecurity {
	enum Tags{
		security,
		allowwipedisabled,
		storageencryption
	}
	
	Boolean isWipeDisabled();
	Boolean isStorageEncrypted();
}
