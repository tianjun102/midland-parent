/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50614
Source Host           : 127.0.0.1:3306
Source Database       : midland

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2017-07-25 16:21:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `answer`
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '回答表主键ID',
  `answer_area` text COMMENT '回答类容',
  `answer_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '回答时间',
  `anwer_name` varchar(25) DEFAULT NULL COMMENT '回答人名字',
  `againstNum` int(11) DEFAULT '0' COMMENT '反对总数',
  `supportNum` int(11) DEFAULT NULL COMMENT '支持总数',
  `questions_id` int(11) DEFAULT NULL COMMENT '提问id 关联提问表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------

-- ----------------------------
-- Table structure for `appointment`
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '预约记录表ID，采用自增长',
  `appoint_sn` varchar(11) DEFAULT NULL COMMENT '预约编号',
  `source` int(11) unsigned zerofill DEFAULT NULL COMMENT '0=网站；1=微站',
  `call` varchar(20) DEFAULT NULL COMMENT '称呼',
  `phone` varchar(25) DEFAULT NULL COMMENT '手机号码',
  `house_type` int(11) DEFAULT NULL COMMENT '0=住宅；1=公寓；2=写字楼；3=商铺',
  `sell_rent` int(11) DEFAULT NULL COMMENT '0=租；1=售',
  `appointment_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '预约时间',
  `area` varchar(20) DEFAULT NULL COMMENT '所属区域',
  `community_name` varchar(20) DEFAULT NULL COMMENT '小区名字',
  `address` varchar(100) DEFAULT NULL COMMENT '门牌地址',
  `layout` varchar(11) DEFAULT NULL COMMENT '户型',
  `measure` varchar(11) DEFAULT NULL COMMENT '面积',
  `price` varchar(11) DEFAULT NULL COMMENT '售价/租价',
  `entrust_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '委托时间',
  `user_id` int(11) DEFAULT NULL COMMENT '经纪人id',
  `user_cn_name` varchar(11) DEFAULT NULL COMMENT '经纪人名字',
  `status` int(1) unsigned zerofill DEFAULT '0' COMMENT 'state；0=未处理；1=处理中；2已完成',
  `handle_time` timestamp NULL DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appointment
-- ----------------------------

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '分类id',
  `cate_id` int(11) DEFAULT NULL,
  `type` varchar(11) DEFAULT NULL COMMENT '类别；住宅工、商铺等',
  `city_id` int(11) DEFAULT NULL COMMENT '城市id',
  `enclosure` varchar(255) DEFAULT NULL COMMENT '附件地址',
  `imgUrl` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `meta_keywords` varchar(500) DEFAULT NULL COMMENT 'META关键字',
  `meta_desc` varchar(500) DEFAULT NULL COMMENT 'META描述',
  `img_desc` varchar(500) DEFAULT NULL COMMENT '缩略图描述',
  `details` text COMMENT '详情描述',
  `orderby` int(11) DEFAULT NULL COMMENT '排序',
  `clickNum` int(11) DEFAULT NULL COMMENT '点击量',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for `banner`
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `banner_img` varchar(255) DEFAULT NULL COMMENT 'PCbanner图地址',
  `banner_linkurl` varchar(255) DEFAULT NULL COMMENT '图片链接',
  `img_desc` varchar(50) DEFAULT NULL COMMENT '图片说明',
  `sort_order` int(3) DEFAULT NULL COMMENT '排序，默认升序',
  `enabled` int(1) DEFAULT '0' COMMENT '是否启用：0=未启用；1=启用；',
  `city_id` int(11) DEFAULT NULL,
  `source` int(3) DEFAULT NULL COMMENT '0=网站；1=微站',
  `model` varchar(20) DEFAULT NULL COMMENT '模块；0=首页；1=资讯；2调研市场',
  `start_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '结束时间',
  `position` varchar(255) DEFAULT NULL COMMENT 'banner位置',
  `video_url` varchar(255) DEFAULT NULL COMMENT '视频链接',
  `detail` text COMMENT '详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主页横幅';

-- ----------------------------
-- Records of banner
-- ----------------------------

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL DEFAULT '0',
  `cate_name` varchar(255) DEFAULT NULL COMMENT '分类名称',
  `status` int(11) DEFAULT '0' COMMENT '0=开放；1=关闭',
  `orderby` int(11) DEFAULT '0' COMMENT '排序值',
  `parentId` int(11) DEFAULT NULL COMMENT '父节点id',
  `type` int(11) DEFAULT NULL COMMENT '0=市场调研分类；1=资讯分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for `city`
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '城市表主键',
  `city_name` varchar(100) DEFAULT NULL COMMENT '城市名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点id；城市/区域',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------

