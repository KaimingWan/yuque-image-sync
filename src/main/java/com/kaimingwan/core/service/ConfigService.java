package com.kaimingwan.core.service;

import com.kaimingwan.core.util.PropertiesUtil;
import java.io.File;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

/**
 * Load core hexo-yuque service
 *
 * @author wanshao create time is  2022/10/30
 **/
@Slf4j
public class ConfigService {

  public Properties getProperties() {
    String classPath = this.getClass().getClassLoader().getResource("").getPath();
    File classFile = new File(classPath);
    String confPath = classFile.getParent() + "/../conf/conf.properties";

    File confFile = new File(confPath);
    if (!confFile.exists()) {
      log.warn("Not exists conf file in " + confPath + ", will load file from classpath");
      String confClassPath = this.getClass().getClassLoader().getResource("conf/conf.properties").getPath();
      confFile = new File(confClassPath);
    }

    log.info("conf.properties is located in " + confFile.getAbsoluteFile());

    return PropertiesUtil.getProperties(confFile.getAbsolutePath());

  }
}
