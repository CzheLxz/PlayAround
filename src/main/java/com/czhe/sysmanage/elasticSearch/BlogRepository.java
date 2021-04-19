package com.czhe.sysmanage.elasticSearch;

import com.czhe.sysmanage.entity.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author czhe
 * @version 1.0
 * @create 2021/4/12 17:26
 * @description elasticSearch操作Dao
 **/
@Component
public interface BlogRepository extends ElasticsearchRepository<Blog, String> {
}
