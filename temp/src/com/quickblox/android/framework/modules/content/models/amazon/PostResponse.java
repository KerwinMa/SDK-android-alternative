package com.quickblox.android.framework.modules.content.models.amazon;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * User: Oleg Soroka
 * Date: 04.10.12
 * Time: 18:01
 */
@Root(name = "PostResponse")
public class PostResponse {

    // Parses following xml
    // <PostResponse>
    //      <Location>http://qbprod.s3.amazonaws.com/56f94b81e9fa447899731c5ec1c5fe0500</Location>
    //      <Bucket>qbprod</Bucket>
    //      <Key>56f94b81e9fa447899731c5ec1c5fe0500</Key>
    //      <ETag>"92a71b6aa0ac11e9a9d3d4e50afd6e90"</ETag>
    // </PostResponse>

    @Element(name = "Location")
    String location;

    @Element(name = "Bucket")
    String bucket;

    @Element(name = "Key")
    String key;

    @Element(name = "ETag")
    String eTag;

    public PostResponse() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    @Override
    public String toString() {
        return "PostResponse{" +
                "location='" + location + '\'' +
                ", bucket='" + bucket + '\'' +
                ", key='" + key + '\'' +
                ", eTag='" + eTag + '\'' +
                '}';
    }
}