package com.kaimingwan.core.service.model;

import lombok.Data;

/**
 * @author wanshao create time is  2022/11/6
 **/
@Data
public class ImgWrapper {

  private byte[] imgBytes;
  private String fileName;

  public ImgWrapper(byte[] imgBytes, String fileName) {
    this.imgBytes = imgBytes;
    this.fileName = fileName;
  }
}
