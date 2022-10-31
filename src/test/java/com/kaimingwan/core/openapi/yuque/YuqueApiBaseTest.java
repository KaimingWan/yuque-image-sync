package com.kaimingwan.core.openapi.yuque;


import com.kaimingwan.core.openapi.yuque.model.YuqueDoc;
import com.kaimingwan.core.service.ConfigService;
import java.util.List;
import java.util.Properties;
import org.junit.Test;

/**
 * @author wanshao create time is  2022/10/31
 **/
public class YuqueApiBaseTest {

  private static YuqueApi yuqueApi;

  static {
    ConfigService configService = new ConfigService();
    Properties confProps = configService.getProperties();
    yuqueApi = new YuqueApi(confProps);
  }


  @Test
  public void fetchPost() {
    List<YuqueDoc> yuqueDocs = yuqueApi.fetchPost();
    System.out.println("success");
  }
}