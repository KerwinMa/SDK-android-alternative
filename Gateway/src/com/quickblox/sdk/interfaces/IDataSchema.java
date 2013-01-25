package com.quickblox.sdk.interfaces;

import java.util.Map;

public interface IDataSchema {
	enum Tags{
		classname,
		fields
	}

	public interface IDataField{
		
		enum Tags{
			name,
			type,
			array,
			order
		}
		String getName();
		/**
		 * <ul>
		 * <li>boolean</li>
			<li>string</li>
			<li>integer</li>
			<li>float</li>
			<li>integerDate - Outputs Date</li>
			<li>stringDate - Outputs Date</li>
		 * </ul>
		 * @return
		 */
		String getType();
		Boolean isArray();
		Integer getSortOrder();
	}
	String getClassName();
	Map<String, IDataField> getFields();
}
