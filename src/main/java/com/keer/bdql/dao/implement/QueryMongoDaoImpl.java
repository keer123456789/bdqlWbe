package com.keer.bdql.dao.implement;

import com.keer.bdql.dao.QueryDao;
import com.keer.bdql.pojo.mongoDao.Assets;
import com.keer.bdql.pojo.mongoDao.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        logger.info("查询mongo中，assets集合中的表名结果 ： "+set.toString());
        return new ArrayList(set);
    }

    /**
     * 获取mongodb中所有的附加数据集的表名
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
        logger.info("查询mongo中，metadata集合中的表名结果 ： "+set.toString());
        return new ArrayList(set);
    }
}
