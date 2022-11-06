package com.kaimingwan.core.service.impl;

import com.kaimingwan.core.service.ImageBedService;
import com.kaimingwan.core.service.model.ImgWrapper;
import com.kaimingwan.core.util.ImageHashUtil;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author wanshao create time is  2022/11/6
 **/
public class ImageBedServiceImplTest extends TestCase {

  private static ImageBedService imageBedService;

  static {
    imageBedService = new ImageBedServiceImpl();
  }

  @Test
  public void testIsImageExists() {
    System.out.println(imageBedService.isImageExists(
        "https://www.baidu.com/img/xxxPCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png"));
  }

  public void testDownloadImage() {
    ImgWrapper imageStr = imageBedService.downloadImage(
        "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png");
    System.out.println("Image hash is " + ImageHashUtil.getImageHash(imageStr.getFileName()));
  }
}