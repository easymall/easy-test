package com.zhu8fei.easytest.spring.mybatis;

import com.alibaba.fastjson.JSON;
import com.zhu8fei.easytest.core.BaseJunitTest;
import com.zhu8fei.easytest.core.bean.DataSetBean;
import com.zhu8fei.easytest.core.utils.MarkTestTarget;
import com.zhu8fei.easytest.spring.method.MethodTest;
import org.junit.Test;

/**
 * Created by zhu8fei on 2017/5/8.
 */
@MarkTestTarget(MethodTest.class)
public class ExpectBeanJunitTest extends BaseJunitTest {
    @Test
    public void jsonFormat() {
        String json = " {" +
                " \"prepare\":[{" +
                " \"tableName\":\"prepare_table\"," +
                " \"columns\":[\"column1\",\"column2\"]," +
                " \"rows\":[[\"value1\",\"value2\"],[\"value1\",\"value2\"]  ]" +
                " }  ], " +
                "   \"expect\":[{" +
                " \"tableName\":\"expect_table\"," +
                " \"columns\":[\"column1\",\"column2\"]," +
                " \"rows\" :[[\"value1\",\"value2\"],[\"value1\",\"value2\"]  ]" +
                "   } ]" +
                "  }";

        DataSetBean bean = JSON.parseObject(json, DataSetBean.class);
        logger.info(bean.toString());
    }

}