-- ----------------------------
-- Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '配套id',
  `supporting` varchar(255) DEFAULT NULL COMMENT '配套信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config
-- ----------------------------

-- ----------------------------
-- Table structure for `elite_club`
-- ----------------------------
DROP TABLE IF EXISTS `elite_club`;
CREATE TABLE `elite_club` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '精英会主键id',
  `city_id` int(11) DEFAULT NULL COMMENT '城市id',
  `img_url` varchar(100) DEFAULT NULL COMMENT '上传图片路径',
  `img_desc` varchar(500) DEFAULT NULL COMMENT '图片描述',
  `ad_name` varchar(100) DEFAULT NULL COMMENT '活动名称',
  `ad_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '活动时间',
  `ad_title` varchar(100) DEFAULT NULL COMMENT '活动主题',
  `ad_address` varchar(200) DEFAULT NULL COMMENT '活动地址',
  `detail` text COMMENT '活动详情',
  `isshow` int(3) DEFAULT NULL COMMENT '是否开放',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of elite_club
-- ----------------------------

-- ----------------------------
-- Table structure for `elite_vip`
-- ----------------------------
DROP TABLE IF EXISTS `elite_vip`;
CREATE TABLE `elite_vip` (
  `id` int(11) NOT NULL DEFAULT '0',
  `type` varchar(20) DEFAULT NULL COMMENT '精英类别',
  `level` varchar(20) DEFAULT NULL COMMENT '会员级别',
  `cname` varchar(50) DEFAULT NULL COMMENT '中文名称',
  `ename` varchar(50) DEFAULT NULL COMMENT '英文名称',
  `address` varchar(100) DEFAULT NULL COMMENT '所属地区',
  `post` varchar(100) DEFAULT NULL COMMENT '职务',
  `img_url` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `img_desc` varchar(255) DEFAULT NULL COMMENT '图片描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of elite_vip
-- ----------------------------

-- ----------------------------
-- Table structure for `entrust`
-- ----------------------------
DROP TABLE IF EXISTS `entrust`;
CREATE TABLE `entrust` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '委托表主键id；',
  `entrust_sn` varchar(11) DEFAULT NULL COMMENT '委托编号',
  `source` int(11) DEFAULT NULL COMMENT '网站来源；0=网站；1=微站；',
  `house_type` int(11) DEFAULT NULL COMMENT '房屋类型；0=住宅；1=公寓；2=写字楼；3=商铺',
  `sell_rent` int(11) DEFAULT NULL COMMENT '0=租；1=售',
  `entrust_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '委托时间',
  `area` varchar(20) DEFAULT NULL COMMENT '所属区域',
  `community_name` varchar(20) DEFAULT NULL COMMENT '小区名字',
  `address` varchar(100) DEFAULT NULL COMMENT '门牌地址',
  `layout` varchar(11) DEFAULT NULL COMMENT '户型',
  `measure` varchar(20) DEFAULT NULL COMMENT '面积',
  `price` varchar(11) DEFAULT NULL COMMENT '价格',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_cn_name` varchar(11) DEFAULT NULL COMMENT '经纪人名字',
  `status` int(1) unsigned DEFAULT NULL COMMENT 'state；0=未处理；1=处理中',
  `handle_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '处理时间',
  `call` varchar(255) DEFAULT NULL COMMENT '称呼',
  `renovation` int(11) DEFAULT NULL COMMENT '0=简装；1=精装',
  `supporting` varchar(255) DEFAULT NULL COMMENT '配套；多种配套使用 “|” 隔开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entrust
-- ----------------------------

