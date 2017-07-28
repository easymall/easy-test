package com.zhu8fei.easytest.spring.mybatis.mapper;

import com.zhu8fei.easytest.core.bean.SimpleTable;
import com.zhu8fei.easytest.core.data.DataBaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * mybatis数据库处理类
 * Created by zhu8fei on 2017/5/6.
 */
@Repository
public interface SimpleMybatisMapper extends DataBaseMapper {
    @InsertProvider(type = SimpleMybatisSqlProvider.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    void insert(SimpleTable simpleTable);

    @SelectProvider(type = SimpleMybatisSqlProvider.class, method = "select")
    List<Map<String, String>> select(SimpleTable simpleTable);

}
