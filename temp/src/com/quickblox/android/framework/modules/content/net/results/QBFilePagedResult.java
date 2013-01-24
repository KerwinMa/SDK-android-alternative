package com.quickblox.android.framework.modules.content.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quickblox.android.framework.base.helpers.StringifyArrayList;
import com.quickblox.android.framework.base.net.results.PagedResult;
import com.quickblox.android.framework.modules.content.models.QBFile;
import com.quickblox.android.framework.modules.content.models.QBFilePaged;
import com.quickblox.android.framework.modules.content.models.QBFileWrap;
import com.quickblox.android.framework.modules.content.models.deserializers.QBFileStatusDeserializer;
import com.quickblox.android.framework.modules.messages.models.QBFileStatus;
import com.quickblox.android.framework.modules.users.models.deserializers.QBStringifyArrayListDeserializer;

import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 28.08.12
 * Time: 14:46
 */
public class QBFilePagedResult extends PagedResult<QBFile> {

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {
            extractEntity();
        }
    }

    public void extractEntity() {
        String stringToParse = response.getRawBody();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(StringifyArrayList.class, new QBStringifyArrayListDeserializer());
        gsonBuilder.registerTypeAdapter(QBFileStatus.class, new QBFileStatusDeserializer());
        Gson gson = gsonBuilder.create();

        QBFilePaged filePaged = gson.fromJson(stringToParse, QBFilePaged.class);

        currentPage = filePaged.getCurrentPage();
        perPage = filePaged.getPerPage();
        totalEntries = filePaged.getTotalEntries();

        for (QBFileWrap item : filePaged.getItems()) {
            items.add(item.getEntity());
        }
    }

    //

    public ArrayList<QBFile> getFiles() {
        return items;
    }
}