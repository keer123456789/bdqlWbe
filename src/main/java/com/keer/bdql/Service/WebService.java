package com.keer.bdql.Service;


import com.keer.bdql.Domain.WebResult;

import java.io.IOException;

public interface WebService {
    WebResult getKey(String key);

    WebResult startConn(String url);

    WebResult getCloumnsName(String key);

    WebResult getTableData(String key, String operation);

    WebResult runBDQL(String BDQL);

    void Experiment(int i, int j) throws IOException, InterruptedException;
}
