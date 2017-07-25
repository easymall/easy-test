package com.zhu8fei.easytest.core.lang.io;


import org.junit.Assert;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhu8fei on 2017/5/10.
 */
public class FileUtils {
    /**
     * 创建空文件
     *
     * @param file
     */
    public static boolean createFile(File file) throws IOException {
        Assert.assertNotNull("File not be null", file);
        if (file.isDirectory()) {
            throw new IllegalArgumentException("File : " + file.getAbsolutePath() + " is a directory !");
        }
        if (file.exists()) {
            return true;
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        return true;
    }

    /**
     * 创建空文件
     *
     * @param file
     */
    public static boolean createFile(String file) throws IOException {
        Assert.assertNotNull("File not be null", file);
        return createFile(new File(file));
    }

}
