package com.kaimingwan.core.openapi.oss;

import com.aliyun.oss.*;
import com.kaimingwan.core.constant.PropertiesKey;
import java.io.ByteArrayInputStream;
import java.util.Properties;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;

/**
 * @author wanshao create time is  2022/11/6
 **/
public class AliyunOssApi implements AliyunOssApiBase {


  private final String BUCKET_NAME;


  private final Properties appConf;

  private final OSS ossClient;


  public AliyunOssApi(Properties appConf) {
    this.appConf = appConf;
    this.BUCKET_NAME = appConf.getProperty(PropertiesKey.ALIYUN_OSS_BUCKET);
    String endPoint = appConf.getProperty(PropertiesKey.ALIYUN_OSS_ENDPOINT);
    String ak = appConf.getProperty(PropertiesKey.ALIYUN_OSS_AK);
    String sk = appConf.getProperty(PropertiesKey.ALIYUN_OSS_SK);
    this.ossClient = new OSSClientBuilder().build(endPoint, ak, sk);


  }

  @SneakyThrows
  @Override
  public void uploadImage(String fileName, byte[] imgBytes, String objHashName) {

    try {
      String extName = FilenameUtils.getExtension(fileName);

      ossClient.putObject(BUCKET_NAME, objHashName + "." + extName,
          new ByteArrayInputStream(imgBytes));

    } catch (OSSException oe) {
      System.out.println("Caught an OSSException, which means your request made it to OSS, "
          + "but was rejected with an error response for some reason.");
      System.out.println("Error Message:" + oe.getErrorMessage());
      System.out.println("Error Code:" + oe.getErrorCode());
      System.out.println("Request ID:" + oe.getRequestId());
      System.out.println("Host ID:" + oe.getHostId());
    } catch (ClientException ce) {
      System.out.println("Caught an ClientException, which means the client encountered "
          + "a serious internal problem while trying to communicate with OSS, "
          + "such as not being able to access the network.");
      System.out.println("Error Message:" + ce.getMessage());
    } finally {
      if (ossClient != null) {
        ossClient.shutdown();
      }
    }
  }
}
