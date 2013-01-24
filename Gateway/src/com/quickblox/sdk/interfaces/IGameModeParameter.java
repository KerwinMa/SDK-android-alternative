package com.quickblox.sdk.interfaces;

/*
{
	  "game_mode_parameter": {
	    "game_mode_id": 203,
	    "id": 244,
	    "name": "MyParameter"
	  }
	}
*/
public interface IGameModeParameter extends IBase {
	enum Tags{
		game_mode_parameter,
		id,
		game_mode_id,
		name;
	}
	
	Integer getId();
	Integer getGameModeId();
	String getName();
}
