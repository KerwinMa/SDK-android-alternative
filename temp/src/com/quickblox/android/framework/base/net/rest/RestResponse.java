package com.quickblox.android.framework.base.net.rest;

import com.quickblox.android.framework.base.definitions.ConstsGlobal;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.helpers.ToStringHelper;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * User: Igor Khomenko
 */
public class RestResponse {

    private Lo lo = new Lo(this);

    private UUID uuid;
    private final HttpResponse httpResponse;
    private String responseBodyString;
    private byte[] content;
    private IOException ioException;

    public RestResponse(HttpResponse response, UUID uuid, IOException ioException) {
        this.uuid = uuid;
        this.httpResponse = response;
        this.ioException = ioException;
        lo.g(this);
    }

    public int getStatusCode() {
        if (httpResponse != null) {
            return httpResponse.getStatusLine().getStatusCode();
        }
        return 0;
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        if (httpResponse != null) {
            for (Header header : httpResponse.getAllHeaders()) {
                headers.put(header.getName(), header.getValue());
            }
        }
        return headers;
    }

    public byte[] getContent() {
        try {
            if (content == null) {
                if (httpResponse != null) {
                    content = IOUtils.toByteArray(httpResponse.getEntity().getContent());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public InputStream getContentStream() {
        return new ByteArrayInputStream(getContent());
    }

    public String getRawBody() {
        if (responseBodyString == null) {
            try {
                if (getContent() != null) {
                    responseBodyString = IOUtils.toString(getContent(), ConstsGlobal.DEFAULT_ENCODING);
                } else {
                    responseBodyString = ConstsGlobal.EMPTY_STRING;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseBodyString;
    }

    public String getContentType() {
        if (httpResponse != null) {
            httpResponse.getFirstHeader("Content-Type").getValue();
        }
        return null;
    }

    public IOException getIOException() {
        return ioException;
    }

    @Override
    public String toString() {
        String tab = "    ";
        return String.format("*********************************************************\n" +
                "*** RESPONSE *** %s ***\nSTATUS : %s " +
                "\nHEADERS\n%s\nBODY\n    '%s'",
                String.valueOf(uuid),
                getStatusCode(),
                ToStringHelper.toString(new TreeMap(getHeaders()), tab),
                getRawBody());
    }
}