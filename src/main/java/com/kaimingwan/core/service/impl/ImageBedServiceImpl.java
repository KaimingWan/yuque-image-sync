package com.kaimingwan.core.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.kaimingwan.core.http.ConsoleHttpClient;
import com.kaimingwan.core.http.model.ResponseData;
import com.kaimingwan.core.service.ImageBedService;
import com.kaimingwan.core.service.model.ImgWrapper;
import com.kaimingwan.core.util.JacksonUtil;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wanshao create time is  2022/11/6
 **/
public class ImageBedServiceImpl implements ImageBedService {

  private final String IMAGE_URL_REGEX = "\\!\\[.*\\]\\((.*)\\)";


  @Override
  public boolean isImageExists(String imageUrl) {

    try {
      ConsoleHttpClient.getWithString(imageUrl, new HashMap<>());
      return true;
    } catch (Exception e) {
      return false;
    }
  }


  @Override
  public ImgWrapper downloadImage(String imageUrl) {
    ResponseData responseData = ConsoleHttpClient.getImageFile(imageUrl, new HashMap<>());

    return new ImgWrapper((byte[]) responseData.getData(), responseData.getFileName());
  }

  @Override
  public String parseMarkdownImageUrl(String mdImgTag) {
    String regex = "\\((.*)\\)";
    Pattern patten = Pattern.compile(regex);
    Matcher matcher = patten.matcher(mdImgTag);

    List<String> matchStrs = new ArrayList<>();

    while (matcher.find()) {
      matchStrs.add(matcher.group());
    }

    if (CollectionUtil.isNotEmpty(matchStrs) && matchStrs.size() > 1) {
      throw new UnsupportedOperationException(
          "Find more than one url. They are \n" + JacksonUtil.toJson(matchStrs));

    }

    String matchedStr = matchStrs.get(0);
    //remove ( )
    return matchedStr.substring(1, matchedStr.length() - 1);
  }


  @Override
  public List<String> parsePostMdImageTag(String mdBody) {
    if (mdBody == null) {
      throw new UnsupportedOperationException("Can't parse null content");
    }
    Pattern patten = Pattern.compile(IMAGE_URL_REGEX);
    Matcher matcher = patten.matcher(mdBody);

    List<String> matchStrs = new ArrayList<>();

    while (matcher.find()) {
      matchStrs.add(matcher.group());
    }
    return matchStrs;
  }

}
