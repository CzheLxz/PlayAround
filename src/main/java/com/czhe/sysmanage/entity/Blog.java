package com.czhe.sysmanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author czhe
 * @version 1.0
 * @create 2021/4/12 15:25
 * @description @Document用来声明Java对象与ElasticSearch索引的关系
 * indexName 索引名称
 * type      索引类型
 * shards    主分区数量 默认5
 * replicas  副本分区数量 默认1
 * createIndex 索引不存在时 是否自动创建索引 默认true
 * @Id 表示索引的主键
 * @Field 用来描述字段的ES数据类型 是否分词等配置 等于Mapping描述
 **/

@Data
@AllArgsConstructor
@Document(indexName = "blog_index", type = "blog_type", shards = 1, replicas = 0)
public class Blog {

    @Id
    private String id;

    /**
     * 主名称
     */
    @Field(type = FieldType.Keyword)
    private String masterName;

    /**
     * 文章数量
     */
    private Integer articleNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 描述
     */
    private String description;
}