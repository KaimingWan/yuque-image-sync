package com.kaimingwan.core.openapi.oss;

/**
 * @author wanshao create time is  2022/11/6
 **/
public interface AliyunOssApiBase {

  /**
   * @param originImageUrl
   * @param imgBytes
   * @param objHashName
   * @return oss image url
   */
  String uploadImage(String originImageUrl, byte[] imgBytes, String objHashName);

}