-- ----------------------------
-- Table structure for `feedback`
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '反馈信息表',
  `username` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `phone` varchar(25) DEFAULT NULL COMMENT '手机号码',
  `feedback_content` text,
  `add_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '反馈时间',
  `status` int(3) DEFAULT NULL COMMENT 'state 0=已取消；1=已完成；2=处理中',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------

-- ----------------------------
-- Table structure for `feedback_email`
-- ----------------------------
DROP TABLE IF EXISTS `feedback_email`;
CREATE TABLE `feedback_email` (
  `id` int(11) DEFAULT NULL COMMENT '反馈邮箱',
  `city_id` int(11) DEFAULT NULL COMMENT '城市id',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `type` varchar(255) DEFAULT NULL COMMENT '类型；总部，分布等',
  `contact_person` varchar(500) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(25) DEFAULT NULL COMMENT '联系电话'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback_email
-- ----------------------------

-- ----------------------------
-- Table structure for `film_library`
-- ----------------------------
DROP TABLE IF EXISTS `film_library`;
CREATE TABLE `film_library` (
  `id` int(11) DEFAULT NULL COMMENT '片库id',
  `city_id` int(11) DEFAULT NULL COMMENT '城市id',
  `img_desc` varchar(255) DEFAULT NULL COMMENT '图片描述',
  `houses_name` varchar(500) DEFAULT NULL COMMENT '楼盘名称',
  `houses_id` int(11) DEFAULT NULL COMMENT '楼盘id',
  `introduction` text COMMENT '简介',
  `video_url` varchar(225) DEFAULT NULL COMMENT '视频url地址'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of film_library
-- ----------------------------

-- ----------------------------
-- Table structure for `footer`
-- ----------------------------
DROP TABLE IF EXISTS `footer`;
CREATE TABLE `footer` (
  `id` int(11) DEFAULT NULL COMMENT '网站页脚配置表',
  `registration_protocol` text COMMENT '注册协议',
  `disclaimer` text COMMENT '免责声明',
  `record_number` text COMMENT '备案编号',
  `recruitment` text COMMENT '人才招聘',
  `about_us` text COMMENT '关于我们',
  `privacy` text COMMENT '隐私政策',
  `service_area` text COMMENT '服务范围',
  `trading_process` text COMMENT '买卖流程',
  `isexport` int(3) DEFAULT NULL COMMENT '是否外销0=外销；1=非外销；2精英会',
  `purpose` text COMMENT '精英会宗旨',
  `elite_desc` text COMMENT '精英会简介',
  `membership` text COMMENT '入会资格',
  `development` text COMMENT '发展前沿'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of footer
-- ----------------------------

-- ----------------------------
-- Table structure for `hot_search`
-- ----------------------------
DROP TABLE IF EXISTS `hot_search`;
CREATE TABLE `hot_search` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keywords` varchar(50) DEFAULT NULL COMMENT '关键字',
  `count` int(11) DEFAULT NULL COMMENT '查询次数',
  `sort_order` int(3) DEFAULT NULL COMMENT '排序，默认升序',
  `city_id` int(11) DEFAULT NULL COMMENT '城市id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  `isshow` int(11) DEFAULT NULL COMMENT '是否开启',
  `orderby` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='热搜关键字';

-- ----------------------------
-- Records of hot_search
-- ----------------------------

-- ----------------------------
-- Table structure for `information`
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资讯表，主键',
  `cate_id` int(11) DEFAULT NULL COMMENT '分类id',
  `cate_parentId` int(11) DEFAULT NULL COMMENT '分类父id',
  `type` varchar(11) DEFAULT NULL COMMENT '楼盘类型；住宅；商铺等',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `source` varchar(100) DEFAULT NULL COMMENT '来源',
  `enclosure` varchar(255) DEFAULT NULL COMMENT '附件地址',
  `meta_keywords` varchar(500) DEFAULT NULL COMMENT 'meta关键字',
  `meta_desc` varchar(500) DEFAULT NULL COMMENT 'meta描述',
  `imgUrl` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `img_desc` varchar(500) DEFAULT NULL COMMENT '缩略图描述',
  `details` text COMMENT '详细描述',
  `orderby` int(11) DEFAULT NULL COMMENT '排序',
  `clickNum` int(11) DEFAULT NULL COMMENT '点击次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of information
-- ----------------------------

