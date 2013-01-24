package com.quickblox.android.framework.modules.content.net.queries;

import android.net.Uri;
import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.definitions.ServerZone;
import com.quickblox.android.framework.base.models.QBSettings;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.content.definitions.Consts;
import com.quickblox.android.framework.modules.content.net.results.QBFileUploadResult;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * User: Andriy Dmitrenko
 * Date: 28.08.12
 * Time: 14:00
 */
public class QueryUploadFile extends Query {

    private String params;
    private File file;

    public QueryUploadFile(File file, String params) {
        this.file = file;
        this.params = params;
    }

    @Override
    protected void setParams(RestRequest request) {
        MultipartEntity multipartEntity = new MultipartEntity();

        try {
            List<NameValuePair> parameters = URLEncodedUtils.parse(new URI(params), null);
            for (NameValuePair p : parameters) {
                addPart(multipartEntity, p.getName(), p.getValue());
            }
            multipartEntity.addPart(Consts.FILE, new FileBody(file));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        request.setMultipartEntity(multipartEntity);
    }

    protected void putValue(Map<String, String> parametersMap, Uri uri, String key) {
        putValue(parametersMap, key, uri.getQueryParameter(key));
    }

    private void addPart(MultipartEntity entity, String param, String value) throws UnsupportedEncodingException {
        entity.addPart(param, new StringBody(value));
        System.out.println(param + "=" + value);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.POST);
    }

    @Override
    public String getUrl() {
        String endpoint = Consts.AMAZON_ENDPOINT;
        if (QBSettings.getInstance().getServerZone().equals(ServerZone.STAGE)) {
            endpoint = Consts.AMAZON_STAGE_ENDPOINT;
        }

        return endpoint;
    }

    @Override
    public Class getResultClass() {
        return QBFileUploadResult.class;
    }
}