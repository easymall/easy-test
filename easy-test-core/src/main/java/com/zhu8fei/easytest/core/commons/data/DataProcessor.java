package com.zhu8fei.easytest.core.commons.data;

import com.zhu8fei.easytest.core.commons.exception.EasyTestException;

import java.lang.reflect.Method;

/**
 * 提供测试所需的预处理数据及数据结果比对接口
 * 需要额外的数据类型及方式时,可以实现此接口
 *
 * @see SimpleJsonProcessorIpml
 * Created by zhu8fei on 2017/5/7.
 */
public interface DataProcessor {
    /**
     * 通过给定的测试方法获取其注解. 获取测试源数据文件. 并插入数据.
     *
     * @param method 测试方法
     */
    void insertPrepareData(Method method) throws EasyTestException;

    /**
     * 通过给定的测试方法获取其注解. 获取测试结果数据文件. 并进行比对.
     *
     * @param method 测试方法
     * @return 比对结果
     */
    boolean compareResult(Method method) throws EasyTestException;
}
