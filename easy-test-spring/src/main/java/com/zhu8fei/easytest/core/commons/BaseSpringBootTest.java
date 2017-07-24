package com.zhu8fei.easytest.core.commons;

import com.zhu8fei.easytest.core.commons.utils.MarkTestTarget;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/**
 * 需要spring依赖的测试
 * <p>
 * 注意. 此方法无事务控制.默认会提交到数据库.
 * <p>
 * Created by zhu8fei on 2017/5/7.
 */
@AutoConfigureMockMvc
@MarkTestTarget(MarkTestTarget.class)
public class BaseSpringBootTest extends BaseSpringTest{
    @Autowired
    protected MockMvc mvc;

    @Before
    public void setup() {

    }

    @After
    public void destroy() {

    }
}
