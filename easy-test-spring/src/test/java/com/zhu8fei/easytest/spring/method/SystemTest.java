package com.zhu8fei.easytest.spring.method;

import com.zhu8fei.easytest.core.utils.MarkTestTarget;
import com.zhu8fei.easytest.spring.EasyTestAll;
import com.zhu8fei.easytest.spring.system.MavenPropertyTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by zhu8fei on 2017/5/10.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        MavenPropertyTest.class, MavenPropertyTest.class
})
@MarkTestTarget(EasyTestAll.class)
public class SystemTest {
}
