package com.quickblox.android.framework.modules.location.net.result;

import com.google.gson.Gson;
import com.quickblox.android.framework.base.net.results.PagedResult;
import com.quickblox.android.framework.modules.location.models.QBLocation;
import com.quickblox.android.framework.modules.location.models.QBLocationPaged;
import com.quickblox.android.framework.modules.location.models.QBLocationWrap;

import java.util.ArrayList;

/**
 * @author: Oleg Soroka
 * Date: 17.07.12
 * Time: 15:18
 */
public class QBLocationPagedResult extends PagedResult<QBLocation> {

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {
            extractEntity();
        }
    }

    @Override
    protected void extractEntity() {
        String stringToParse = response.getRawBody();

        Gson gson = new Gson();
        QBLocationPaged locationPaged = gson.fromJson(stringToParse, QBLocationPaged.class);

        currentPage = locationPaged.getCurrentPage();
        perPage = locationPaged.getPerPage();
        totalEntries = locationPaged.getTotalEntries();

        for (QBLocationWrap wrap : locationPaged.getItems()) {
            items.add(wrap.getEntity());
        }
    }

    @Override
    public String toString() {
        return "QBLocationPagedResult{" +
                "locations=" + items +
                ", totalEntries=" + totalEntries +
                ", currentPage=" + currentPage +
                ", perPage=" + perPage +
                '}';
    }

    //

    public ArrayList<QBLocation> getLocations() {
        return items;
    }

    public void setLocations(ArrayList<QBLocation> locations) {
        this.items = locations;
    }
}