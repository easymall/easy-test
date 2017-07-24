package com.zhu8fei.easytest.core.commons.utils;

import com.zhu8fei.easytest.core.commons.lang.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;


/**
 * @author zhu8fei Wang
 */
@MarkTestTarget(MarkTestTarget.class)
public class FindNotMakeTest {
    private String logPath = null;

    @Before
    public void init() {
        logPath = System.getProperty("findClassLogPath");
        if (StringUtils.isEmpty(logPath)) {
            logger.warn("看到这句话,正常情况是,你手动启动了test case, 请到target/logs/findClass.log 查看结果");
            logPath = System.getProperty("user.dir") + "/target/logs/findClass.log";
        }
    }

    private Logger logger = LoggerFactory.getLogger(FindNotMakeTest.class);
    private static final String CLASS_NAME_PREFIX = "Test";
    private static final String PACKAGE_NAME_PREFIX = "com.zhu8fei";

    @Test
    public void findNotMakeTest() {
        Set<Class<?>> javaClass = FindClass.getClasses(PACKAGE_NAME_PREFIX);
        // WTF windows
        logInfo("============    project : " + System.getProperty("user.dir") + " Without control Test cases:   ==============\n");
        for (Class<?> clazz : javaClass) {
            if (clazz.getName().contains("$")) {
                // 不监视内部类等
                continue;
            }
            if (clazz.getName().indexOf(CLASS_NAME_PREFIX) == clazz.getName().length() - 4) {
                MarkTestTarget mtt = clazz.getAnnotation(MarkTestTarget.class);
                if (mtt == null) {
                    logInfo(clazz.getName());
                    continue;
                }
                Class[] testTypes = mtt.value();
                if (testTypes.length == 0) {
                    logInfo(clazz.getName());
                }
            }
        }
        logInfo("\n============+++++++++++++++++++++++++++++++++++++++++++++++++==============\n\n");
    }

    private void logInfo(String msg) {
        if (logPath == null) {
            logger.info(msg);
        } else {
            try {
                FileUtils.createFile(logPath);
                // WTF windows
                FileWriter fw = new FileWriter(logPath, true);
                fw.write(msg);
                fw.write("\n");
                fw.flush();
                fw.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}