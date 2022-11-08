package com.kaimingwan.core;

import com.kaimingwan.core.service.SyncService;
import com.kaimingwan.core.service.impl.ConfigServiceImpl;
import com.kaimingwan.core.service.impl.SyncServiceImpl;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * @author wanshao create time is  2022/10/30
 **/
@Slf4j
public class MainSync {

  public static void main(String[] args) {
    log.info("Main sync started.....");
    try {
      ConfigServiceImpl configServiceImpl = new ConfigServiceImpl();
      Properties confProps = configServiceImpl.getProperties();

      SyncService syncService = new SyncServiceImpl(confProps);
      syncService.sync();
    } catch (Exception e) {
      String errMsg = "Core sync process failed with exception. Root cause is "
          + ExceptionUtils.getRootCauseMessage(e);
      log.error(errMsg, e);
      System.exit(1);
    }

  }

}
