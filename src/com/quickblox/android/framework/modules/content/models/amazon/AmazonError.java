package com.quickblox.android.framework.modules.content.models.amazon;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * User: Oleg Soroka
 * Date: 06.10.12
 * Time: 12:22
 */
@Root(name = "AmazonError")
public class AmazonError {

    public static final String REQUEST_TIMEOUT = "RequestTimeout";

    // Parses following xml
    // <AmazonError>
    //      <Code>RequestTimeout</Code>
    //      <Message>Your socket connection to the server was not read from or written to within the timeout period.
    //      Idle connections will be closed.</Message>
    //      <RequestId>6A4FD2F5E99AC852</RequestId>
    //      <HostId>XzEsV7hu3IS8QAH/bkZ0IODbBU2dWROzTn86RHyeeUihhbuEptQonMSci3aj2h+k</HostId>
    // </AmazonError>

    @Element(name = "Code")
    String code;

    @Element(name = "Message")
    String message;

    @Element(name = "RequestId")
    String requestId;

    @Element(name = "HostId")
    String hostId;

    public AmazonError() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    @Override
    public String toString() {
        return "AmazonError{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", requestId='" + requestId + '\'' +
                ", hostId='" + hostId + '\'' +
                '}';
    }
}