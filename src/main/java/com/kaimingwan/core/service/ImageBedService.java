package com.kaimingwan.core.service;

import com.kaimingwan.core.service.model.ImgWrapper;

/**
 * @author wanshao create time is  2022/11/6
 **/
public interface ImageBedService {

  boolean isImageExists(String imageUrl);

  ImgWrapper downloadImage(String imageUrl);

  /**
   * @param mdImgTag like ![]()
   * @return The http image url
   */
  String parseMarkdownImageUrl(String mdImgTag);

}
