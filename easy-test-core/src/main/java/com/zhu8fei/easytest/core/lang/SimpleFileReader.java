package com.zhu8fei.easytest.core.lang;

import com.zhu8fei.easytest.core.exception.EasyTestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhu8fei on 2017/5/7.
 */
public class SimpleFileReader {
    private static Logger logger = LoggerFactory.getLogger(SimpleFileReader.class);

    public static String readAnFileContext(String path) throws EasyTestException {
        logger.debug("read file path : {}", path);
        File file = new File(path);
        if (!file.isFile()) {
            throw new EasyTestException("文件不存在" + path);
        }
        StringBuilder sb = new StringBuilder();
        try {
            List<String> contentLines = Files.lines(Paths.get(file.toURI()))
                    .collect(Collectors.toList());
            logger.debug("read file in {} lines", contentLines.size());
            for (String str : contentLines) {
                sb.append(str);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new EasyTestException(e.getMessage(), e);
        }
        return sb.toString();
    }

}
