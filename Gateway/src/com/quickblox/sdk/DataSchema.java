package com.quickblox.sdk;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.quickblox.sdk.interfaces.IDataSchema;

class DataSchema implements IDataSchema {
	class DataField implements IDataField{
		private static final String TAG = "DataField";
		private Boolean mIsArray;
		private Integer mSortOrder;
		private String mType;
		private String mName;

		public DataField(JsonReader jsonReader){
			try {
				jsonReader.setLenient(true);
				jsonReader.beginObject();
				while( jsonReader.hasNext()){
					final String innerName = jsonReader.nextName();
					final boolean isInnerNull = jsonReader.peek() == JsonToken.NULL;
					if( innerName.equals( Tags.type.name() ) && !isInnerNull ) {
						this.mType = jsonReader.nextString();	
					} else {
						if( innerName.equals( Tags.array.name() ) && !isInnerNull ) {
							this.mIsArray = jsonReader.nextBoolean();	
						} else {
							if( innerName.equals( Tags.order.name() ) && !isInnerNull ) {
								this.mSortOrder = jsonReader.nextInt();
							} else {
								if( innerName.equals( Tags.name.name() ) && !isInnerNull ) {
									this.mName = jsonReader.nextString();
								} else {
									jsonReader.skipValue();
								}
							}	
						}	
					}	
				}
				jsonReader.endObject();
			} catch (IOException e) {
				if(Log.isLoggable(TAG, Log.ERROR)){
					Log.e(TAG,"IOException : " + e.getMessage(), e);
				}
			}	
		}

		@Override
		public String getType() {
			return mType;
		}

		@Override
		public Boolean isArray() {
			return mIsArray;
		}

		@Override
		public Integer getSortOrder() {
			return mSortOrder;
		}

		@Override
		public String getName() {
			return mName;
		}
	}

	private static final String TAG = "DataSchema";

	private String mClassName;
	private Map<String, IDataField> mFields = new HashMap<String, IDataSchema.IDataField>();

	public DataSchema(JsonReader jsonReader){
		try {
			jsonReader.setLenient(true);
			jsonReader.beginObject();
			while( jsonReader.hasNext()){
				final String innerName = jsonReader.nextName();
				final boolean isInnerNull = jsonReader.peek() == JsonToken.NULL;
				if( innerName.equals( Tags.classname.name() ) && !isInnerNull ) {
					this.mClassName = jsonReader.nextString();	
				} else {
					if( innerName.equals( Tags.fields.name() ) && !isInnerNull ) {
						jsonReader.beginArray();
						while( jsonReader.hasNext()){
							IDataField dataField = new DataField(jsonReader);
							this.mFields.put(dataField.getName(), dataField);
						}
						jsonReader.endArray();
					} else {
						jsonReader.skipValue();
					}	
				}	
			}
			jsonReader.endObject();
		} catch (IOException e) {
			if(Log.isLoggable(TAG, Log.ERROR)){
				Log.e(TAG,"IOException : " + e.getMessage(), e);
			}
		}	
	}

	@Override
	public String getClassName() {
		return mClassName;
	}

	@Override
	public Map<String, IDataField> getFields() {
		return mFields ;
	}

}
