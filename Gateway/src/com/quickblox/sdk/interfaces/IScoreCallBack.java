package com.quickblox.sdk.interfaces;

import java.util.List;

public interface IScoreCallBack {
	void onScoreCreated(String account, Integer applicationId, IScore scoreObject);
	void onScoreUpdated(String account, Integer applicationId, IScore scoreObject);
	void onScoreDeleted(String account, Integer applicationId, Integer scoreId);
	void onScoreList(String account, Integer applicationId, List<IScore> scoreList);
}
