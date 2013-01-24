package com.quickblox.android.framework.modules.users.models.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.quickblox.android.framework.base.helpers.StringifyArrayList;

import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * User: Oleg Soroka
 * Date: 28.09.12
 * Time: 15:50
 */
public class QBStringifyArrayListDeserializer implements JsonDeserializer<StringifyArrayList> {
    @Override
    public StringifyArrayList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        String str = json.toString();
        StringifyArrayList list = new StringifyArrayList(Arrays.asList(str.split(",")));
        return list;
    }
}