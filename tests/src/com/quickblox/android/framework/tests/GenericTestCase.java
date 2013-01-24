package com.quickblox.android.framework.tests;

import android.test.InstrumentationTestCase;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.helpers.StringUtils;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.users.models.QBUser;
import com.quickblox.android.framework.tests.helpers.JsonValidator;
import org.eel.kitchen.jsonschema.report.ValidationReport;

/**
 * User: Oleg Soroka
 * Date: 16.08.12
 * Time: 11:46
 */
public class GenericTestCase extends InstrumentationTestCase {

    Lo lo = new Lo(this.getClass().getSimpleName());

    // Main test user account
    String login = TestConfig.USER_LOGIN;
    String password = TestConfig.USER_PASSWORD;
    protected QBUser testUserAccount = new QBUser(login, password);

    protected void checkEmptyResponseBody(Result result) {
        if (StringUtils.isEmpty(result.getRawBody())) {
            assertTrue(true);
        } else {
            fail("response body should be empty");
        }
    }

    protected void checkIfSuccess(Result result) {
        if (result.isSuccess()) {
            assertTrue(true);
        } else {
            fail("this response should not contains errors");
        }
    }

    protected void checkIfNotSuccess(Result result) {
        if (!result.isSuccess()) {
            assertTrue(true);
        } else {
            fail("this response should contains errors");
        }
    }

    protected void checkHttpStatus(int successCode, Result result) {
        if (result.getStatusCode() == successCode) {
            assertTrue(true);
        } else {
            fail("result: " + result.getStatusCode()
                    + " but must be: " + successCode);
        }
    }

    protected void checkSchemaValidation(String schema, Result result) {
        lo.g("JSON schema validation : %s", schema);
        ValidationReport report = JsonValidator.validate(schema, result);
        if (report.getMessages().isEmpty()) {
            assertTrue(true);
        } else {
            StringBuilder messages = new StringBuilder("\n");
            int i = 0;
            for (String message : report.getMessages()) {
                messages.append(++i + ") ").append(message).append("\n");
            }
            fail("JSON Schema validation errors : " + messages.toString());
        }
    }
}