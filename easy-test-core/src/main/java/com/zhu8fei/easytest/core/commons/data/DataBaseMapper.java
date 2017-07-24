package com.zhu8fei.easytest.core.commons.data;

import com.zhu8fei.easytest.core.commons.bean.SimpleTable;

import java.util.List;
import java.util.Map;

/**
 * 插件条件数据,查询预期数据
 * Created by zhu8fei on 2017/5/13.
 */
public interface DataBaseMapper {
    /**
     * 插入
     *
     * @param simpleTable 插入数据行
     */
    void insert(SimpleTable simpleTable);

    /**
     * 查询预期数据
     *
     * @param simpleTable 条件
     * @return 返回查询结果
     */
    List<Map<String, String>> select(SimpleTable simpleTable);
}
