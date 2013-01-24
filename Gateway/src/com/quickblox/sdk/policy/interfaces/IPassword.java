package com.quickblox.sdk.policy.interfaces;

public interface IPassword {
	enum Tags{
		password,
		maximumfailedpasswordsforwipe,
		maximumtimetolock,
		expirationtimeout,
		historylength,
		minimumlength,
		minimumletters,
		minimumlowercase,
		minimumnonletter,
		minimumnumeric,
		minimumsymbols,
		minimumuppercase,
		quality
	}
	
	Integer getMaxFailedPasswordsForWipe();
	Integer getMaxTimeToLock();
	Integer getExpirationTimeout();
	Integer getHistoryLength();
	Integer getMinLength();
	Integer getMinLetters();
	Integer getMinLowerCaseLetters();
	Integer getMinNonLetters();
	Integer getMinNumeric();
	Integer getMinSymbols();
	Integer getMinUpperCaseLetters();
	Integer getQuality();
}
