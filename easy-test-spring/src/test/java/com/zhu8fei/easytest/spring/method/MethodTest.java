package com.zhu8fei.easytest.spring.method;

import com.zhu8fei.easytest.core.utils.MarkTestTarget;
import com.zhu8fei.easytest.spring.EasyTestAll;
import com.zhu8fei.easytest.spring.mybatis.ExpectBeanJunitTest;
import com.zhu8fei.easytest.spring.mybatis.MybatisConfigTest;
import com.zhu8fei.easytest.spring.mybatis.SimpleMybatisSqlProviderSpringTest;
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
