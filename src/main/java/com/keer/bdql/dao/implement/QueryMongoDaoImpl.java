package com.keer.bdql.dao.implement;

import com.keer.bdql.dao.QueryDao;
import com.keer.bdql.pojo.Table;
import com.keer.bdql.pojo.mongoDao.Assets;
import com.keer.bdql.pojo.mongoDao.Metadata;
import com.keer.bdql.pojo.mongoDao.TableData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Repository
public class QueryMongoDaoImpl implements QueryDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 获取mongodb中所有的关键数据集的表名
     *
     * @return
     */
    public List getAssetTableName() {
        List<Assets> assets = mongoTemplate.findAll(Assets.class);
        HashSet<String> set = new HashSet<>();
        for (Assets assets1 : assets) {
            set.add(assets1.getData().getTableName());
        }
        logger.info("查询mongo中，assets集合中的表名结果 ： " + set.toString());
        return new ArrayList(set);
    }

    /**
     * 获取mongodb中所有的附加数据集的表名
     *
     * @return
     */
    @Override
    public List getMetadataTableName() {
        List<Metadata> metadataList = mongoTemplate.findAll(Metadata.class);
        HashSet<String> set = new HashSet<>();
        for (Metadata metadata : metadataList) {
            if (metadata.getMetadata() != null) {
                set.add(metadata.getMetadata().getTableName());
            }
        }
        logger.info("查询mongo中，metadata集合中的表名结果 ： " + set.toString());
        return new ArrayList(set);
    }

    /**
     * 分页获取数据表的数据
     * @param tableName
     * @param operation CREATE or TRANSFER
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Table getTableData(String tableName, String operation, int pageNum, int pageSize) {
        Table table = new Table();
        table.setType(operation);
        table.setTableName(tableName);
        if (operation.equals("CREATE")) {
            Query query = Query.query(Criteria.where("data.tableName").is(tableName));
            Pageable pageable = new PageRequest(pageNum, pageSize); // get 5 profiles on a page
            query.with(pageable);
            List<Assets> data = mongoTemplate.find(query, Assets.class);
            for (Assets assets : data) {
                table.addTableData(assets.getData().getTableData());
                for (Object o : assets.getData().getTableData().keySet()) {
                    table.addColumn((String) o);
                }
            }
        } else {
            Query query = Query.query(Criteria.where("metadata.tableName").is(tableName));
            Pageable pageable = new PageRequest(pageNum, pageSize); // get 5 profiles on a page
            query.with(pageable);
            List<Metadata> data = mongoTemplate.find(query, Metadata.class);
            for (Metadata metadata : data) {
                table.addTableData(metadata.getMetadata().getTableData());
                for (Object o : metadata.getMetadata().getTableData().keySet()) {
                    table.addColumn((String) o);
                }
            }
        }

        return table;
    }

    /**
     *  计算相应数据表下的数据量
     * @param tableName
     * @param operation
     * @return
     */
    @Override
    public long countTableData(String tableName, String operation) {
        if (operation.equals("CREATE")) {
            Query query = new Query(Criteria.where("data.tableName").is(tableName));
            return mongoTemplate.count(query,Assets.class);
        }else{
            Query query=new Query(Criteria.where("metadata.tableName").is(tableName));
            return mongoTemplate.count(query,Metadata.class);
        }
    }
}
