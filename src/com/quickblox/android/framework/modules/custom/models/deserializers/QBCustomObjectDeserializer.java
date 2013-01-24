package com.quickblox.android.framework.modules.custom.models.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.quickblox.android.framework.modules.custom.definitions.Consts;
import com.quickblox.android.framework.modules.custom.models.QBCustomObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Iterator;

/**
 * User: Oleg Soroka
 * Date: 03.10.12
 * Time: 00:21
 */
public class QBCustomObjectDeserializer implements JsonDeserializer<QBCustomObject> {

    private String className;

    public QBCustomObjectDeserializer(String className) {
        this.className = className;
    }

    @Override
    public QBCustomObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        // Парсится вручную, т.к. реальная модель CustomObject в выдаче
        // отличается от объектной, наследованной от QBEntity.
        // В модели отсутствует обязательно поле id, а также модель
        // имеет тип полей created_at и updated_at отличный от базовой модели
        // -- Long вместо Date.
        QBCustomObject co = new QBCustomObject();

        try {
            JSONObject jsonObject = new JSONObject(json.toString());
            Iterator<?> keys = jsonObject.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                String value = jsonObject.getString(key);

                if (key.equals(Consts.ENTITY_FIELD_ID)) {
                    co.setCustomObjectId(value);
                } else if (key.equals(Consts.ENTITY_FIELD_USER_ID)) {
                    int userId = Integer.parseInt(value);
                    co.setUserId(userId);
                } else if (key.equals(Consts.ENTITY_FIELD_CREATED_AT)) {
                    long createdAt = Long.parseLong(value);
                    co.setCreatedAt(new Date(createdAt));
                } else if (key.equals(Consts.ENTITY_FIELD_UPDATED_AT)) {
                    long updatedAt = Long.parseLong(value);
                    co.setUpdatedAt(new Date(updatedAt));
                } else {
                    if (value.equals("null")) {
                        value = null;
                    }
                    co.getFields().put(key, value);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        co.setClassName(className);

        return co;
    }
}