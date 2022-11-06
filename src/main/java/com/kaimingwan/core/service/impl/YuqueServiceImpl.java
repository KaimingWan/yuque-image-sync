package com.kaimingwan.core.service.impl;

import com.kaimingwan.core.constant.PropertiesKey;
import com.kaimingwan.core.http.ConsoleHttpClient;
import com.kaimingwan.core.openapi.yuque.model.YuqueDocDetail;
import com.kaimingwan.core.service.YuqueService;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wanshao create time is  2022/10/30
 **/
public class YuqueServiceImpl implements YuqueService {

  private final String YUQUE_TOKEN;

  private final String IMAGE_URL_REGEX = "\\!\\[.*\\]\\((.*)\\)";

  public YuqueServiceImpl(Properties properties) {
    this.YUQUE_TOKEN = properties.getProperty(PropertiesKey.YUQUE_TOKEN);
  }

  @Override
  public List<String> parsePostMdImageTag(YuqueDocDetail yuqueDocDetail) {
    if (yuqueDocDetail == null) {
      throw new UnsupportedOperationException("Can't parse null yuqueDocDetail object");
    }
    Pattern patten = Pattern.compile(IMAGE_URL_REGEX);
    Matcher matcher = patten.matcher(yuqueDocDetail.getBody());

    List<String> matchStrs = new ArrayList<>();

    while (matcher.find()) {
      matchStrs.add(matcher.group());
    }
    return matchStrs;
  }



}
