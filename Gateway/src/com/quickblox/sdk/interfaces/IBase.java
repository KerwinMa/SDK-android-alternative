package com.quickblox.sdk.interfaces;

import java.util.Date;

public interface IBase {
	enum Tags{
		updated_at,
		created_at,
		owner_id,
		id;
	}
	
	String getParentId();
	Date getCreated();
	Date getUpdated();
	Date getLastRequested();
}
