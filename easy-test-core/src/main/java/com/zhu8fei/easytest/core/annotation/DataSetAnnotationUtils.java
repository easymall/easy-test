package com.zhu8fei.easytest.core.annotation;

import com.zhu8fei.easytest.core.data.DataProcessor;
import com.zhu8fei.easytest.core.exception.EasyTestException;
import com.zhu8fei.easytest.core.lang.SimpleFileReader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * DataSet注解工具类
 * Created by zhu8fei on 2017/5/7.
 */
public class DataSetAnnotationUtils {
    private static Logger logger = LoggerFactory.getLogger(DataSetAnnotationUtils.class);
    private static final String DOT = ".";
    private static final String DEFAULT_TYPE = "json";

    /**
     * 是否运行
     *
     * @param method 测试方法
     * @return 是否运行
     */
    public static boolean isRun(Method method) {
        DataSet dataSet = method.getAnnotation(DataSet.class);
        return dataSet != null && dataSet.run();
    }

    /**
     * 是否运行
     *
     * @param method 测试方法
     * @return 实现类名
     * @throws EasyTestException
     */
    public static Class<? extends DataProcessor> getImplName(Method method) throws EasyTestException {
        DataSet dataSet = method.getAnnotation(DataSet.class);
        String type = dataSet.type();
        if (StringUtils.isEmpty(type)) {
            type = DEFAULT_TYPE;
        }
        Class<? extends DataProcessor> clz = DataSetProcessorEnum.getProcessorType(type);
        if (clz == null) {
            logger.error("实现类型(DataSet.type)错误 method : {}", method.getName());
            throw new EasyTestException("实现类型(DataSet.type)错误");
        }
        return clz;
    }

    /**
     * 是否打印日志
     *
     * @param method 测试方法
     * @return 是否打印日志
     */
    public static boolean isLog(Method method) {
        DataSet dataSet = method.getAnnotation(DataSet.class);
        return dataSet.log();
    }

    /**
     * 直接返回数据内容
     *
     * @param method 测试方法
     * @return 注解内容
     * @throws EasyTestException
     */
    public static String dataContext(Method method) throws EasyTestException {
        DataSet dataSet = method.getAnnotation(DataSet.class);
        String context = dataSet.value();
        if (StringUtils.isNotEmpty(context)) {
            return context;
        }
        try {
            context = SimpleFileReader.readAnFileContext(DataSetAnnotationUtils.dataSetFileName(method));
        } catch (EasyTestException e) {
            logger.error(e.getMessage(), e);
            throw new EasyTestException(e.getMessage(), e);
        }

        return context;

    }

    /**
     * 返回预处理数据文件路径
     *
     * @param method 测试方法
     * @return 文件内容
     * @throws EasyTestException
     */
    public static String dataSetFileName(Method method) throws EasyTestException {
        DataSet dataSet = method.getAnnotation(DataSet.class);
        String path = dataSet.path();
        String file = dataSet.file();
        String type = dataSet.type();
        if (StringUtils.isEmpty(file)) {
            file = method.getDeclaringClass().getSimpleName();
            file += DOT + method.getName();
        }
        return getPath(method, path, file, type);
    }


    /**
     * 返回路径
     *
     * @param method 测试方法
     * @param path   文件地址(不包含文件名)
     * @param file   文件名
     * @param type   文件类型
     * @return 返回文件内容
     * @throws EasyTestException
     */
    private static String getPath(Method method, String path, String file, String type) throws EasyTestException {
        if (StringUtils.isEmpty(path)) {
            path = DOT;
        }
        if (StringUtils.isEmpty(type)) {
            type = DEFAULT_TYPE;
        }
        if (DOT.equals(path)) {
            Class clz = method.getDeclaringClass();
            path = clz.getResource(".").getFile();
        } else {
            path = DataSetAnnotationUtils.class.getResource("/").getFile() + path;
        }
        return path + file + DOT + type;
    }
}
