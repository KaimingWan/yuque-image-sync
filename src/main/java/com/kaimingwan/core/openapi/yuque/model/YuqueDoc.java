package com.kaimingwan.core.openapi.yuque.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author wanshao create time is  2022/10/31
 * @ref https://www.yuque.com/yuque/developer/docserializer
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class YuqueDoc {

  private String id;

  private String slug;

  private String title;

  @JsonProperty("public")
  private Boolean publicDoc;


  private Boolean status;


  @JsonProperty("created_at")
  private String createTime;

  @JsonProperty("updated_at")
  private String updated_at;

}
