package com.zhu8fei.easytest.core.annotation;

import com.zhu8fei.easytest.core.BaseJunitTest;
import com.zhu8fei.easytest.core.exception.EasyTestException;
import com.zhu8fei.easytest.core.utils.MarkTestTarget;
import com.zhu8fei.easytest.core.method.AnnotationTest;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Method;

/**
 * Created by zhu8fei on 2017/5/7.
 */
@MarkTestTarget(AnnotationTest.class)
public class DataSetAnnotationUtilsJunitTest extends BaseJunitTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @DataSet(path = "/", value = "SimpleFileReader", run = false)
    @Test
    public void dataSetFileName() throws Exception {
        Method method = getClass().getMethod("dataSetFileName");
        String fileName = DataSetAnnotationUtils.dataSetFileName(method);
        logger.info(fileName);
    }

    @DataSet(value = "SimpleFileReader", run = false)
    @Test
    public void dataSetFileNameToDot() throws Exception {
        Method method = getClass().getMethod("dataSetFileNameToDot");
        String fileName = DataSetAnnotationUtils.dataSetFileName(method);
        logger.info(fileName);
        Assert.assertTrue(fileName.contains("com/zhu8fei/easytest/core/commons/annotation/DataSetAnnotationUtilsJunitTest.dataSetFileNameToDot.json"));
    }

    @DataSet(file = "SimpleFileReader", run = false)
    @Test
    public void fileNotExist() throws Exception {
        Method method = getClass().getMethod("fileNotExist");

        exception.expect(EasyTestException.class);
        exception.expectMessage("文件不存在");
        DataSetAnnotationUtils.dataContext(method);
    }


    @DataSet(type = "WTF", run = false)
    @Test
    public void implNotExist() throws Exception {
        Method method = getClass().getMethod("implNotExist");
        exception.expect(EasyTestException.class);
        exception.expectMessage("实现类型");
        DataSetAnnotationUtils.getImplName(method);
    }


    @Test
    public void isRunNotExist() throws Exception {
        Method method = getClass().getMethod("isRunNotExist");
        DataSetAnnotationUtils.isRun(method);
    }

    @Test
    public void instance() {
        // WTF 不创建当前对象.  则这个类就没有被覆盖.
        new DataSetAnnotationUtils();
    }

}