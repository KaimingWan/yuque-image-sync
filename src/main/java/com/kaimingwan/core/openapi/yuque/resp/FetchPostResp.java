package com.kaimingwan.core.openapi.yuque.resp;

import com.kaimingwan.core.openapi.yuque.model.YuqueDoc;
import java.util.List;
import lombok.Data;

/**
 * @author wanshao create time is  2022/10/31
 **/
@Data
public class FetchPostResp {

  private List<YuqueDoc> data;


}
