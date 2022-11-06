package com.kaimingwan.core.openapi.yuque;

import com.kaimingwan.core.openapi.yuque.resp.FetchPostDetailResp;
import com.kaimingwan.core.openapi.yuque.resp.FetchPostResp;

/**
 * @author wanshao create time is  2022/10/31
 **/
public interface YuqueApiBase {


  FetchPostResp fetchPost();

  FetchPostDetailResp fetchPostDetail(String docSlug);

}
