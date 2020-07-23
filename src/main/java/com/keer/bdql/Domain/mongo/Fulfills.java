package com.keer.bdql.Domain.mongo;

import java.io.Serializable;

/**
 * Transaction数据集中的intputs对象中的Fulfills对象
 */
public class Fulfills implements Serializable {
    private String output_index;
    private String transaction_id;

    public String getOutput_index() {
        return output_index;
    }

    public void setOutput_index(String output_index) {
        this.output_index = output_index;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
}
