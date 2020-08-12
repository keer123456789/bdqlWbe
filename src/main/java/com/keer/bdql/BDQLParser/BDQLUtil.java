package com.keer.bdql.BDQLParser;

import com.bigchaindb.model.Transaction;
import com.bigchaindb.model.Transactions;
import com.google.gson.internal.LinkedTreeMap;
import com.keer.bdql.Bigchaindb.BigchainDBUtil;
import com.keer.bdql.pojo.WebResult;
import com.keer.bdql.pojo.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class BDQLUtil {

    private static Logger logger = LoggerFactory.getLogger(BDQLUtil.class);

    @Autowired
    BDQLParser bdqlParser;

    @Autowired
    BigchainDBUtil bigchainDBUtil;


    /**
     * 去掉字符串的第一个和最后一个单引号
     * @param s
     * @return
     */
    public  String fixString(String s){
        if(s.substring(0,1).equals("'")&&s.substring(s.length()-1,s.length()).equals("'")){
            return s.substring(1,s.length()-1);
        }
        return s;
    }


    /**
     * 开始解析BDQL  不出意外的话这个函数是第一个使用
     *
     * @param sql
     * @return
     */
    public WebResult work(String sql) {
        WebResult result = new WebResult();
        if(sql.split(";").length!=1){
            result.setData(WebResult.BDQL_ERROR_GRAMMAR);
            result.setMessage(WebResult.MSG_BDQL_ERROR_GRAMMAR+",不支持批量查询");
            logger.error("BDQL语句："+sql+",目前不支持批量操作");
            return result;
        }
        return bdqlParser.BDQLParser(sql.toUpperCase());
    }


    /**
     * 通过公钥获得全部表数据
     * @param pubkey
     * @return
     * @throws IOException
     */
    public  Map<String, Table> getAlltablesByPubKey(String pubkey) throws IOException {

        Map<String,Table> result=new HashMap<String, Table>();


        Transactions transactions= bigchainDBUtil.getAllTransactionByPubKey(pubkey);
        LinkedTreeMap map=new LinkedTreeMap();
        for(Transaction transaction:transactions.getTransactions()){
            if(transaction.getOperation().equals("\"CREATE\"")){
                map=(LinkedTreeMap) transaction.getAsset().getData();
                if(!result.containsKey(map.get("tableName"))){
                    Table table=new Table();
                    table.setTableName(map.get("tableName").toString());
                    table.setType("CREATE");
                    table.setColumnName(transaction);
//                    table.setRowData(transaction);
                    result.put(table.getTableName(),table);
                }else{
                    Table table=result.get(map.get("tableName"));
                    table.setColumnName(transaction);
//                    table.setRowData(transaction);
                    result.put(table.getTableName(),table);
                }
            }else{
                map=(LinkedTreeMap) transaction.getMetaData();
                if(!result.containsKey(map.get("tableName"))){
                    Table table=new Table();
                    table.setTableName(map.get("tableName").toString());
                    table.setType("TRANSFER");
                    table.setColumnName(transaction);
//                    table.setRowData(transaction);
                    result.put(table.getTableName(),table);
                }else{
                    Table table=result.get(map.get("tableName"));
                    table.setColumnName(transaction);
//                    table.setRowData(transaction);
                    result.put(table.getTableName(),table);
                }
            }
        }


        return result;
    }


    public static void main(String[] args) throws IOException {
        String a="adfaDDD;";
        System.out.println(a.toUpperCase());
    }
}