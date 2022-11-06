package com.kaimingwan.core.openapi.oss;

import com.kaimingwan.core.openapi.yuque.YuqueApi;
import com.kaimingwan.core.service.ImageBedService;
import com.kaimingwan.core.service.impl.ConfigServiceImpl;
import com.kaimingwan.core.service.impl.ImageBedServiceImpl;
import com.kaimingwan.core.service.model.ImgWrapper;
import com.kaimingwan.core.util.ImageHashUtil;
import java.util.Properties;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author wanshao create time is  2022/11/6
 **/
public class AliyunOssApiTest extends TestCase {

  private static AliyunOssApi aliyunOssApi;

  private static YuqueApi yuqueApi;

  private static ImageBedService imageBedService;


  static {
    ConfigServiceImpl configServiceImpl = new ConfigServiceImpl();
    Properties confProps = configServiceImpl.getProperties();
    aliyunOssApi = new AliyunOssApi(confProps);
    yuqueApi = new YuqueApi(confProps);
    imageBedService = new ImageBedServiceImpl();
  }

  @Test
  public void testUploadImage() {

    String imgUrl = "https://cdn.nlark.com/yuque/0/2022/png/96442/1666937973926-8d8eb9ca-7c97-4edd-a689-546d24b0b970.png#clientId=uf80e6242-6d9a-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=962&id=u31c328df&margin=%5Bobject%20Object%5D&name=image.png&originHeight=1924&originWidth=3826&originalType=binary&ratio=1&rotation=0&showTitle=false&size=470458&status=done&style=none&taskId=u8c468ae5-0a5c-40a2-bf64-a725b13a2fb&title=&width=1913";
    //test one is enough

    ImgWrapper imageStrWrapper = imageBedService.downloadImage(imgUrl);
    aliyunOssApi.uploadImage(imageStrWrapper.getFileName(), imageStrWrapper.getImgBytes(),
        ImageHashUtil.getImageHash(imageStrWrapper.getFileName()));

  }
}