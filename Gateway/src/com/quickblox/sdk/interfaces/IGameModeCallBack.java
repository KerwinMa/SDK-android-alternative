package com.quickblox.sdk.interfaces;

import java.util.List;

public interface IGameModeCallBack {
	void onGameModeCreated(String account, Integer applicationId, IGameMode gameModeObject);
	void onGameModeUpdated(String account, Integer applicationId, IGameMode gameModeObject);
	void onGameModeDeleted(String account, Integer applicationId, Integer gameModeId);
	void onGameModeList(String account, Integer applicationId, List<IGameMode> gameModeList);
}
