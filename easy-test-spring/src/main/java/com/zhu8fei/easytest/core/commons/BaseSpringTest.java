package com.zhu8fei.easytest.core.commons;

import com.zhu8fei.easytest.core.commons.mybatis.config.MybatisConfig;
import com.zhu8fei.easytest.core.commons.spring.listener.JunitCaseListener;
import com.zhu8fei.easytest.core.commons.utils.MarkTestTarget;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * 需要spring依赖的测试
 * <p>
 * 注意. 此方法无事务控制.默认会提交到数据库.
 * <p>
 * Created by zhu8fei on 2017/5/7.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {

        MybatisConfig.class

})
@TestExecutionListeners(listeners = {

        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        JunitCaseListener.class

})
@ActiveProfiles("easy-test")
@MarkTestTarget(MarkTestTarget.class)
public class BaseSpringTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void setup() {

    }

    @After
    public void destroy() {

    }
}
