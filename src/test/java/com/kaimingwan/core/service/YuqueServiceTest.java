package com.kaimingwan.core.service;

import cn.hutool.core.collection.CollectionUtil;
import com.kaimingwan.core.openapi.yuque.YuqueApi;
import com.kaimingwan.core.openapi.yuque.model.YuqueDocDetail;
import com.kaimingwan.core.openapi.yuque.resp.FetchPostDetailResp;
import com.kaimingwan.core.service.impl.ConfigServiceImpl;
import com.kaimingwan.core.service.impl.YuqueServiceImpl;
import java.util.List;
import java.util.Properties;
import junit.framework.TestCase;

/**
 * @author wanshao create time is  2022/11/6
 **/
public class YuqueServiceTest extends TestCase {

  private static YuqueServiceImpl yuqueServiceImpl;

  private static YuqueApi yuqueApi;


  static {
    ConfigServiceImpl configServiceImpl = new ConfigServiceImpl();
    Properties confProps = configServiceImpl.getProperties();
    yuqueServiceImpl = new YuqueServiceImpl(confProps);
    yuqueApi = new YuqueApi(confProps);
  }

  public void testParsePostImageUrl() {
    FetchPostDetailResp resp = yuqueApi.fetchPostDetail("ehd9u1");
    YuqueDocDetail detail = resp.getData();
    List<String> imageUrls = yuqueServiceImpl.parsePostMdImageTag(detail);
    if (CollectionUtil.isNotEmpty(imageUrls)) {
      imageUrls.forEach(x -> System.out.println(x));
    }
  }


}