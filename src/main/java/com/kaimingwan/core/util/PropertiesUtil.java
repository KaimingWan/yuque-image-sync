package com.kaimingwan.core.util;

import java.io.FileInputStream;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * @author wanshao create time is  2022/10/30
 **/
@Slf4j
public class PropertiesUtil {

  public static Properties getProperties(String confPath) {
    try {
      Properties prop = new Properties();
      prop.load(new FileInputStream(confPath));
      return prop;
    } catch (Exception e) {
      String msg = "load " + confPath + " error.msg:" + ExceptionUtils.getRootCauseMessage(e);
      log.error(msg, e);
      throw new RuntimeException(msg, e);
    }
  }

}
