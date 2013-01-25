package com.quickblox.sdk.interfaces;

import java.util.List;

public interface IDataSchemaCallback {
	void onDataSchemaCreated(String account, Integer applicationId, IDataSchema dataSchema);
	void onDataSchemaList(String account, Integer applicationId, List<IDataSchema> dataSchemaList);
}
