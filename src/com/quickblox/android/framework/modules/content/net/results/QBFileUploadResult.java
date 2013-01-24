package com.quickblox.android.framework.modules.content.net.results;

import com.quickblox.android.framework.base.definitions.QBErrors;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.content.models.amazon.AmazonError;
import com.quickblox.android.framework.modules.content.models.amazon.PostResponse;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * User: Andriy Dmitrenko
 * Date: 28.08.12
 * Time: 16:07
 */
public class QBFileUploadResult extends Result {

    private Lo lo = new Lo(this);

    private PostResponse amazonPostResponse;

    public PostResponse getAmazonPostResponse() {
        return amazonPostResponse;
    }

    @Override
    protected void processResponse() {
//        super.processResponse();

        // Important: current response contains XML, not JSON.
        String stringToParse = response.getRawBody();

        // Using Simple XML for deserialization (http://simple.sourceforge.net/home.php).
        Serializer serializer = new Persister();
        try {
            amazonPostResponse = serializer.read(PostResponse.class, stringToParse);
        } catch (Exception e) {
            e.printStackTrace();

            amazonPostResponse = null;
            getErrors().add(QBErrors.FILE_UPLOAD_ERROR);

            try {
                AmazonError error = serializer.read(AmazonError.class, stringToParse);
                if (error.getMessage().equals(AmazonError.REQUEST_TIMEOUT)) {
                    getErrors().add(QBErrors.AMAZON_REQUEST_TIMEOUT);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        lo.g(LOG_MSG_OBJ_PARSED_XML + amazonPostResponse);
    }
}