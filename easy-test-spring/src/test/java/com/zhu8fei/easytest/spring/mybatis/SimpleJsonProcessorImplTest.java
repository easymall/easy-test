package com.zhu8fei.easytest.spring.mybatis;

import com.zhu8fei.easytest.spring.BaseTransactionalSpringTest;
import com.zhu8fei.easytest.core.annotation.DataSet;
import com.zhu8fei.easytest.core.utils.MarkTestTarget;
import com.zhu8fei.easytest.spring.method.PrepareAndExpectSqlTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by zhu8fei on 2017/5/9.
 */
@MarkTestTarget(PrepareAndExpectSqlTest.class)
public class SimpleJsonProcessorImplTest extends BaseTransactionalSpringTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @DataSet(value = "{" +
            "     \"prepare\": [{" +
            "          \"tableName\": \"u_user\"," +
            "          \"columns\": [ \"name\", \"real_name\"]," +
            "          \"rows\": [" +
            "               [\"name1\", \"real_name1\"]," +
            "               [\"name2\", \"real_name2\"]," +
            "          ]" +
            "     }]," +
            "     \"expect\": [{" +
            "          \"tableName\": \"u_user\"," +
            "          \"columns\": [\"name\", \"real_name\"]," +
            "          \"rows\": [" +
            "               [\"name1\", \"real_name1\"]," +
            "               [\"name2\", \"real_name2\"]," +
            "          ]," +
            "          \"param\": {" +
            "               \"real_name\": \"real_name%\"," +
            "          }" +
            "     }]" +
            "}", log = true)
    @Test
    public void dataSetAnnotationTest() {
        logger.info(" 谁知道呢. ");
    }

    @Test
    @DataSet(type = "", path = "")
    public void dataSetFileTest() {
        logger.info(" 谁知道呢. ");
    }

    @Test
    @DataSet(path = "/", file = "SimpleFileReader")
    public void dataSetTest() {
        logger.info(" 谁知道呢. ");
    }

}