package com.kaimingwan.core.openapi.yuque;

import com.kaimingwan.core.constant.PropertiesKey;
import com.kaimingwan.core.http.ConsoleHttpClient;
import com.kaimingwan.core.openapi.yuque.resp.FetchPostDetailResp;
import com.kaimingwan.core.openapi.yuque.resp.FetchPostResp;
import com.kaimingwan.core.util.JacksonUtil;
import java.util.*;
import lombok.SneakyThrows;

/**
 * @author wanshao create time is  2022/10/31
 **/
public class YuqueApi implements YuqueApiBase {


  private final String YUQUE_DOMAIN = "https://www.yuque.com/api/v2";


  private final String INVOKER_APP_NAME = "hexo-yuque-java";

  private final Properties appConf;

  private final Map<String, String> headers;

  private final String namespace;


  public YuqueApi(Properties appConf) {
    this.appConf = appConf;
    this.headers = initYuqueHeader();
    this.namespace = appConf.getProperty(PropertiesKey.YUQUE_USER) + "/" + appConf.getProperty(
        PropertiesKey.YUQUE_REPO);
  }

  private Map<String, String> initYuqueHeader() {
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json");
    headers.put("User-Agent", INVOKER_APP_NAME);
    headers.put("X-Auth-Token", appConf.getProperty(PropertiesKey.YUQUE_TOKEN));
    return headers;
  }

  @SneakyThrows
  @Override
  public FetchPostResp fetchPost() {
    String url = YUQUE_DOMAIN + "/repos/" + namespace + "/docs";
    String respJson = ConsoleHttpClient.getWithString(url, headers);
    return (FetchPostResp) JacksonUtil.toObj(respJson, FetchPostResp.class);
  }

  public FetchPostDetailResp fetchPostDetail(String docSlug){
    String url = YUQUE_DOMAIN + "/repos/" + namespace + "/docs/"+docSlug;
    String respJson = ConsoleHttpClient.getWithString(url, headers);
    return (FetchPostDetailResp) JacksonUtil.toObj(respJson, FetchPostDetailResp.class);
  }

}
