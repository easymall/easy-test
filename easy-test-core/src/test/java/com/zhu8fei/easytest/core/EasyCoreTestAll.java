package com.zhu8fei.easytest.core;

import com.zhu8fei.easytest.core.method.AnnotationTest;
import com.zhu8fei.easytest.core.method.MethodTest;
import com.zhu8fei.easytest.core.method.SystemTest;
import com.zhu8fei.easytest.core.runner.EasySuite;
import com.zhu8fei.easytest.core.utils.FindNotMakeTest;
import com.zhu8fei.easytest.core.utils.MarkTestTarget;
import org.junit.runner.RunWith;


@RunWith(EasySuite.class)
@EasySuite.SuiteClasses({
        FindNotMakeTest.class, AnnotationTest.class,
        MethodTest.class, SystemTest.class
})
@MarkTestTarget({MarkTestTarget.class})
public class EasyCoreTestAll {
    static {
        // 默认需要检查的包(FindNotMakeTest 需要这个配置)
        System.setProperty("easy.test.base.package", "com.zhu8fei");
        System.setProperty("easy.test.base.db.url", "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&aut");
        System.setProperty("easy.test.base.db.username", "root");
        System.setProperty("easy.test.base.db.password", "");
        System.setProperty("easy.test.base.db.driver", "com.mysql.jdbc.Driver");
    }
}
