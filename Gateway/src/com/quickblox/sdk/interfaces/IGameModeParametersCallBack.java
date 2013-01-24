package com.quickblox.sdk.interfaces;

import java.util.List;

public interface IGameModeParametersCallBack {
	void onGameModeParameterCreated(String account, Integer applicationId, IGameModeParameter gameModeParameterObject);
	void onGameModeParameterUpdated(String account, Integer applicationId, IGameModeParameter gameModeParameterObject);
	void onGameModeParameterDeleted(String account, Integer applicationId, Integer gameModeParameterId);
	void onGameModeParameterList(String account, Integer applicationId, List<IGameModeParameter> gameModeParameterList);
}
