package com.kaimingwan.core.service;

/**
 * @author wanshao create time is  2022/11/6
 **/
public interface SyncService {

  void sync();

  /**
   * Write out posts whose images are replaced to local file system
   */
  void writeOutProcessedPosts(String content, String fileName);
}
