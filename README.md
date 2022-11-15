## yuque-image-sync

### 基本说明
支持自动下载语雀文章，将文章中所有图片上传图床(例如阿里云OSS)后替换文
章中图片地址为图床地址，将处理好的markdown源文件保存到指定位置。理论上可兼容多种博客系统，例如Docusaurus、Hexo等。我自己个人网站用的hexo暂时就只测试了hexo.

|参数| 说明                                                                |
|  ----  |-------------------------------------------------------------------|
|yuque.token| 语雀->账户设置->token 中获取                                               |
|yuque.repo| 知识库的名称，知识库设置中路径最后的就是                                              |
|yuque.user| 账户设置->账户管理->个人路径最后就是                                              |
|yuque.new.interval.sec| 设置小于等于0的值表示拉取和处理所有语雀已发布的文章，设定具体的秒数，更新时间>(当前时间-秒数) 的文章才会被处理，提升处理效率 |                                           |
|aliyun.oss.endpoint| 阿里云OSS控制台可查，例如：https://oss-cn-hangzhou.aliyuncs.com               |
|aliyun.oss.bucket| OSS bucket名字                                                      |
|aliyun.oss.ak| 阿里云AccessKey                                                      |
|aliyun.oss.sk| 阿里云SecretKey                                                      |
|parallel| 并行度，一般设置成和你核心数一样就可以                                               |
|local.post.home| 处理后的markdown源文件需要保存的位置                                            |
|sync.timeout.sec| 每个线程获取异步处理结果的超时时间                                                 |

### 使用教程
可以参考我个人网站的使用说明[语雀文章自动同步Hexo](https://kaimingwan.com)

