package com.keer.bdql.dao.implement;

import com.keer.bdql.dao.BDQLDao;
import com.keer.bdql.pojo.mongoDao.Assets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public class BDQLDaoImpl implements BDQLDao {

    @Autowired
    MongoTemplate mongoTemplate;
    public List<Assets> queryAssets(String tableName, String id, List<String> fields) {
        Query query = new Query();
        query.addCriteria(Criteria.where("data.tableName").is(tableName));
        if (id != null) {
            query.addCriteria(Criteria.where("id").is(id));
        }
        if (fields!=null||fields.size() > 0) {
            for (String field : fields) {
                query.fields().include(field);
            }
        }
        return mongoTemplate.find(query,Assets.class);
    }


}
