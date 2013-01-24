package com.quickblox.android.framework.base.definitions;

/**
 * User: Oleg Soroka
 * Date: 02.10.12
 * Time: 12:38
 */
public class QBErrors {

    public static final String FILE_DOES_NOT_EXIST = "File does not exist";
    public static final String FILE_UPLOAD_ERROR = "File upload error";
    public static final String AMAZON_REQUEST_TIMEOUT = "Amazon request timeout";
    public static final String PASSED_OBJECT_IS_NOT_FILE = "Passed object is not file";
    public static final String INCORRECT_CONTENT_TYPE = "Incorrect content type";

    // Ошибки из REST выдачи

    public static final String UNAUTHORIZED = "Unauthorized";
    // CO: '{"code":null,"message":"Undefined class"}'
    public static final String UNDEFINED_CLASS = "Undefined class";
    // CO: '{"code":null,"message":"The resource wasn't found"}'
    public static final String RESOURCE_NOT_FOUND = "The resource wasn't found";
    // Content: '{"errors":["Size param is not set"]}'
    public static final String SIZE_NOT_SET = "Size param is not set";

}