-- ----------------------------
-- Table structure for `liaison_record`
-- ----------------------------
DROP TABLE IF EXISTS `liaison_record`;
CREATE TABLE `liaison_record` (
  `id` int(11) DEFAULT NULL COMMENT '联络记录表id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `phone` varchar(25) DEFAULT NULL COMMENT '手机',
  `cate` varchar(100) DEFAULT NULL COMMENT '分类',
  `leaving_message` text COMMENT '留言',
  `add_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '提交时间',
  `is_ontact` int(3) DEFAULT NULL COMMENT '是否联系'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of liaison_record
-- ----------------------------

-- ----------------------------
-- Table structure for `link_url_manager`
-- ----------------------------
DROP TABLE IF EXISTS `link_url_manager`;
CREATE TABLE `link_url_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '链接关联表',
  `city_id` int(11) DEFAULT NULL COMMENT '城市id',
  `link_name` varchar(20) DEFAULT NULL COMMENT '链接名',
  `link_url` varchar(100) DEFAULT NULL COMMENT '链接',
  `contacts` varchar(20) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `remarks` text COMMENT '备注',
  `isshow` int(2) DEFAULT NULL COMMENT '是否开启 0=开启；1=关闭',
  `orderby` int(3) DEFAULT NULL COMMENT '排序字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of link_url_manager
-- ----------------------------

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '菜单表主键',
  `icon_img` varchar(100) DEFAULT NULL COMMENT '小图标',
  `city_id` int(11) DEFAULT NULL COMMENT '城市id',
  `platform` int(2) DEFAULT NULL COMMENT '0=pc网站；1=微站',
  `menu_name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `click_num` int(11) DEFAULT NULL COMMENT '点击量',
  `orderby` int(11) DEFAULT NULL COMMENT '排序字段',
  `url` varchar(100) DEFAULT NULL COMMENT '菜单栏链接',
  `isshow` int(2) DEFAULT NULL COMMENT '是否开启',
  `position` int(2) DEFAULT NULL COMMENT 'position=1顶部；position=0底部',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点id',
  `source` int(2) DEFAULT NULL COMMENT '平台来源 0=网站；1=微站',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_name` varchar(32) DEFAULT NULL COMMENT '权限名',
  `permission_sign` varchar(128) DEFAULT NULL COMMENT '权限标识,程序中判断使用,如"user:create"',
  `description` varchar(256) DEFAULT NULL COMMENT '权限描述,UI界面显示使用',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级id',
  `parent_sign` varchar(128) DEFAULT NULL,
  `permission_path` varchar(256) DEFAULT NULL COMMENT '访问路径',
  `permission_type` int(11) DEFAULT NULL COMMENT '权限类型(0一级菜单 1二级菜单 2功能菜单)',
  `order_no` int(11) DEFAULT NULL COMMENT '排序号',
  `status` int(11) DEFAULT NULL COMMENT 'state',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '用户列表', 'userList', null, '3', 'systemManage', 'rest/user/userIndex', '1', '1', '1');
INSERT INTO `permission` VALUES ('2', '角色列表', 'rolelist', null, '3', 'systemManage', 'rest/role/roleIndex', '1', '2', '1');
INSERT INTO `permission` VALUES ('3', '系统管理', 'systemManage', null, null, null, null, '0', '2', '1');
INSERT INTO `permission` VALUES ('4', '客户管理', 'custManage', null, null, null, null, '0', '1', '1');
INSERT INTO `permission` VALUES ('5', '区域列表', 'arealist', null, '4', 'custManage', 'rest/cust/areaIndex', '1', '1', '1');
INSERT INTO `permission` VALUES ('6', '客户列表', 'custlist', null, '4', 'custManage', 'rest/cust/custIndex', '1', '2', '1');
INSERT INTO `permission` VALUES ('7', '个人中心', 'personalCenter', null, null, null, null, '0', '3', '1');
INSERT INTO `permission` VALUES ('8', '修改密码', 'modifyPassword', null, '7', 'personalCenter', 'rest/user/toModifyPwdPage', '1', '2', '1');
INSERT INTO `permission` VALUES ('9', '公告管理', 'noticeManage', null, null, null, null, '0', '4', '1');
INSERT INTO `permission` VALUES ('10', '公告列表', 'noticelist', null, '9', 'noticeManage', 'rest/notice/noticeIndex', '1', '1', '1');
INSERT INTO `permission` VALUES ('11', '日志管理', 'sysLogManage', null, null, null, null, '0', '5', '1');
INSERT INTO `permission` VALUES ('12', '日志列表', 'sysLoglist', null, '11', 'sysLogManage', 'rest/sysLog/sysLogIndex', '1', '1', '1');
INSERT INTO `permission` VALUES ('13', '数据备份', 'backupList', null, '3', 'systemManage', 'rest/dataBackup/index', '1', '13', '1');
INSERT INTO `permission` VALUES ('14', '产品管理', 'product', null, null, null, null, '0', '1', '1');
INSERT INTO `permission` VALUES ('15', '分类', 'categoryList', null, '14', 'product', 'rest/product/showCategoryIndex', '1', '1', '1');
INSERT INTO `permission` VALUES ('16', '产品列表', 'productList', null, '14', 'product', 'rest/product/showProductIndex', '1', '2', '1');
INSERT INTO `permission` VALUES ('17', '库存调整单', 'kctzdList', null, '14', 'product', 'rest/stock/showKctzdIndex', '1', '3', '1');
INSERT INTO `permission` VALUES ('18', '库存查询', 'queryList', null, '14', 'product', 'rest/stock/stockQueryIndex', '1', '4', '1');
INSERT INTO `permission` VALUES ('19', '产品分销区域', 'distPlanList', null, '14', 'product', 'rest/distPlan/showDistPlanIndex', '1', '5', '1');
INSERT INTO `permission` VALUES ('20', '订单管理', 'orderInfo', null, null, null, null, '0', '1', '1');
INSERT INTO `permission` VALUES ('21', '订单列表', 'orderInfoList', null, '20', 'orderInfo', 'rest/order/showOrderInfoIndex', '1', '1', '1');
INSERT INTO `permission` VALUES ('22', '结算管理', 'settlement', null, null, null, null, '0', '1', '1');
INSERT INTO `permission` VALUES ('23', '财务结算单', 'settlementList', null, '22', 'settlement', '/rest/settlement/settlementIndex', '1', '1', '1');
INSERT INTO `permission` VALUES ('24', '报表中心', 'dataTable', null, null, null, null, '0', '1', '1');
INSERT INTO `permission` VALUES ('25', '客户统计', 'showCustDataTable', null, '24', 'dataTable', '/rest/dataTable/showCustDataTable', '1', '1', '1');
INSERT INTO `permission` VALUES ('26', '订单统计', 'showOrderDataTable', null, '24', 'dataTable', '/rest/dataTable/showOrderDataTable', '1', '2', '1');
INSERT INTO `permission` VALUES ('27', '商品排行统计', 'showProductDataTable', null, '24', 'dataTable', '/rest/dataTable/showProductDataTable', '1', '3', '1');
INSERT INTO `permission` VALUES ('28', '财务统计', 'showPriceDataTable', null, '24', 'dataTable', '/rest/dataTable/showPriceDataTable', '1', '4', '1');

