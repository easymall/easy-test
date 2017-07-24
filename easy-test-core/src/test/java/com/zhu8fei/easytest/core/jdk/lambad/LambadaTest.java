package com.zhu8fei.easytest.core.jdk.lambad;

import com.zhu8fei.easytest.core.commons.BaseJunitTest;
import com.zhu8fei.easytest.core.commons.utils.MarkTestTarget;
import org.junit.Test;

import java.util.Arrays;


/**
 * Created by zhu8fei on 2017/5/11.
 */
@MarkTestTarget
public class LambadaTest extends BaseJunitTest {
    @Test

    public void lambada() {
        String[] a = {"a", "x", "c", "d"};
        Arrays.sort(a, String::compareTo);

        for (String str : a) {
            logger.info(str);
        }
    }


}
