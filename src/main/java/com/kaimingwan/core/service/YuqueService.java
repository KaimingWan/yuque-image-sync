package com.kaimingwan.core.service;

import com.kaimingwan.core.openapi.yuque.model.YuqueDocDetail;
import java.util.List;

/**
 * @author wanshao create time is  2022/11/6
 **/
public interface YuqueService {

  /**
   * Image tag is like ![]()
   * @param yuqueDocDetail
   * @return
   */
  List<String> parsePostMdImageTag(YuqueDocDetail yuqueDocDetail);


}
