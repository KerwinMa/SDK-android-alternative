package com.quickblox.sdk.data.interfaces;

import uk.co.madmouse.core.Data.Interfaces.IDataTableBuilder;

public interface IRatingsDataTableBuilder extends IDataTableBuilder {
	
	public interface IGameModeColumns{
		public static final String ID = "_id";
		public static final String ACCOUNT = "account";
		public static final String APPLICATION_ID = "applicationId";
		public static final String USER_ID = "userId";
		public static final String TITLE = "title";
		public static final String DIRTY = "dirty";
		public static final String SYNCED_AT = "synced_at";
		public static final String JSON_OBJECT = "jsonObject";
	
	}
	
	public interface IGameModeDeletedColumns{
		public static final String ID = "_id";
		public static final String ACCOUNT = "account";
		public static final String APPLICATION_ID = "applicationId";
		public static final String USER_ID = "userId";
		public static final String DELETED_AT = "deletedAt";
	
	}
	
	
	public interface IScoreColumns{
		public static final String ID = "_id";
		public static final String ACCOUNT = "account";
		public static final String APPLICATION_ID = "applicationId";
		public static final String GAME_MODE_ID = "gameModeId";
		public static final String USER_ID = "userId";
		public static final String CREATED_AT = "createdAt";
		public static final String VALUE = "value";
		public static final String DIRTY = "dirty";
		public static final String SYNCED_AT = "synced_at";
		public static final String JSON_OBJECT = "jsonObject";
	}
	
	public interface IScoreDeletedColumns{
		public static final String ID = "_id";
		public static final String ACCOUNT = "account";
		public static final String APPLICATION_ID = "applicationId";
		public static final String GAME_MODE_ID = "gameModeId";
		public static final String DELETED_AT = "deletedAt";
	
	}

	
	public interface IGameModeParametersColumns{
		public static final String ID = "_id";
		public static final String ACCOUNT = "account";
		public static final String APPLICATION_ID = "applicationId";
		public static final String GAME_MODE_ID = "gameModeId";
		public static final String NAME = "paramName";
	}
	
	public interface IGameModeParametersDeletedColumns{
		public static final String ID = "_id";
		public static final String ACCOUNT = "account";
		public static final String APPLICATION_ID = "applicationId";
		public static final String GAME_MODE_ID = "gameModeId";
		public static final String DELETED_AT = "deletedAt";
	
	}
	
	
	public interface IGameModeParameterValuesColumns{
		public static final String ID = "_id";
		public static final String ACCOUNT = "account";
		public static final String GAME_MODE_PARAMETER_ID = "game_mode_parameter_id";
		public static final String CREATED_AT = "createdAt";
		public static final String SCORE_ID = "score_id";
		public static final String UPDATED_AT = "updated_at";
		public static final String VALUE = "valueTxt";
	}

	
}
