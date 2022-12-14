package com.kaimingwan.core.openapi.yuque;


import cn.hutool.core.collection.CollectionUtil;
import com.kaimingwan.core.openapi.yuque.model.YuqueDoc;
import com.kaimingwan.core.openapi.yuque.model.YuqueDocDetail;
import com.kaimingwan.core.openapi.yuque.resp.FetchPostDetailResp;
import com.kaimingwan.core.openapi.yuque.resp.FetchPostResp;
import com.kaimingwan.core.service.impl.ConfigServiceImpl;
import java.util.List;
import java.util.Properties;
import org.junit.Test;

/**
 * @author wanshao create time is  2022/10/31
 **/
public class YuqueApiBaseTest {

  private static YuqueApi yuqueApi;

  static {
    ConfigServiceImpl configServiceImpl = new ConfigServiceImpl();
    Properties confProps = configServiceImpl.getProperties();
    yuqueApi = new YuqueApi(confProps);
  }


  @Test
  public void fetchPost() {
    FetchPostResp fetchPostResp = yuqueApi.fetchPost();
    List<YuqueDoc> yuqueDocs = fetchPostResp.getData();
    if (CollectionUtil.isNotEmpty(yuqueDocs)) {
      yuqueDocs.forEach(x->System.out.println(x.getSlug()));
    }
  }

  @Test
  public void testFetchPostDetail() {
    FetchPostDetailResp resp = yuqueApi.fetchPostDetail("ehd9u1");
    YuqueDocDetail detail = resp.getData();
    System.out.println(detail);
  }
}