package com.zhu8fei.easytest.spring.listener;

import com.zhu8fei.easytest.core.annotation.DataSet;
import com.zhu8fei.easytest.core.annotation.DataSetAnnotationUtils;
import com.zhu8fei.easytest.core.data.DataProcessor;
import com.zhu8fei.easytest.core.exception.EasyTestException;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 使用DataSet注解 预处理数据 , 对比操作结果
 *
 * @see DataSet
 * Created by zhu8fei on 2017/5/5.
 */
@Transactional
public class JunitCaseListener extends AbstractTestExecutionListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Returns {@code 2500}.
     */
    @Override
    public final int getOrder() {
        return 2500;
    }

    @Override
    public void beforeTestClass(TestContext testContext) {
        logger.debug("beforeTestClass");
    }

    @Override
    public void prepareTestInstance(TestContext testContext) {
        MDC.put("Trace", UUID.randomUUID().toString());
    }

    @Override
    public void beforeTestMethod(TestContext testContext) {
        Method method = testContext.getTestMethod();
        if (DataSetAnnotationUtils.isRun(method)) {
            try {
                DataProcessor dataProcessor = testContext.
                        getApplicationContext().getBean(DataSetAnnotationUtils.getImplName(method));
                dataProcessor.insertPrepareData(method);
            } catch (EasyTestException e) {
                logger.error(e.getMessage(), e);
                Assert.fail(e.getMessage());
            }
        }
    }

    @Override
    public void afterTestMethod(TestContext testContext) {
        Method method = testContext.getTestMethod();
        if (DataSetAnnotationUtils.isRun(method)) {
            try {
                DataProcessor dataProcessor = testContext.
                        getApplicationContext().getBean(DataSetAnnotationUtils.getImplName(method));
                boolean success = dataProcessor.compareResult(method);
                if (!success) {
                    logger.info("Data test result : failure");
                    Assert.fail();
                }
            } catch (EasyTestException e) {
                logger.error(e.getMessage(), e);
                Assert.fail(e.getMessage());
            }
        }
        logger.info("Data test result : success");
        MDC.clear();
    }

    @Override
    public void afterTestClass(TestContext testContext) {

    }

}
