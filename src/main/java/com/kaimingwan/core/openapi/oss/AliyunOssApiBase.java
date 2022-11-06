package com.kaimingwan.core.openapi.oss;

/**
 * @author wanshao create time is  2022/11/6
 **/
public interface AliyunOssApiBase {

  void uploadImage(String originImageUrl, byte[] imgBytes, String objHashName);

}
