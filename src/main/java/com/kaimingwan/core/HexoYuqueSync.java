package com.kaimingwan.core;

import com.kaimingwan.core.service.impl.ConfigServiceImpl;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wanshao create time is  2022/10/30
 **/
@Slf4j
public class HexoYuqueSync {

  public static void main(String[] args) {
    log.info("HexoYuqueSync started.....");
    ConfigServiceImpl configServiceImpl = new ConfigServiceImpl();
    Properties confProps = configServiceImpl.getProperties();

  }

}
