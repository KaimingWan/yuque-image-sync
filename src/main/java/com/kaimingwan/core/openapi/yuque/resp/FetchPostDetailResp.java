package com.kaimingwan.core.openapi.yuque.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kaimingwan.core.openapi.yuque.model.YuqueDocDetail;
import lombok.Data;

/**
 * @author wanshao create time is  2022/11/6
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FetchPostDetailResp {

  private YuqueDocDetail data;

  private Ability ability;

}


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Ability {

  private Boolean update;

  private Boolean destroy;

}
