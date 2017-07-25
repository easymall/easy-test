package com.zhu8fei.easytest.spring;

import com.zhu8fei.easytest.core.runner.EasySuite;
import com.zhu8fei.easytest.core.utils.FindNotMakeTest;
import com.zhu8fei.easytest.core.utils.MarkTestTarget;
import com.zhu8fei.easytest.spring.method.MethodTest;
import com.zhu8fei.easytest.spring.method.PrepareAndExpectSqlTest;
import com.zhu8fei.easytest.spring.method.SystemTest;
import org.junit.runner.RunWith;


@RunWith(EasySuite.class)
@EasySuite.SuiteClasses({
        FindNotMakeTest.class, PrepareAndExpectSqlTest.class,
        MethodTest.class, SystemTest.class
})
@MarkTestTarget({MarkTestTarget.class})
public class EasyTestAll {
    static {
        System.setProperty("base.test.package","com.zhu8fei");
    }
}
