package com.keer.bdql.Domain.mongo;

import java.io.Serializable;
import java.util.List;
/**
 * Transaction数据集中的Output对象
 */
public class Output implements Serializable {
    private String amount;
    private Condition condition;
    private List<String> public_keys;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public List<String> getPublic_keys() {
        return public_keys;
    }

    public void setPublic_keys(List<String> public_keys) {
        this.public_keys = public_keys;
    }
}
