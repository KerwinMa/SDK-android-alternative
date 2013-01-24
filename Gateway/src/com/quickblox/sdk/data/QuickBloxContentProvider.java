package com.quickblox.sdk.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ProviderInfo;

import uk.co.madmouse.core.Data.AbstractContentProvider;
import uk.co.madmouse.core.Data.Interfaces.IDataTableBuilder;

public class QuickBloxContentProvider extends  AbstractContentProvider {

	private static QuickBloxContentProvider self;
	
	public static QuickBloxContentProvider getInstance(Context context) {
		if (self == null) {
			self = new QuickBloxContentProvider();
			self.attachInfo(context, null);
		}

		return self;
	}
	
	@Override
	public void attachInfo(Context context, ProviderInfo info) {
		loadDataTableBuilders();
		super.attachInfo(context, info);
		
	}
	
	
	@Override
	protected String getDatabaseName() {
		return DataTableConstants.DATABASE_NAME;
	}

	@Override
	protected int getDatabaseVersion() {
		return DataTableConstants.DATABASE_VERSION;
	}

	private List<IDataTableBuilder> mDatabaseObjects = null;
	@Override
	public List<IDataTableBuilder> getDatabaseObjects() {
		if(mDatabaseObjects == null){
			loadDataTableBuilders();
		}
		return mDatabaseObjects;
	}


	private void loadDataTableBuilders(){
		if(mDatabaseObjects == null){
			mDatabaseObjects = new ArrayList<IDataTableBuilder>();
		}
		
		if(mDatabaseObjects != null){
			mDatabaseObjects.add(new UserDataTableBuilder(getUriMatcher(), getContext()));
			mDatabaseObjects.add(new ApplicationTableBuilder(getUriMatcher(), getContext()));
			mDatabaseObjects.add(new RatingDataTableBuilder(getUriMatcher(), getContext()));
			mDatabaseObjects.add(new CustomObjectDataTableBuilder(getUriMatcher(), getContext()));
		}
	}

}
