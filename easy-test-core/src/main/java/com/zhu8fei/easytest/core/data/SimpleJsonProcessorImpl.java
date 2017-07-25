package com.zhu8fei.easytest.core.data;

import com.alibaba.fastjson.JSON;
import com.zhu8fei.easytest.core.bean.DataSetBean;

import javax.annotation.Resource;

/**
 * 注解上直接json处理实现
 * Created by zhu8fei on 2017/5/6.
 */
public class SimpleJsonProcessorImpl extends SimpleAbstractProcessor implements DataProcessor {
    @Resource(name = "simpleMybatisMapper")
    public void setDataBaseMapper(DataBaseMapper dataBaseMapper) {
        super.setDataBaseMapper(dataBaseMapper);
    }

    protected DataSetBean getDataSetPrepareBean(String context) {
        return JSON.parseObject(context, DataSetBean.class);
    }

    protected DataSetBean getDataSetCompareBean(String context) {
        return JSON.parseObject(context, DataSetBean.class);
    }

}
