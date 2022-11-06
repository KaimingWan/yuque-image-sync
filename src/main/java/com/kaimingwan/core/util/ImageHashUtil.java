package com.kaimingwan.core.util;

/**
 * @author wanshao create time is  2022/11/6
 **/
public class ImageHashUtil {

  public static String getImageHash(String imageStr) {
    return String.valueOf(fnv1a_64(imageStr));
  }

  private static long fnv1a_64(String chars) {
    long hash = 0xcbf29ce484222325L;
    for (int i = 0; i < chars.length(); ++i) {
      char c = chars.charAt(i);
      hash ^= c;
      hash *= 0x100000001b3L;
    }
    return hash;
  }

}
