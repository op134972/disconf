package com.baidu.disconf2.test.service.config.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.disconf2.service.config.dao.ConfigDao;
import com.baidu.disconf2.test.common.BaseTestCase;
import com.baidu.ub.common.utils.FileUtils;

/**
 * 
 * @author liaoqiqi
 * @version 2014-6-17
 */
public class ConfigDaoTestCase extends BaseTestCase {

    protected static final Logger LOG = LoggerFactory
            .getLogger(ConfigDaoTestCase.class);

    @Autowired
    private ConfigDao configDao;

    @Test
    public void test() {

        URL url = ClassLoader.getSystemResource("file2/confA.properties");

        byte[] btyes = readFileContent(url.getPath());

        try {

            String str = new String(btyes, "UTF-8");
            LOG.info(str);

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 读取文件的内容到byte数组中
     * 
     * @param fileName
     * @return
     */
    private byte[] readFileContent(String fileName) {

        InputStream is = null;
        try {

            // 按GBK编码与UTF-8编码分别查找文件
            File f = new File(new String(fileName.getBytes("GBK")));
            f = f.isFile() ? f : new File(
                    new String(fileName.getBytes("UTF-8")));
            if (!f.isFile()) {
                LOG.error(fileName + " 文件不存在!");
                return null;
            }

            int length = (int) f.length();
            byte[] bytes = new byte[length];

            is = new FileInputStream(f);
            is.read(bytes);
            return bytes;

        } catch (Exception e) {

            LOG.error("error when download " + fileName, e);

        } finally {
            FileUtils.closeInputStream(is);
        }
        return null;
    }
}