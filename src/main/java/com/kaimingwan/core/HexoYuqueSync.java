package com.kaimingwan.core;

import com.kaimingwan.core.constant.PropertiesKey;
import com.kaimingwan.core.service.ConfigService;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wanshao create time is  2022/10/30
 **/
@Slf4j
public class HexoYuqueSync {

  public static void main(String[] args) {
    log.info("HexoYuqueSync started.....");
    ConfigService configService = new ConfigService();
    Properties confProps = configService.getProperties();
    log.info("Yuque token is "+confProps.getProperty(PropertiesKey.YUQUE_TOKEN));
  }

}
