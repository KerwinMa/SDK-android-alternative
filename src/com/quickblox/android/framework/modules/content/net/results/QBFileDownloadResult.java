package com.quickblox.android.framework.modules.content.net.results;

import com.quickblox.android.framework.base.net.results.Result;

import java.io.InputStream;

/**
 * User: Oleg Soroka
 * Date: 28.08.12
 * Time: 14:55
 */
public class QBFileDownloadResult extends Result {

    // TODO написать объект данных QBData

    private byte[] content;
    private InputStream contentStream;

    @Override
    protected void processResponse() {
        content = response.getContent();
        contentStream = response.getContentStream();
    }

    //

    public byte[] getContent() {
        return content;
    }

    public InputStream getContentStream() {
        return contentStream;
    }
}