-- ----------------------------
-- Table structure for `qr_code`
-- ----------------------------
DROP TABLE IF EXISTS `qr_code`;
CREATE TABLE `qr_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id 二维码管理表',
  `img_url` varchar(255) DEFAULT NULL COMMENT '二维码连接',
  `city_id` varchar(11) DEFAULT NULL COMMENT '城市id',
  `source` int(11) DEFAULT NULL COMMENT '平台 来源',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `isshow` int(2) DEFAULT NULL COMMENT '是否开放',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qr_code
-- ----------------------------

-- ----------------------------
-- Table structure for `questions`
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `questions_title` varchar(255) DEFAULT NULL COMMENT '提问主题',
  `source` int(11) DEFAULT NULL COMMENT '''0=网站；1=微站''',
  `question_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '提问时间',
  `question_name` varchar(20) DEFAULT NULL,
  `question_phone` varchar(25) DEFAULT NULL COMMENT '提问手机',
  `auditor` varchar(25) DEFAULT NULL COMMENT '审核人',
  `questions_area` text COMMENT '提问类容',
  `status` int(1) DEFAULT '0' COMMENT '0=待审核；1=审核通过；2=审核失败',
  `click_num` int(11) DEFAULT '0' COMMENT '点击量',
  `fabulous` int(11) DEFAULT '0' COMMENT '点赞',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of questions
-- ----------------------------

-- ----------------------------
-- Table structure for `quotation`
-- ----------------------------
DROP TABLE IF EXISTS `quotation`;
CREATE TABLE `quotation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '行情表，主键',
  `data_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '数据时间',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `city_id` int(11) DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `slice_id` int(11) DEFAULT NULL COMMENT '片区',
  `deal_num` int(11) DEFAULT NULL COMMENT '成交数量',
  `deal_acreage` int(11) DEFAULT NULL COMMENT '成交面积',
  `price` varchar(11) DEFAULT '' COMMENT '成交均价',
  `sold_num` int(11) DEFAULT NULL COMMENT '可售套数',
  `sold_area` varchar(255) DEFAULT NULL COMMENT '可售面积',
  `ring_ratio` varchar(11) DEFAULT NULL COMMENT '环比',
  `update_time` varchar(11) DEFAULT NULL COMMENT '更新时间',
  `is_new` int(2) DEFAULT NULL COMMENT '1=新房；0=二手房',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quotation
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名',
  `role_sign` varchar(128) DEFAULT NULL COMMENT '角色标识,程序中判断使用,如"admin"',
  `description` varchar(256) DEFAULT NULL COMMENT '角色描述,UI界面显示使用',
  `role_type` int(11) DEFAULT NULL COMMENT '角色类型：0=公司内；1=客户;',
  `state` int(11) DEFAULT NULL COMMENT 'state',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', 'admin', '管理员', '0', '1');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `role_id` int(11) unsigned DEFAULT NULL COMMENT '角色id',
  `permission_id` int(11) unsigned DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=323 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色与权限关联表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('98', '18', '4');
