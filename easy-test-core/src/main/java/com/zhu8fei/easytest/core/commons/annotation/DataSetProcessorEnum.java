package com.zhu8fei.easytest.core.commons.annotation;

import com.zhu8fei.easytest.core.commons.data.DataProcessor;
import com.zhu8fei.easytest.core.commons.data.SimpleJsonProcessorIpml;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhu8fei on 2017/5/10.
 */
public class DataSetProcessorEnum {

    private static Map<String, Class<? extends DataProcessor>> dataProcessorMap = new HashMap<>();

    static {
        dataProcessorMap.put("json", SimpleJsonProcessorIpml.class);
    }

    public static void registDataProcessor(String type, Class<? extends DataProcessor> processor) {
        dataProcessorMap.put(type, processor);
    }

    public static Class<? extends DataProcessor> getProcessorType(String type) {
        return dataProcessorMap.get(type);
    }
}
