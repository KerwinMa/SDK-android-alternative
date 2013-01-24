package com.quickblox.android.framework.modules.content.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntityWrap;

/**
 * User: Oleg Soroka
 * Date: 04.10.12
 * Time: 12:18
 */
public class QBFileWrap extends QBEntityWrap<QBFile> {

    @SerializedName("blob")
    private QBFile file;

    public QBFileWrap() {
    }

    public QBFile getFile() {
        return file;
    }

    public void setFile(QBFile file) {
        this.file = file;
    }

    @Override
    public QBFile getEntity() {
        return file;
    }
}