package com.keer.bdql.Util;

import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.FulFill;
import com.bigchaindb.model.Transaction;
import com.keer.bdql.pojo.mongoDao.Transactions;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BigChainDbUtil {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建资产数据（不带附加信息）
     * @param assetData
     * @param publicKey
     * @param privateKey
     * @return
     */
    public String createAssert(Object assetData, EdDSAPublicKey publicKey, EdDSAPrivateKey privateKey){
        return createAssert(assetData,null,publicKey,privateKey);
    }

    /**
     * 创建资产（有附加数据集）
     * @param assetData
     * @param metaData

     * @param publicKey
     * @param privateKey
     * @return
     */
    public String createAssert(Object assetData,Object metaData,EdDSAPublicKey publicKey, EdDSAPrivateKey privateKey){
        Transaction sendTransaction = null;
        try {
            sendTransaction = BigchainDbTransactionBuilder
                    .init()
                    .operation(Operations.CREATE)
                    .addAssets(assetData, assetData.getClass())
                    .addMetaData(metaData)
                    .buildAndSign(publicKey, privateKey)
                    .sendTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if(checkTransaction(sendTransaction.getId())){
            return sendTransaction.getId();
        }else{
            return null;
        }
    }

    /**
     * 交易资产
     * @param assetId
     * @param metaData
     * @param fromPubKey
     * @param fromPriKey
     * @param toPubKey
     * @return
     */
    public String transferAsset(String assetId,Object metaData,EdDSAPublicKey fromPubKey,EdDSAPrivateKey fromPriKey,EdDSAPublicKey toPubKey){
        Transaction sendTransaction = null;
        try {
            sendTransaction = BigchainDbTransactionBuilder
                    .init()
                    .operation(Operations.TRANSFER)
                    .addAssets(assetId, String.class)
                    .addMetaData(metaData)
                    .addInput(null,creatTransactionInput(assetId),fromPubKey)
                    .addOutput("1",toPubKey)
                    .buildAndSign(toPubKey, fromPriKey)
                    .sendTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if(checkTransaction(sendTransaction.getId())){
            return sendTransaction.getId();
        }else{
            return null;
        }
    }

    /**
     * 创建交易输出
     * @param assetId
     * @return
     */
    private FulFill creatTransactionInput(String assetId){
        FulFill spendFrom = new FulFill();
        /**
         * 通过mongodb查找最后一笔交易
         */
        Query query=new Query(Criteria.where("operation").is("TRANSFER"));
        Pageable pageable = new PageRequest(0, 1);
        query.with(new Sort(Sort.Direction.ASC,"_id"));
        query.with(pageable);
        List<Transactions> transactions=mongoTemplate.find(query,Transactions.class);
        if(transactions.size()>0){
            assetId=transactions.get(0).getId();
        }
        spendFrom.setTransactionId(assetId);
        spendFrom.setOutputIndex(0);
        return spendFrom;
    }

    /**
     * 通过mongodb查询交易id是否存在
     * @param txid
     * @return
     */
    public boolean checkTransaction(String txid){
        Query query=new Query(Criteria.where("id").is(txid));
        return mongoTemplate.exists(query, Transactions.class);
    }

}
