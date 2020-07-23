package com.keer.bdql.Domain.mongo;

import java.io.Serializable;

/**
 * Transaction数据集中的outputs对象中的condition对象
 */
public class Condition implements Serializable {
    private Details details;
    private String uri;

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
