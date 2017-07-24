package com.zhu8fei.easytest.core.method;

import com.zhu8fei.easytest.core.EasyTestAll;
import com.zhu8fei.easytest.core.commons.mybatis.SimpleAbstractProcessorTest;
import com.zhu8fei.easytest.core.commons.mybatis.SimpleJsonProcessorImplTest;
import com.zhu8fei.easytest.core.commons.mybatis.SimpleMybatisSqlProviderSpringTest;
import com.zhu8fei.easytest.core.commons.utils.MarkTestTarget;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by zhu8fei on 2017/5/9.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SimpleMybatisSqlProviderSpringTest.class, SimpleJsonProcessorImplTest.class,
        SimpleAbstractProcessorTest.class,
})
@MarkTestTarget(EasyTestAll.class)
public class PrepareAndExpectSqlTest {
}
