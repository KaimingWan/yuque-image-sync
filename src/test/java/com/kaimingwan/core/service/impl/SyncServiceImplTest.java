package com.kaimingwan.core.service.impl;

import com.kaimingwan.core.openapi.oss.AliyunOssApi;
import com.kaimingwan.core.openapi.yuque.YuqueApi;
import com.kaimingwan.core.service.ImageBedService;
import com.kaimingwan.core.service.SyncService;
import java.util.Properties;
import junit.framework.TestCase;

/**
 * @author wanshao create time is  2022/11/9
 **/
public class SyncServiceImplTest extends TestCase {


  private static SyncServiceImpl syncService;


  static {
    ConfigServiceImpl configServiceImpl = new ConfigServiceImpl();
    Properties confProps = configServiceImpl.getProperties();
    syncService = new SyncServiceImpl(confProps);
  }

  public void testFormatContent() {
    String content= "---\n"
        + "\n"
        + "title: 创新者的窘境读书笔记<br />urlname: 万少<br />date: 2022-04-30<br />updated: 2022-04-30<br />author: 万少<br />top: false<br />cover: false<br />categories: 读书笔记<br />tags:\n"
        + "\n"
        + "- 读书笔记\n"
        + "- 创新者的窘境\n"
        + "\n"
        + "---";

    content = syncService.formatContent(content, "<div style=\"display:none\">[\\s\\S]*?<\\/div>", "");
    content = syncService.formatContent(content, "(<br>[\\s\\n]){2}", "<br>");
    content = syncService.formatContent(content, "(<br \\/>[\\n]?){2}", "<br />\n");
    content = syncService.formatContent(content, "<br \\/>", "\n");
    content = syncService.formatContent(content, "<a name=\\\".*?\\\"><\\/a>", "");
    System.out.println(content);
  }
}