INSERT INTO `role_permission` VALUES ('99', '18', '5');
INSERT INTO `role_permission` VALUES ('100', '18', '6');
INSERT INTO `role_permission` VALUES ('101', '18', '14');
INSERT INTO `role_permission` VALUES ('102', '18', '15');
INSERT INTO `role_permission` VALUES ('103', '18', '16');
INSERT INTO `role_permission` VALUES ('104', '18', '17');
INSERT INTO `role_permission` VALUES ('105', '18', '18');
INSERT INTO `role_permission` VALUES ('106', '18', '19');
INSERT INTO `role_permission` VALUES ('107', '18', '20');
INSERT INTO `role_permission` VALUES ('108', '18', '21');
INSERT INTO `role_permission` VALUES ('109', '18', '22');
INSERT INTO `role_permission` VALUES ('110', '18', '23');
INSERT INTO `role_permission` VALUES ('111', '18', '3');
INSERT INTO `role_permission` VALUES ('112', '18', '1');
INSERT INTO `role_permission` VALUES ('113', '18', '2');
INSERT INTO `role_permission` VALUES ('114', '18', '13');
INSERT INTO `role_permission` VALUES ('178', '1', '24');
INSERT INTO `role_permission` VALUES ('179', '1', '25');
INSERT INTO `role_permission` VALUES ('180', '1', '26');
INSERT INTO `role_permission` VALUES ('181', '1', '27');
INSERT INTO `role_permission` VALUES ('182', '1', '28');
INSERT INTO `role_permission` VALUES ('183', '1', '4');
INSERT INTO `role_permission` VALUES ('184', '1', '5');
INSERT INTO `role_permission` VALUES ('185', '1', '6');
INSERT INTO `role_permission` VALUES ('191', '1', '20');
INSERT INTO `role_permission` VALUES ('192', '1', '21');
INSERT INTO `role_permission` VALUES ('193', '1', '22');
INSERT INTO `role_permission` VALUES ('194', '1', '23');
INSERT INTO `role_permission` VALUES ('195', '1', '3');
INSERT INTO `role_permission` VALUES ('196', '1', '1');
INSERT INTO `role_permission` VALUES ('197', '1', '2');
INSERT INTO `role_permission` VALUES ('198', '1', '13');
INSERT INTO `role_permission` VALUES ('199', '1', '7');
INSERT INTO `role_permission` VALUES ('200', '1', '8');
INSERT INTO `role_permission` VALUES ('201', '1', '9');
INSERT INTO `role_permission` VALUES ('202', '1', '10');
INSERT INTO `role_permission` VALUES ('203', '1', '11');
INSERT INTO `role_permission` VALUES ('204', '1', '12');
INSERT INTO `role_permission` VALUES ('205', '1', '14');
INSERT INTO `role_permission` VALUES ('206', '1', '15');
INSERT INTO `role_permission` VALUES ('207', '1', '17');
INSERT INTO `role_permission` VALUES ('208', '1', '18');
INSERT INTO `role_permission` VALUES ('209', '1', '16');
INSERT INTO `role_permission` VALUES ('238', '15', '14');
INSERT INTO `role_permission` VALUES ('239', '15', '16');
INSERT INTO `role_permission` VALUES ('240', '15', '17');
INSERT INTO `role_permission` VALUES ('241', '15', '18');
INSERT INTO `role_permission` VALUES ('242', '15', '20');
INSERT INTO `role_permission` VALUES ('243', '15', '21');
INSERT INTO `role_permission` VALUES ('244', '15', '22');
INSERT INTO `role_permission` VALUES ('245', '15', '23');
INSERT INTO `role_permission` VALUES ('246', '15', '24');
INSERT INTO `role_permission` VALUES ('247', '15', '25');
INSERT INTO `role_permission` VALUES ('248', '15', '26');
INSERT INTO `role_permission` VALUES ('249', '15', '27');
INSERT INTO `role_permission` VALUES ('250', '15', '28');
INSERT INTO `role_permission` VALUES ('251', '15', '7');
INSERT INTO `role_permission` VALUES ('252', '15', '8');
INSERT INTO `role_permission` VALUES ('253', '11', '14');
INSERT INTO `role_permission` VALUES ('254', '11', '16');
INSERT INTO `role_permission` VALUES ('255', '11', '17');
INSERT INTO `role_permission` VALUES ('256', '11', '18');
INSERT INTO `role_permission` VALUES ('257', '11', '20');
INSERT INTO `role_permission` VALUES ('258', '11', '21');
INSERT INTO `role_permission` VALUES ('259', '11', '7');
INSERT INTO `role_permission` VALUES ('260', '11', '8');
INSERT INTO `role_permission` VALUES ('261', '11', '22');
INSERT INTO `role_permission` VALUES ('262', '11', '23');
INSERT INTO `role_permission` VALUES ('263', '11', '24');
INSERT INTO `role_permission` VALUES ('264', '11', '25');
INSERT INTO `role_permission` VALUES ('265', '11', '26');
INSERT INTO `role_permission` VALUES ('266', '11', '27');
INSERT INTO `role_permission` VALUES ('267', '19', '4');
INSERT INTO `role_permission` VALUES ('268', '19', '5');
INSERT INTO `role_permission` VALUES ('269', '19', '6');
INSERT INTO `role_permission` VALUES ('270', '19', '14');
INSERT INTO `role_permission` VALUES ('271', '19', '15');
INSERT INTO `role_permission` VALUES ('272', '19', '16');
INSERT INTO `role_permission` VALUES ('273', '19', '17');
INSERT INTO `role_permission` VALUES ('274', '19', '18');
INSERT INTO `role_permission` VALUES ('275', '19', '20');
INSERT INTO `role_permission` VALUES ('276', '19', '21');
INSERT INTO `role_permission` VALUES ('277', '19', '22');
INSERT INTO `role_permission` VALUES ('278', '19', '23');
INSERT INTO `role_permission` VALUES ('279', '19', '24');
INSERT INTO `role_permission` VALUES ('280', '19', '25');
INSERT INTO `role_permission` VALUES ('281', '19', '26');
INSERT INTO `role_permission` VALUES ('282', '19', '27');
INSERT INTO `role_permission` VALUES ('283', '19', '28');
INSERT INTO `role_permission` VALUES ('284', '19', '3');
INSERT INTO `role_permission` VALUES ('285', '19', '1');
INSERT INTO `role_permission` VALUES ('286', '19', '2');
INSERT INTO `role_permission` VALUES ('287', '19', '13');
INSERT INTO `role_permission` VALUES ('288', '19', '7');
INSERT INTO `role_permission` VALUES ('289', '19', '8');
INSERT INTO `role_permission` VALUES ('290', '19', '9');
INSERT INTO `role_permission` VALUES ('291', '19', '10');
INSERT INTO `role_permission` VALUES ('292', '19', '11');
INSERT INTO `role_permission` VALUES ('293', '19', '12');
INSERT INTO `role_permission` VALUES ('294', '1', '19');
INSERT INTO `role_permission` VALUES ('295', '1', '4');
INSERT INTO `role_permission` VALUES ('296', '1', '5');
INSERT INTO `role_permission` VALUES ('297', '1', '6');
INSERT INTO `role_permission` VALUES ('298', '1', '14');
INSERT INTO `role_permission` VALUES ('299', '1', '15');
INSERT INTO `role_permission` VALUES ('300', '1', '16');
INSERT INTO `role_permission` VALUES ('301', '1', '17');
INSERT INTO `role_permission` VALUES ('302', '1', '18');
INSERT INTO `role_permission` VALUES ('303', '1', '19');
INSERT INTO `role_permission` VALUES ('304', '1', '20');
INSERT INTO `role_permission` VALUES ('305', '1', '21');
INSERT INTO `role_permission` VALUES ('306', '1', '22');
INSERT INTO `role_permission` VALUES ('307', '1', '23');
INSERT INTO `role_permission` VALUES ('308', '1', '24');
INSERT INTO `role_permission` VALUES ('309', '1', '25');
INSERT INTO `role_permission` VALUES ('310', '1', '26');
INSERT INTO `role_permission` VALUES ('311', '1', '27');
INSERT INTO `role_permission` VALUES ('312', '1', '28');
INSERT INTO `role_permission` VALUES ('313', '1', '3');
INSERT INTO `role_permission` VALUES ('314', '1', '1');
INSERT INTO `role_permission` VALUES ('315', '1', '2');
INSERT INTO `role_permission` VALUES ('316', '1', '13');
INSERT INTO `role_permission` VALUES ('317', '1', '7');
INSERT INTO `role_permission` VALUES ('318', '1', '8');
INSERT INTO `role_permission` VALUES ('319', '1', '9');
INSERT INTO `role_permission` VALUES ('320', '1', '10');
INSERT INTO `role_permission` VALUES ('321', '1', '11');
INSERT INTO `role_permission` VALUES ('322', '1', '12');

