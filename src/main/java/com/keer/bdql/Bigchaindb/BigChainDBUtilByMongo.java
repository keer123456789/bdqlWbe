package com.keer.bdql.Bigchaindb;

import com.keer.bdql.pojo.mongoDao.Operation;
import com.keer.bdql.pojo.mongoDao.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BigChainDBUtilByMongo {
    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 根据公钥查询所有未交易的资产id
     *
     * @param pubkey
     * @return
     */
    public List<String> queryAsset(String pubkey) {
        Query query = new Query(Criteria.where("outputs.public_keys").is(pubkey));
        List<Transactions> transactions = mongoTemplate.find(query, Transactions.class);
        List<String> assets = new ArrayList<>();
        for (Transactions transactions1 : transactions) {
            if (transactions1.getOperation().equals(Operation.CREATE)) {
                assets.add(transactions1.getId());
            } else {
                assets.add(transactions1.getAsset().getId());
            }
        }
        /**
         * 去重
         */
        return assets.stream().distinct().collect(Collectors.toList());
    }



}
