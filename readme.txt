﻿2011-1-8
修改处包括:
1.重构之前的代码，将查询数据库和缓存数据绑定在一起
2.重构权限验证处代码，将basic和oauth两种验证方式封装起来
3.目前添加了新浪微博、网易微博、嘀咕的同步功能
4.添加了fanfou的随便看看的功能，命令为-see,实现类为PubliclineCommand
5.修复了清除全部缓存时出现数组访问错误的缺陷

2011-1-9
修改处包括
1.添加了消息推送代码，处理类为FanfouPushTask
2.若消息已经推送过了，将消息放在缓存中，下次就不推送了

2011-1-9
修改处包括
1.提交时appengine-web.xml误覆盖了，恢复至最初版本
2.修改了同步消息时的提示信息，之前的提示信息有误
3.当push消息无内容时，不发送XMPP消息

2011-1-10
修改处包括
1.移除了缓存主页消息ID和判断主页消息是否重复的功能，原因是没有必要，之前这样做原因是push的时候没有保存lastStatusId
2.添加了查看收件箱和发件箱的代码，实现类分外为OutBoxMessageCommand和InBoxMessageCommand
3.添加了回复私信的功能，实现类为DirectMessageHandler
4.添加了直接发送私信的功能，实现类为MessageCommand

2011-1-16
修改处包括
1.添加了oauth认证时采用get请求的类，即实现了UrlOAuthProvider和UrlOAuthConsumer两个类
2.添加了同步到腾讯微博的功能
3.添加了同步到豆瓣我说的功能

2011-1-27
修改处包括
1.使用-unbind tencent命令时提示参数无效的问题

2011-2-18
修改处包括
1.添加同步更新到搜狐微博的功能

2011-2-20
修改处包括
1.添加了使用OAuth2.0权限验证方式验证人人网
2.添加了人人网的通知发送代码，目前尚不能同步人人网的新鲜事，因为接口没有人人网没有将同步新鲜事的API接口提供出来

2011-3-7
修改处包括
1.开启了消息推送功能，预定一个应用支撑100个用户的消息推送功能，1分钟执行一次定时任务，分10次将10个任务放置在任务列表中，
任务列表1分钟执行10次，即一个任务执行6s，就GAE现在的配置，应该是可以支撑的了的。具体可以新开一个账号测试一下。

2011-3-17
修改处包括
1.修改了消息推送的功能，查看系统最大可以支撑的用户数，目前设定的用户数为900，查看是否可以支撑得住，GAE默认配置中的各项数据
是否有超标的情况发生。若方案可行，将代码提交到SVN上。
2.目前设定的仍然是1分钟刷新一次，可能较为频繁，具体数据可以再实际测试

2011-3-18
修改处包括：
1.添加了单独发送到fanfou和twitter成功后的提示信息，之前发送成功后没有任何提示信息

2011-3-20 mcxiaoke
修改了一点点文字细节，未修改任何功能，部署了一个开启PUSH的测试版，有时间再仔细测试

2011-3-26 
修改处包括：
1.添加了保存fanfou消息的代码，实现类为FanFouMessagePersistence.

2011-5-1
修改处包括：
1.添加了查看热词和搜索的功能，实现类分别为HotCommand和SearchCommand

2011-5-6
修改处包括：
1. 添加字数超标提示
2. 修改发送失败提示信息
3. 添加统计数据查看功能
4. 微调热词提示信息

2011-5-20
修改处包括：
1.修正了使用home、mentions、see命令时Member中的lastStatusId被覆盖了，导致使用命令查看消息时会出现紊乱的问题
2.在Member类中添加lastMentionId字段，在StatusesHanler类中提取抽象出公共的方法

2011-06-19
1. BasicAuth使用UTF-8编码，修复了用户名不能为中文，密码不能有特殊字符的BUG
2. 微调了发布消息后的提示信息

2011-9-8
1.修改了twitter OAuth的常量定义
2.为fanfou增加了OAuth的功能，绑定OAuth后原来使用bind绑定的用户密码被自动擦除掉；没有OAuth绑定功能和之前的保持一致

2011-11-26
1.增加查看黑名单功能
2.增加使用-me命令时查看未读的mentions, direct message,以及关注请求数量功能

2011-11-26
1.使用unbind fanfou命令时候同时擦除掉OAuth的绑定信息

2012-2-8
1.去掉bind命令
2.添加了-oauthFanfou和-oauthVeriy两个命令，为不会翻墙的同学提供方便
3.去掉了fanfou的基本验证方式，只采用OAuth的方式

2012-2-9
1.获取黑名单列表时添加了page参数，可获取所有的黑名单列表

2012-2-27
1.去掉了项目中的json包
2.添加了获取source的代码

2012-2-28
1.重构代码
2.删除无效代码

2012-2-28
1.使用scribe库，替换设置OAuth认证信息的代码
2.删除无效代码

2012-2-29
1.重构代码，删除无效代码
2.添加单独更新微博消息的代码

2012-3-26
1.去掉时间限制
2.增加统计数