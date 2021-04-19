package com.czhe.sysmanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.czhe.sysmanage.elasticSearch.BlogRepository;
import com.czhe.sysmanage.entity.Blog;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author czhe
 * @version 1.0
 * @create 2021/4/12 15:27
 * @description
 **/
@RequestMapping("/blog")
@RestController
public class BlogController {

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Autowired
    private BlogRepository repository;


    @GetMapping("/batchSave")
    public String batchSave() {
        List<Blog> blogList = new ArrayList<>();
        Blog blog = new Blog(UUID.randomUUID().toString(), "Czhezhe", 10001, 999, 666, "do some thing");
        blogList.add(blog);
        for (int i = 1; i <= 10; i++) {
            Blog blo = new Blog(UUID.randomUUID().toString(), "TEST DATA", (10001 + i), (i * 10), (i * 6), ("do some thing" + i));
            blogList.add(blo);
        }
        List<IndexQuery> queries = new ArrayList<>();
        IndexQuery indexQuery;
        int counter = 0;
        for (Blog item : blogList) {
            indexQuery = new IndexQuery();
            indexQuery.setId(item.getId());
            indexQuery.setSource(JSONObject.toJSONString(item));
            indexQuery.setIndexName("blog_index");
            indexQuery.setType("blog_type");
            queries.add(indexQuery);
            //分批提交索引
            if (counter != 0 && counter % 1000 == 0) {
                restTemplate.bulkIndex(queries);
                queries.clear();
                System.out.println("bulkIndex counter : " + counter);
            }
            counter++;
        }
        //不足批的索引最后不要忘记提交
        if (queries.size() > 0) {
            restTemplate.bulkIndex(queries);
        }
        restTemplate.refresh("blog_index");
        return "add success";
    }


    @GetMapping("/addList")
    public String addList() {
        List<Blog> blogList = new ArrayList<>();
        Blog blog = new Blog(UUID.randomUUID().toString(), "Czhezhe", 10001, 999, 666, "do some thing");
        blogList.add(blog);
        for (int i = 1; i <= 10; i++) {
            Blog blo = new Blog(UUID.randomUUID().toString(), "TEST DATA", (10001 + i), (i * 10), (i * 6), ("do some thing" + i));
            blogList.add(blo);
        }
        repository.saveAll(blogList);
        return "add success";
    }

    @PostMapping("/query")
    public String queryMatchList(@RequestParam("field") String field, @RequestParam("value") String value) {
        MatchQueryBuilder builder = QueryBuilders.matchQuery(field, value);
        SearchQuery searchQuery = new NativeSearchQuery(builder);
        List<Blog> result = restTemplate.queryForList(searchQuery, Blog.class);
        return result.toString();
    }

}