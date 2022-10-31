package com.kaimingwan.core.service;

import com.kaimingwan.core.constant.PropertiesKey;
import java.util.Properties;

/**
 * @author wanshao create time is  2022/10/30
 **/
public class YuqueService {

  private final String YUQUE_TOKEN;

  public YuqueService(Properties properties) {
    this.YUQUE_TOKEN = properties.getProperty(PropertiesKey.YUQUE_TOKEN);
  }

  /**
   * Download yuque posts to local hexo home
   */
  public void downloadPosts() {

  }

}
