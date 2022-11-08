package com.kaimingwan.core.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.kaimingwan.core.constant.PropertiesKey;
import com.kaimingwan.core.openapi.oss.AliyunOssApi;
import com.kaimingwan.core.openapi.oss.AliyunOssApiBase;
import com.kaimingwan.core.openapi.yuque.YuqueApi;
import com.kaimingwan.core.openapi.yuque.YuqueApiBase;
import com.kaimingwan.core.openapi.yuque.model.YuqueDoc;
import com.kaimingwan.core.openapi.yuque.resp.FetchPostResp;
import com.kaimingwan.core.service.ImageBedService;
import com.kaimingwan.core.service.SyncService;
import com.kaimingwan.core.service.model.ImgWrapper;
import com.kaimingwan.core.thread.NamedThreadFactory;
import com.kaimingwan.core.util.ImageHashUtil;
import java.io.File;
import java.util.*;
import java.util.concurrent.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * @author wanshao create time is  2022/11/6
 **/
@Slf4j
public class SyncServiceImpl implements SyncService {

  private AliyunOssApiBase aliyunOssApi;

  private YuqueApiBase yuqueApi;


  private ImageBedService imageBedService;


  private final ThreadPoolExecutor threadPool;

  private final int paralle;

  private final String postHome;

  private final int syncTimeoutSec;

  public SyncServiceImpl(Properties conf) {
    aliyunOssApi = new AliyunOssApi(conf);
    yuqueApi = new YuqueApi(conf);
    imageBedService = new ImageBedServiceImpl();
    threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
        Runtime.getRuntime().availableProcessors() * 2, 0, TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(1000), new NamedThreadFactory("image-download-and-upload"),
        new ThreadPoolExecutor.AbortPolicy());
    this.paralle = Integer.valueOf(conf.getProperty(PropertiesKey.PARALLEL));
    this.postHome = conf.getProperty(PropertiesKey.LOCAL_POST_PATH);
    this.syncTimeoutSec = Integer.valueOf(conf.getProperty(PropertiesKey.SYNC_TIMEOUT_SEC));
  }

  @SneakyThrows
  @Override
  public void sync() {
    log.info("Begin to download yuque post list....");
    FetchPostResp fetchPostResp = yuqueApi.fetchPost();
    log.info("Total download post count " + fetchPostResp.getData().size());

    int subListSize = fetchPostResp.getData().size() / paralle + 1;

    List<List<YuqueDoc>> splitList = Lists.partition(fetchPostResp.getData(), subListSize);

    List<Future> futures = new ArrayList<>();
    for (List<YuqueDoc> docs : splitList) {
      futures.add(threadPool.submit(new SyncWorker(docs)));
    }

    for (int i = 0; i < futures.size(); i++) {
      try {
        Future future = futures.get(i);
        future.get(syncTimeoutSec, TimeUnit.SECONDS);
        log.info("Finish fetch future result index " + i);
      } catch (Exception e) {
        String errMsg =
            "Some posts sync failed. Root cause is " + ExceptionUtils.getRootCauseMessage(e);
        log.error(errMsg, e);
        throw e;
      }
    }

    threadPool.shutdown();
  }

  @SneakyThrows
  @Override
  public void writeOutProcessedPosts(String content, String fileName) {
    FileUtils.writeStringToFile(new File(postHome + "/" + fileName + ".md"), content);
  }


  class SyncWorker implements Runnable {

    private List<YuqueDoc> yuqueDocs;

    public SyncWorker(List<YuqueDoc> yuqueDocs) {
      this.yuqueDocs = yuqueDocs;
    }

    @Override
    public void run() {
      log.info("Begin fetch doc detail, total detail doc size is " + yuqueDocs.size());
      for (YuqueDoc yuqueDoc : yuqueDocs) {
        log.info("Begin to download yuque post " + yuqueDoc.getTitle());
        String docBody = yuqueApi.fetchPostDetail(yuqueDoc.getSlug()).getData().getBody();
        List<String> mdImgTags = imageBedService.parsePostMdImageTag(docBody);
        List<String> imgUrls = new ArrayList<>();
        if (CollectionUtil.isEmpty(mdImgTags)) {
          writeOutProcessedPosts(docBody, yuqueDoc.getSlug());
          continue;
        }

        mdImgTags.forEach(x -> imgUrls.add(imageBedService.parseMarkdownImageUrl(x)));

        log.info("Begin to download yuque post images...");

        for (String imgUrl : imgUrls) {
          ImgWrapper imageStrWrapper = imageBedService.downloadImage(imgUrl);
          String ossUrl = aliyunOssApi.uploadImage(imageStrWrapper.getFileName(),
              imageStrWrapper.getImgBytes(), ImageHashUtil.getImageHash(imgUrl));

          docBody = docBody.replace(imgUrl, ossUrl);
        }
        writeOutProcessedPosts(docBody, yuqueDoc.getSlug());
        log.info("Finish sync processed images. Total count is " + imgUrls.size());
      }
    }
  }
}
