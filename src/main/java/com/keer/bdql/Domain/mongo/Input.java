package com.keer.bdql.Domain.mongo;

import java.io.Serializable;
import java.util.List;
/**
 * Transaction数据集中的intput对象
 */
public class Input implements Serializable {
    private String fulfillment;
    private Fulfills fulfills;
    private List<String> owners_before;

    public String getFulfillment() {
        return fulfillment;
    }

    public void setFulfillment(String fulfillment) {
        this.fulfillment = fulfillment;
    }

    public Fulfills getFulfills() {
        return fulfills;
    }

    public void setFulfills(Fulfills fulfills) {
        this.fulfills = fulfills;
    }

    public List<String> getOwners_before() {
        return owners_before;
    }

    public void setOwners_before(List<String> owners_before) {
        this.owners_before = owners_before;
    }
}
