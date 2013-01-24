package com.quickblox.android.framework.base.net.results;

import com.google.gson.Gson;
import com.quickblox.android.framework.base.models.QBNumber;

/**
 * User: Oleg Soroka
 * Date: 09.10.12
 * Time: 11:55
 */
public class QBNumberResult extends Result {

    private Double value;

    public QBNumberResult(Double value) {
        this.value = value;
    }

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
        QBNumber number = gson.fromJson(stringToParse, QBNumber.class);
        value = number.getValue();
    }

    //

    public Double getValue() {
        return value;
    }
}