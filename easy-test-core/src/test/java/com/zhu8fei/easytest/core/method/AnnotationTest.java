package com.zhu8fei.easytest.core.method;

import com.zhu8fei.easytest.core.EasyCoreTestAll;
import com.zhu8fei.easytest.core.annotation.DataSetAnnotationUtilsJunitTest;
import com.zhu8fei.easytest.core.utils.MarkTestTarget;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by zhu8fei on 2017/5/7.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({DataSetAnnotationUtilsJunitTest.class})
@MarkTestTarget(EasyCoreTestAll.class)
public class AnnotationTest {
}
