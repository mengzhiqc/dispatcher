dispatcher
==========

#赏金系统
	赏金系统主要用来为爱房楼盘小组员工合理进行绩效评估，是除了point积分系统之外另外一种评估工具。
	发布赏金的人员定义好任务类型、任务金钱数量、任务预计完成时间、任务需求文档。
	接受赏金的员工在任务列表点选接标，并在规定时间内完成的，获得该标的金钱数。
	每个双月，我们以赏金获得数作为一个很大维度对员工进行绩效评估。
	
#开发周期
	2012-12 ~2012-1	
	
#使用架构
	struts + spring + hibernate
	通过maven对代码进行管理
	前台页面使用bootstrap
		
#参加任务人员
	* 孟智
	* 廖麒麟

#功能具体需求
	页面功能总述
	|-首页
	|-任务列表
	|-赏金任务发布者管理页面（增删改查）
		|-增 添加任务
		|-删 删除任务
		|-改 编辑任务
		|-查 已发布任务 <- 赛选查询逻辑（可以使用lucence或者solr）
	|-赏金任务接收者管理页面（增删改查）
		|-增 接标任务触发服务
		|-删 删除已接任务
		|-改 修改已接任务 主要是文档、完成阶段等等
		|-查 查询我的任务
	|-OAuth接口
	
* OAuth接口
	* anJuke OAuth wiki文档地址： [OAuth Wiki维护](http://wiki.corp.anjuke.com/index.php?title=Oauth) 	
	
	* Action类 com.aifang.controller.user.LoginAction 处理了整个登陆认证的过程
	>TODO：认证的操作考虑做成intecepter去做，应该可以在结构上更好的理解一点。
	
* User表设计 
 
  因为使用了OAUTH登陆，在第一次登陆的时候，会往本地数据库users表插入一条数据，表结构初步设计如下：
	<pre>
    create table `users` (
		id int not null auto_increment comment '主键自增ID',
		username varchar(100) not null default '' comment '用户名,同oauth中的username',
		chinesename varchar(100) not null default '' comment '中文名',
		email varchar(100) not null default '' comment 'Email地址',
		created int comment '创建时间',
		last_update datetime comment '最后更新时间' ,
		primary key(id)
	) engine=innodb default charset=utf8 comment='用户基本表';
	</pre>
	