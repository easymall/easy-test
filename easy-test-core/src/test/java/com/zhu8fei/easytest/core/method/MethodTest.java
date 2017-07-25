package com.zhu8fei.easytest.core.method;

import com.zhu8fei.easytest.core.EasyCoreTestAll;
import com.zhu8fei.easytest.core.lang.SimpleFileReaderTest;
import com.zhu8fei.easytest.core.utils.MarkTestTarget;
import com.zhu8fei.easytest.core.jdk.CollectionDiffTest;
import com.zhu8fei.easytest.core.jdk.ExtendsTransactionalSpringTest;
import com.zhu8fei.easytest.core.jdk.MethodAndClassTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by zhu8fei on 2017/5/9.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExtendsTransactionalSpringTest.class, MethodAndClassTest.class,
        CollectionDiffTest.class, SimpleFileReaderTest.class,

})
@MarkTestTarget(EasyCoreTestAll.class)
public class MethodTest {
}
