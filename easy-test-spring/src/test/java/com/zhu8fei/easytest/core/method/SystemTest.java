package com.zhu8fei.easytest.core.method;

import com.zhu8fei.easytest.core.EasyTestAll;
import com.zhu8fei.easytest.core.commons.utils.MarkTestTarget;
import com.zhu8fei.easytest.core.system.MavenPropertyTest;
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