-- ----------------------------
-- Table structure for `special_page`
-- ----------------------------
DROP TABLE IF EXISTS `special_page`;
CREATE TABLE `special_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '特殊页面配置表；id主键',
  `city_id` int(11) DEFAULT NULL COMMENT '城市id',
  `source` int(2) DEFAULT NULL COMMENT '0=网站；1=微站',
  `mode_name` varchar(100) DEFAULT NULL COMMENT '模块名称',
  `position` int(3) DEFAULT NULL COMMENT '位置',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `description` text COMMENT '描述',
  `price` float DEFAULT NULL COMMENT '价格',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `img_url` varchar(100) DEFAULT NULL COMMENT '图片连接',
  `subway_distance` varchar(255) DEFAULT NULL COMMENT '地铁距离描述',
  `link_url` varchar(255) DEFAULT NULL COMMENT '外网连接',
  `detail` text COMMENT '详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of special_page
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_fair`
-- ----------------------------
DROP TABLE IF EXISTS `trade_fair`;
CREATE TABLE `trade_fair` (
  `id` int(11) DEFAULT NULL COMMENT '楼盘展销会主键id',
  `img_url` varchar(100) DEFAULT NULL COMMENT '楼盘图片连接',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `houses_id` int(11) DEFAULT NULL COMMENT '楼盘id',
  `introduction` text COMMENT '简介',
  `isshow` int(3) DEFAULT NULL COMMENT '是否开启'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trade_fair
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `user_cn_name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `password` char(64) DEFAULT NULL COMMENT '密码',
  `user_type` int(1) DEFAULT NULL COMMENT '用户类型：0=智者汇；1=渠道渠道商；2=终端服务商；3=安装专员；',
  `state` int(1) DEFAULT NULL COMMENT 'state(0失效,1生效,3删除)',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机（可作为登录账号），唯一',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `head_img` varchar(256) DEFAULT NULL COMMENT '头像图片路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '系统管理员', 'e91b1e9b5d137b0fbe34415093e2e5e12fad83812058ad538b2bc7d71abb8f8b', '0', '1', 'ad@qq.com', '13600403645', '2014-07-17 12:59:08', null, '2017-06-16 12:00:16', null);

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) unsigned DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户与角色关联表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('88', '88', '11');
INSERT INTO `user_role` VALUES ('90', '98', '15');
INSERT INTO `user_role` VALUES ('92', '88', '15');
INSERT INTO `user_role` VALUES ('93', '89', '15');
INSERT INTO `user_role` VALUES ('95', '114', '15');
INSERT INTO `user_role` VALUES ('97', '141', '1');
INSERT INTO `user_role` VALUES ('98', '99', '1');
INSERT INTO `user_role` VALUES ('99', '120', '15');
INSERT INTO `user_role` VALUES ('100', '144', '15');
INSERT INTO `user_role` VALUES ('101', '153', '1');
INSERT INTO `user_role` VALUES ('102', '153', '10');
INSERT INTO `user_role` VALUES ('103', '153', '11');
INSERT INTO `user_role` VALUES ('104', '153', '12');
INSERT INTO `user_role` VALUES ('105', '153', '13');
INSERT INTO `user_role` VALUES ('106', '153', '14');
INSERT INTO `user_role` VALUES ('107', '153', '15');
INSERT INTO `user_role` VALUES ('108', '153', '16');
INSERT INTO `user_role` VALUES ('109', '154', '11');
INSERT INTO `user_role` VALUES ('110', '147', '1');
INSERT INTO `user_role` VALUES ('111', '147', '10');
INSERT INTO `user_role` VALUES ('112', '147', '11');
INSERT INTO `user_role` VALUES ('113', '147', '12');
INSERT INTO `user_role` VALUES ('114', '147', '13');
INSERT INTO `user_role` VALUES ('115', '147', '14');
INSERT INTO `user_role` VALUES ('116', '147', '15');
INSERT INTO `user_role` VALUES ('117', '147', '16');
INSERT INTO `user_role` VALUES ('118', '134', '15');
INSERT INTO `user_role` VALUES ('119', '164', '10');
INSERT INTO `user_role` VALUES ('120', '165', '15');
INSERT INTO `user_role` VALUES ('121', '146', '11');
INSERT INTO `user_role` VALUES ('123', '128', '15');
INSERT INTO `user_role` VALUES ('124', '1', '1');
INSERT INTO `user_role` VALUES ('128', '168', '13');
INSERT INTO `user_role` VALUES ('130', '171', '11');
INSERT INTO `user_role` VALUES ('131', '173', '15');
INSERT INTO `user_role` VALUES ('132', '172', '15');
INSERT INTO `user_role` VALUES ('133', '173', '11');
INSERT INTO `user_role` VALUES ('134', '176', '1');
INSERT INTO `user_role` VALUES ('135', '177', '15');
INSERT INTO `user_role` VALUES ('138', '179', '11');
