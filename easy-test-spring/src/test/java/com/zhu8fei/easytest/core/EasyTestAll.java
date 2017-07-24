package com.zhu8fei.easytest.core;

import com.zhu8fei.easytest.core.commons.utils.FindNotMakeTest;
import com.zhu8fei.easytest.core.commons.utils.MarkTestTarget;
import com.zhu8fei.easytest.core.method.MethodTest;
import com.zhu8fei.easytest.core.method.PrepareAndExpectSqlTest;
import com.zhu8fei.easytest.core.method.SystemTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        FindNotMakeTest.class, PrepareAndExpectSqlTest.class,
        MethodTest.class, SystemTest.class
})
@MarkTestTarget({MarkTestTarget.class})
public class EasyTestAll {

}
