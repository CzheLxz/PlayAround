package com.demo.web.config;

import com.demo.dao.entity.DatabaseType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/12/10 17:38
 * @description
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    public static final Map<DatabaseType, List<String>> METHOD_TYPE_MAP = new HashMap<>();

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        DatabaseType type = DatabaseContextHolder.getContextHolder();
        logger.info("=======================DataSource=======================" + type);
        return type;
    }

    void setMethodType(DatabaseType type, String content) {
        List<String> list = Arrays.asList(content.split(","));
        METHOD_TYPE_MAP.put(type, list);
    }
}
