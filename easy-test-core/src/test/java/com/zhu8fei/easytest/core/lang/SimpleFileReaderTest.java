package com.zhu8fei.easytest.core.lang;

import com.zhu8fei.easytest.core.BaseJunitTest;
import com.zhu8fei.easytest.core.exception.EasyTestException;
import com.zhu8fei.easytest.core.utils.MarkTestTarget;
import com.zhu8fei.easytest.core.method.MethodTest;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

/**
 * Created by zhu8fei on 2017/7/21.
 */
@MarkTestTarget(MethodTest.class)
public class SimpleFileReaderTest extends BaseJunitTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void readAnFileContext() throws EasyTestException, IOException {
        SimpleFileReader simpleFileReader = new SimpleFileReader();
        logger.info("SimpleFileReader.toString {}", simpleFileReader.toString());

        String path = getClass().getResource("/").getFile();
        logger.info(path);
        String context = SimpleFileReader.readAnFileContext(path + "SimpleFileReader.json");
        logger.info(context);
        Assert.assertNotNull(context);
        String empty = SimpleFileReader.readAnFileContext(path + "emptyFile");
        logger.info(empty);
        Assert.assertEquals(empty, "");

        // 断言会抛出这个异常. 使用这种方式的exception断言时,后续代码并不执行
        exception.expect(EasyTestException.class);
        // 断言异常内容.但是不需要写全,只要包含即可.写{} 估计是必错了.
        exception.expectMessage("文件不存在");
        SimpleFileReader.readAnFileContext("/xx");

        // Do nothing, and can not do!

    }

}