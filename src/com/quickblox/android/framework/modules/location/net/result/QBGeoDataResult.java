package com.quickblox.android.framework.modules.location.net.result;

import com.google.gson.Gson;
import com.quickblox.android.framework.modules.location.models.QBLocation;
import com.quickblox.android.framework.modules.location.definitions.Consts;
import org.json.JSONObject;

/**
 * User: Oleg Soroka
 * Date: 14.09.12
 * Time: 20:12
 */
public class QBGeoDataResult extends QBLocationResult {

    public QBLocation extractLocation(String stringToParse) {
        Gson gson = new Gson();
        try {
            JSONObject object = new JSONObject(stringToParse);
            stringToParse = object.getString(Consts.GEO_DATA);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gson.fromJson(stringToParse, QBLocation.class);
    }

}