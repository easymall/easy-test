package com.zhu8fei.easytest.core.method;

import com.zhu8fei.easytest.core.EasyTestAll;
import com.zhu8fei.easytest.core.commons.mybatis.ExpectBeanJunitTest;
import com.zhu8fei.easytest.core.commons.mybatis.MybatisConfigTest;
import com.zhu8fei.easytest.core.commons.mybatis.SimpleMybatisSqlProviderSpringTest;
import com.zhu8fei.easytest.core.commons.utils.MarkTestTarget;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by zhu8fei on 2017/5/9.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExpectBeanJunitTest.class, SimpleMybatisSqlProviderSpringTest.class, MybatisConfigTest.class,
})
@MarkTestTarget(EasyTestAll.class)
public class MethodTest {
}
