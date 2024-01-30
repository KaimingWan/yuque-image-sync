package com.kaimingwan.core.openapi.yuque.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kaimingwan.core.openapi.yuque.model.YuqueDoc;
import java.util.List;
import lombok.Data;

/**
 * @author wanshao create time is  2022/10/31
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FetchPostResp {

  private List<YuqueDoc> data;


}
