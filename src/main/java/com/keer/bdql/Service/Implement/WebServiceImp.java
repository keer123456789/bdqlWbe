package com.keer.bdql.Service.Implement;

import com.bigchaindb.model.Assets;

import com.keer.bdql.BDQLParser.BDQLUtil;
import com.keer.bdql.Bigchaindb.BigchainDBRunner;
import com.keer.bdql.Bigchaindb.BigchainDBUtil;
import com.keer.bdql.Bigchaindb.KeyPairHolder;
import com.keer.bdql.pojo.MetaData;
import com.keer.bdql.pojo.WebResult;
import com.keer.bdql.pojo.Table;
import com.keer.bdql.Service.WebService;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebServiceImp implements WebService {
    private static Logger logger = LoggerFactory.getLogger(WebServiceImp.class);

    @Autowired
    KeyPairHolder keyPairHolder;

    @Autowired
    BigchainDBRunner bigchainDBRunner;

    @Autowired
    BDQLUtil bdqlUtil;

    @Autowired
    BigchainDBUtil bigchainDBUtil;




    /**
     * 获取秘钥
     *
     * @return
     */
    @Override
    public WebResult getKey(String key) {
        WebResult parserResult = new WebResult();
        if (keyPairHolder.SaveKeyPairToTXT(keyPairHolder.getKeyPairFromString(key))) {
            parserResult.setStatus(WebResult.SUCCESS);
            parserResult.setMessage("success");
            logger.info("设置数据密钥成功");
        } else {
            parserResult.setMessage("fail");
            parserResult.setStatus(WebResult.ERROR);
            logger.error("设置数据密钥失败");
        }
        return parserResult;
    }

    @Override
    public WebResult startConn(String url) {
        WebResult parserResult = new WebResult();
        if (bigchainDBRunner.StartConn(url)) {
            parserResult.setMessage("连接BigchainDB节点成功！！！");
            parserResult.setStatus(WebResult.SUCCESS);
            parserResult.setData(true);
        } else {
            parserResult.setMessage("连接BigchainDB节点失败成功！");
            parserResult.setStatus(WebResult.ERROR);
            parserResult.setData(false);
        }
        return parserResult;
    }

    @Override
    public WebResult getCloumnsName(String key) {
        WebResult parserResult = new WebResult();
        Map<String, Table> map = null;
        try {
            String publicKey=keyPairHolder.pubKeyToString((EdDSAPublicKey) keyPairHolder.getKeyPairFromString(key).getPublic());
            map = bdqlUtil.getAlltablesByPubKey(publicKey);
        } catch (IOException e) {
            e.printStackTrace();
            parserResult.setData(null);
            parserResult.setMessage("表名获取失败！！");
            parserResult.setStatus(WebResult.ERROR);
            return parserResult;
        }
        List<Map> list = buildJstreeData(map);
        parserResult.setData(list);
        parserResult.setMessage("表名获取成功！！");
        parserResult.setStatus(WebResult.SUCCESS);
        return parserResult;
    }

    @Override
    public WebResult getTableData(String key, String operation) {
        WebResult parserResult = new WebResult();
        Table table = new Table();
        table.setTableName(key);
        if (operation.equals("asset")) {
            Assets assets = bigchainDBUtil.getAssetByKey(key);

            if (assets != null) {
                table.setTableDataWithColumnName(assets);
                table.setType("CREATE");
            } else {
                parserResult.setData(null);
                parserResult.setMessage("查询表数据错误！！！");
                parserResult.setStatus(WebResult.ERROR);
                return parserResult;//TODO 错误
            }
        } else {
            List<MetaData> metaDataList = bigchainDBUtil.getMetaDatasByKey(key);
            if (metaDataList != null) {
                table.setTableDataWithCloumnName(metaDataList);
                table.setType("TRANSFER");
            } else {
                parserResult.setData(null);
                parserResult.setMessage("查询表数据错误！！！");
                parserResult.setStatus(WebResult.ERROR);
                return parserResult;//TODO 错误
            }
        }

        Map map = buildjqGridData(table);
        parserResult.setStatus(WebResult.SUCCESS);
        parserResult.setMessage("表：" + key + "  数据查询成功！！");
        parserResult.setData(map);
        return parserResult;
    }

    @Override
    public WebResult runBDQL(String BDQL) {
        WebResult parserResult = bdqlUtil.work(BDQL);
        if (parserResult.getMessage().equals("select")) {
            parserResult.setData(buildjqGridData((Table) parserResult.getData()));
        }
        return parserResult;


    }

    private List<Map> buildJstreeData(Map<String, Table> map) {
        List<Map> list = new ArrayList<>();
        Map bigchaindb = new HashMap();
        bigchaindb.put("id", "bigchaindb");
        bigchaindb.put("parent", "#");
        bigchaindb.put("text", "bigchaindb");

        Map asset = new HashMap();
        asset.put("id", "asset");
        asset.put("parent", "bigchaindb");
        asset.put("text", "asset");

        Map metadata = new HashMap();
        metadata.put("id", "metadata");
        metadata.put("parent", "bigchaindb");
        metadata.put("text", "metadata");

        list.add(bigchaindb);
        list.add(asset);
        list.add(metadata);
        for (String key : map.keySet()) {
            Map node = new HashMap();
            if (map.get(key).getType().equals("CREATE")) {
                node.put("id", key);
                node.put("parent", "asset");
                node.put("text", key);
            } else {
                node.put("id", key);
                node.put("parent", "metadata");
                node.put("text", key);
            }
            list.add(node);
        }
        return list;
    }


    private Map buildjqGridData(Table table) {
        List cloumNames = table.getColumnName();
        List data = table.getData();
        List cloumAttr = new ArrayList();
        for (Object cloumName : cloumNames) {
            Map map = new HashMap<>();
            map.put("name", cloumName.toString());
            map.put("index", cloumName.toString());
            map.put("width", 60);
            cloumAttr.add(map);
        }
        Map map = new HashMap();
        map.put("cloumNames", cloumNames);
        map.put("data", data);
        map.put("cloumAttr", cloumAttr);
        return map;
    }

    /**
     * 以毫秒为单位
     * @param i asset个数
     * @param j 每个asset的metadata个数
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void Experiment(int i, int j) throws IOException, InterruptedException {
//        Long startTime=System.currentTimeMillis();//开始时间
//        ParserResult result = new ParserResult();
//        List<Long> insertTime=new ArrayList<>();
//        List<Long> updateTime=new ArrayList<>();
//        bigchainDBRunner.StartConn();
//        for (int m = 0; m < j; m++) {
//            Long insertStartTime =System.currentTimeMillis();
//            result = bdqlUtil.work("INSERT INTO Computer (id, ip,mac,size,cpu,ROM,RAM) VALUES ('" + (m + 1) + "','" + (m + 2) + "','Champs-Elysees','" + (m+ 3) + "','i7','" + (m + 4) + "','" + (m + 5) + "')");
//            insertTime.add(System.currentTimeMillis()-insertStartTime);
//            String id = (String) result.getData();
//            logger.info("资产ID：" + id);
//
//            logger.info(bigchainDBUtil.checkTransactionExit(id) + "");
//            ParserResult result1 = new ParserResult();
//            for (int n = 0; n < i; n++) {
//                Long updateStartTime =System.currentTimeMillis();
//                result1 = bdqlUtil.work("UPDATE Person SET FirstName = '" + n + "' , SecondName='" + m + "',age= '" + (m + n) + "',time='" + (m + n + 10) + "' WHERE ID='" + id + "'");
//                updateTime.add(System.currentTimeMillis()-updateStartTime);
//                logger.info("交易ID：" + result1.getData());
//                Thread.sleep(500);
//
//            }
//        }
//        Long endTime=System.currentTimeMillis();
//        logger.info("本次insert语句个数");
//        Outputs outputs = OutputsApi.getOutputs(keyPairHolder.pubKeyToString(keyPairHolder.getPublic()));
//        logger.info("交易总数1：" + outputs.getOutput().size());
//        for (Output output : outputs.getOutput()) {
//            logger.info("交易ID：" + output.getTransactionId() + ",密钥：" + output.getPublicKeys());
//        }

    }
}
