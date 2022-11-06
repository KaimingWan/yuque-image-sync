package com.kaimingwan.core.openapi.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.kaimingwan.core.constant.PropertiesKey;
import java.io.ByteArrayInputStream;
import java.util.Properties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * @author wanshao create time is  2022/11/6
 **/
@Slf4j
public class AliyunOssApi implements AliyunOssApiBase {


  private final String bucketName;


  private final OSS ossClient;

  private final String endPoint;


  public AliyunOssApi(Properties appConf) {
    this.bucketName = appConf.getProperty(PropertiesKey.ALIYUN_OSS_BUCKET);
    endPoint = appConf.getProperty(PropertiesKey.ALIYUN_OSS_ENDPOINT);
    String ak = appConf.getProperty(PropertiesKey.ALIYUN_OSS_AK);
    String sk = appConf.getProperty(PropertiesKey.ALIYUN_OSS_SK);
    this.ossClient = new OSSClientBuilder().build(endPoint, ak, sk);


  }

  @SneakyThrows
  @Override
  public String uploadImage(String fileName, byte[] imgBytes, String objHashName) {

    try {
      String extName = FilenameUtils.getExtension(fileName);

      String fullHashedName = objHashName + "." + extName;
      ossClient.putObject(bucketName, fullHashedName, new ByteArrayInputStream(imgBytes));

      String namespace = endPoint.replaceAll("https://", "");
      String ossImgUrl = "https://" + bucketName + "." + namespace + "/" + fullHashedName;
      return ossImgUrl;


    } catch (Exception e) {
      String errMsg = "Invoke oss open api failed with exception. Root cause is "
          + ExceptionUtils.getRootCauseMessage(e);
      log.error(errMsg, e);
      throw e;
    }
  }
}
