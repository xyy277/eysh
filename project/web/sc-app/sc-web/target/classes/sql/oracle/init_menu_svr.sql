/* svrMenu1 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('7b54f756fa2f4d5bb6d9ddfa7021670a', '', '0004', '服务协同', 'svr', 'platform', 'menu', 'svr', 'data-pjax', '', '1', '0', 'svr', '', 2, '1', '59015fc95f0841088d2ac243fa2aaba1', 1502539146, '0')
/* svrMenu2 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('03eec58e79724923b60ae9722c302d3d', '7b54f756fa2f4d5bb6d9ddfa7021670a', '00040001', '服务协同', 'svr.org', 'platform', 'menu', '', '', '', '1', '0', 'svr.org', '', 30, '1', '59015fc95f0841088d2ac243fa2aaba1', 1502671790, '0')
/* svrMenu3 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('cdf63a08850140a5bbf2e4b3dc3fc75f', '03eec58e79724923b60ae9722c302d3d', '000400010001', '企业管理', 'platform.svr.org.info', 'platform', 'menu', '/platform/svr/org/info', 'data-pjax', '', '1', '0', 'platform.svr.org.info', '', 31, '0', '59015fc95f0841088d2ac243fa2aaba1', 1502671699, '0')
/* svrMenu4 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('2c4f3c05d05e41048f4ee8e3718d9cfd', 'cdf63a08850140a5bbf2e4b3dc3fc75f', '0004000100010001', '删除企业', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.org.info.delete', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1502671679, '0')
/* svrMenu5 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('c5cbcffba35d4ff0becb301473d450f7', 'cdf63a08850140a5bbf2e4b3dc3fc75f', '0004000100010002', '企业审核', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.org.info.saveCheck', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1502671698, '0')
/* svrMenu6 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('496e2129c30144d1ba12002657d46721', '03eec58e79724923b60ae9722c302d3d', '000400010002', '服务分类', 'service.class', 'platform', 'menu', '/platform/svr/service/class', 'data-pjax', '', '1', '0', 'platform.svr.service.class', '', 33, '0', '59015fc95f0841088d2ac243fa2aaba1', 1502546106, '0')
/* svrMenu7 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('98b52ee1fcfc4ad1904876718afd7e87', '496e2129c30144d1ba12002657d46721', '0004000100020001', '新增服务分类', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.class.add', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1502546053, '0')
/* svrMenu8 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('597bb688d80842f1a44feadc7f438f4f', '496e2129c30144d1ba12002657d46721', '0004000100020002', '修改服务分类', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.class.edit', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1502546067, '0')
/* svrMenu9 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('667f54bf09334a139c481a1c1d02bfa4', '496e2129c30144d1ba12002657d46721', '0004000100020003', '删除服务分类', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.class.delete', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1502546082, '0')
/* svrMenu10 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('7ed1412f4d6d4616b2bc40263f1936ec', '496e2129c30144d1ba12002657d46721', '0004000100020004', '服务分类排序', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.class.sort', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1502546104, '0')
/* svrMenu11 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('7b773656607245918761c9e71f792564', '03eec58e79724923b60ae9722c302d3d', '000400010003', '产品管理', 'service', 'platform', 'menu', '/platform/svr/service/item', 'data-pjax', '', '1', '0', 'platform.svr.service.item', '', 34, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503051227, '0')
/* svrMenu12 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('b06581f900174cfa879bab970330f724', '7b773656607245918761c9e71f792564', '0004000100030001', '删除产品', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.item.delete', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1502671630, '0')
/* svrMenu13 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('72b669dd8dce48a581f9622feae91eb0', '7b773656607245918761c9e71f792564', '0004000100030002', '产品审核', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.item.saveCheck', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1502671646, '0')
/* svrMenu14 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('5b3323f9f1414e40a59b1c2e8d325ca5', '7b773656607245918761c9e71f792564', '0004000100030003', '新增产品', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.item.add', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1502691009, '0')
/* svrMenu15 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('13b06331433548bfb4d290e1e62b494f', '7b773656607245918761c9e71f792564', '0004000100030004', '修改产品', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.item.edit', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1502762768, '0')
/* svrMenu16 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('644f6be4ce9947b79e93276e36fc3689', '03eec58e79724923b60ae9722c302d3d', '000400010004', '订单管理', 'order', 'platform', 'menu', '/platform/svr/service/order', 'data-pjax', '', '1', '0', 'platform.svr.service.order', '', 36, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503485320, '0')
/* svrMenu17 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('fe28d50e8d724dda983ca40f20e2b5e2', '644f6be4ce9947b79e93276e36fc3689', '0004000100040001', '取消订单', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.order.closeOrder', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503051242, '0')
/* svrMenu18 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('1c23b04985a847bdb415cfb55ab8c31d', '644f6be4ce9947b79e93276e36fc3689', '0004000100040002', '恢复订单', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.order.restoreOrder', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503107812, '0')
/* svrMenu19 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('9b5688c5429441469fefc0042619e84d', '644f6be4ce9947b79e93276e36fc3689', '0004000100040003', '确认收款', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.order.confirmReceive', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503306763, '0')
/* svrMenu20 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('9fc9c836f7e54e26b9dd5ed8ae468889', '644f6be4ce9947b79e93276e36fc3689', '0004000100040004', '投诉', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.order.saveComplain', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503311116, '0')
/* svrMenu21 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('216e5f00c2d54b73a7d1fc824b913cba', '644f6be4ce9947b79e93276e36fc3689', '0004000100040005', '申请补贴', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.order.saveApplySubsidy', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503396488, '0')
/* svrMenu22 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('a897b203d9874084868f4aa8733eca3c', '644f6be4ce9947b79e93276e36fc3689', '0004000100040006', '分配任务', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.order.saveCredit', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503485317, '0')
/* svrMenu23 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('c58280117a874af0b42c86dc74eef6bf', '03eec58e79724923b60ae9722c302d3d', '000400010005', '服务机构管理', 'serciveOrg', 'platform', 'menu', '/platform/svr/service/org/info', '', '', '1', '0', 'platform.svr.service.org.info', '', 32, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131072, '0')
/* svrMenu24 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('ce281de0888e424096ab991b0abc0656', 'c58280117a874af0b42c86dc74eef6bf', '0004000100050001', '审核', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.org.info.check', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131002, '0')
/* svrMenu25 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('123ce3d49bc34eb3a8cd5de26d92fa9b', 'c58280117a874af0b42c86dc74eef6bf', '0004000100050002', '添加', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.org.info.add', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131002, '0')
/* svrMenu26 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('47171726d3544fde8d016e861f5c07ab', 'c58280117a874af0b42c86dc74eef6bf', '0004000100050003', '修改', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.org.info.edit', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131056, '0')
/* svrMenu27 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('dbe79986b6f04d07b00d11e289152c65', 'c58280117a874af0b42c86dc74eef6bf', '0004000100050004', '删除', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.org.info.del', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131070, '0')
/* svrMenu28 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('428013786fd04a9f99f2ae2c613c6441', '03eec58e79724923b60ae9722c302d3d', '000400010006', '服务活动管理', 'service.activity', 'platform', 'menu', '/platform/svr/service/activity', 'data-pjax', '', '1', '0', 'platform.svr.service.activity', '', 38, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503901128, '0')
/* svrMenu29 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('c46a5ae23c2b43ab9d5c5d7b37875899', '428013786fd04a9f99f2ae2c613c6441', '0004000100060001', '新增服务活动', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.activity.add', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131144, '0')
/* svrMenu30 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('45d2651ebbb84e55bd1580988c9a471a', '428013786fd04a9f99f2ae2c613c6441', '0004000100060002', '删除服务活动', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.activity.delete', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131163, '0')
/* svrMenu31 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('05129b84880f4ee2b6861b384c859086', '428013786fd04a9f99f2ae2c613c6441', '0004000100060003', '修改', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.activity.edit', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503901080, '0')
/* svrMenu32 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('a63b511951154cb49123d0e6bcc01e14', '428013786fd04a9f99f2ae2c613c6441', '0004000100060004', '审核', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.activity.saveCheck', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503901092, '0')
/* svrMenu33 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('bcb52bab6d5342b785b23317bf8bdcf2', '428013786fd04a9f99f2ae2c613c6441', '0004000100060005', '查看报名名单', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.activity.signup', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503901110, '0')
/* svrMenu34 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('7893986fa80248c0ab9df865d1a1f917', '428013786fd04a9f99f2ae2c613c6441', '0004000100060006', '管理员代签', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.activity.signup.sign', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503901126, '0')
/* svrMenu35 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('99c545e71ab241f8851076fb40532c93', '03eec58e79724923b60ae9722c302d3d', '000400010007', '需求管理', 'requirement', 'platform', 'menu', '/platform/svr/org/requirement', '', '', '1', '0', 'platform.svr.org.requirement', '', 35, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131256, '0')
/* svrMenu36 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('5255b3e7557a465f8ce67413ca0f9b9b', '99c545e71ab241f8851076fb40532c93', '0004000100070001', '审核', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.org.requirement.check', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131192, '0')
/* svrMenu37 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('94a0658cf75541d2ad3f20195c8698b9', '99c545e71ab241f8851076fb40532c93', '0004000100070002', '添加', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.org.requirement.add', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131192, '0')
/* svrMenu38 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('266d05041a564b828ba0d1cd2390096d', '99c545e71ab241f8851076fb40532c93', '0004000100070003', '修改', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.org.requirement.edit', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131227, '0')
/* svrMenu39 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('d64174b2efba485cb397e58e50153a5e', '99c545e71ab241f8851076fb40532c93', '0004000100070004', '删除', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.org.requirement.delete', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131254, '0')
/* svrMenu40 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('61c47b2ea2ca4119ab8d3d1e90718e9f', '03eec58e79724923b60ae9722c302d3d', '000400010008', '服务对接管理', 'serviceDocking', 'platform', 'menu', '/platform/svr/service/credit', '', '', '1', '0', 'platform.svr.service.credit', '', 37, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131326, '0')
/* svrMenu41 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('e1a5e34ba4e54f90aa2e4bd0ced1bfab', '61c47b2ea2ca4119ab8d3d1e90718e9f', '0004000100080001', '添加', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.credit.add', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131326, '0')
/* svrMenu42 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('2ca8785b247c404eaa4d09013a3ca687', '61c47b2ea2ca4119ab8d3d1e90718e9f', '0004000100080002', '修改', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.credit.edit', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131326, '0')
/* svrMenu43 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('5984c4301bf94d85892878c617ae0e5b', '61c47b2ea2ca4119ab8d3d1e90718e9f', '0004000100080003', '删除', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.credit.delete', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503131327, '0')
/* svrMenu44 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('e977e3841c7a4e87ba92dc6aff78c542', '03eec58e79724923b60ae9722c302d3d', '000400010009', '创业者管理', 'startupinfo', 'platform', 'menu', '/platform/svr/startup/info', 'data-pjax', '', '1', '0', 'platform.svr.startup.info', '', 39, '0', '59015fc95f0841088d2ac243fa2aaba1', 1503631017, '0')
/* svrMenu45 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('6e743489aed34ac4a50af8f70ca230b3', '03eec58e79724923b60ae9722c302d3d', '000400010010', '活动分类', 'ActivityClass', 'platform', 'menu', '/platform/svr/service/activity/class', '', '', '1', '0', 'platform.svr.service.activity.class', '', 40, '0', '59015fc95f0841088d2ac243fa2aaba1', 1505207384, '0')
/* svrMenu46 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('d6c6702a73ac4d8594489782cf3d6e1b', '6e743489aed34ac4a50af8f70ca230b3', '0004000100100001', '新增分类', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.activity.class.add', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1505197769, '0')
/* svrMenu47 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('4b4772533f044ac693da6157103374ff', '6e743489aed34ac4a50af8f70ca230b3', '0004000100100002', '编辑分类', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.activity.class.edit', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1505197779, '0')
/* svrMenu48 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('7be8ae612747479584d03c7be1c6050d', '6e743489aed34ac4a50af8f70ca230b3', '0004000100100003', '删除分类', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.activity.class.delete', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1505197788, '0')
/* svrMenu49 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('d20197f3f77641c4b70b1de35b3e4eb7', '6e743489aed34ac4a50af8f70ca230b3', '0004000100100004', '关联活动类别', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.class.relCountryDo', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1505207383, '0')
/* svrMenu50 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('e3a41cd4c3cc4b9fb85c7b7028a38c71', '03eec58e79724923b60ae9722c302d3d', '000400010011', '订单评价审核', 'ddpjsh', 'platform', 'menu', '/platform/svr/service/order/appraise', 'data-pjax', '', '1', '0', 'platform.svr.service.order.appraise', '', 41, '0', '3f29972c971e4628801956f0f924b720', 1505542452, '0')
/* svrMenu51 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('3e3abff4f9044bd8be4e6dd2673a051c', 'e3a41cd4c3cc4b9fb85c7b7028a38c71', '0004000100110001', '审核', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.service.order.appraise.edit', '', 0, '0', '3f29972c971e4628801956f0f924b720', 1505542451, '0')
/* svrMenu52 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('3d9055a7d3094f40b1d0da47b31b15c3', '03eec58e79724923b60ae9722c302d3d', '000400010012', '服务统计', 'fwtj', 'platform', 'menu', '/platform/svr/service/record', 'data-pjax', '', '1', '0', 'platform.svr.service.record', '', 42, '0', '392ca2ab267e447babfb2ecba0f3139a', 1506130663, '0')
/* svrMenu53 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('c2dff828cd6f446d82b76c8ba98c209d', '03eec58e79724923b60ae9722c302d3d', '000400010013', '投诉管理', 'Compalin', 'platform', 'menu', '/platform/svr/complain', '', '', '1', '0', 'platform.svr.complain', '', 123, '0', '392ca2ab267e447babfb2ecba0f3139a', 1506154550, '0')
/* svrMenu54 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('a68ee91d0844494490c917d63146dcff', 'c2dff828cd6f446d82b76c8ba98c209d', '0004000100130001', '投诉处理', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.complain.manage', '', 124, '0', '392ca2ab267e447babfb2ecba0f3139a', 1506154549, '0')
/* svrMenu55 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('a062f43b3d47431ba2873d375a84a724', '7b54f756fa2f4d5bb6d9ddfa7021670a', '00040002', '财务管理', 'cw', 'platform', 'menu', '', '', '', '1', '0', 'cw', '', 116, '1', '59015fc95f0841088d2ac243fa2aaba1', 1504574440, '0')
/* svrMenu56 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('1f469de3c2434542996a06aa7532a548', 'a062f43b3d47431ba2873d375a84a724', '000400020001', '支付款项', 'CashOut', 'platform', 'menu', '/platform/accuser/account/cashout', '', '', '1', '0', 'platform.accuser.account.cashout', '', 118, '0', '59015fc95f0841088d2ac243fa2aaba1', 1504574766, '0')
/* svrMenu57 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('d178f93f37134d6097cd217289c30e65', '1f469de3c2434542996a06aa7532a548', '0004000200010001', '上传打款凭证', '', 'platform', 'data', '', '', '', '0', '0', 'platform.accuser.account.cashou.saveApply', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1504574750, '0')
/* svrMenu58 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('28c269f786df4a88a206f12377c30586', '1f469de3c2434542996a06aa7532a548', '0004000200010002', '审核提现', '', 'platform', 'data', '', '', '', '0', '0', 'platform.accuser.account.cashout.edit', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1504574764, '0')
/* svrMenu59 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('2b705b2a6a804da5b7cfe951c08fe8f7', 'a062f43b3d47431ba2873d375a84a724', '000400020002', '历史提现记录', 'His Cashout', 'platform', 'menu', '/platform/accuser/account/cashout/his', '', '', '1', '0', 'platform.accuser.account.cashout.his', '', 119, '0', '59015fc95f0841088d2ac243fa2aaba1', 1504574817, '0')
/* svrMenu60 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('0e0543b5aa6743fd98cc5ffbf0537451', '7b54f756fa2f4d5bb6d9ddfa7021670a', '00040004', '专家文章', 'Expert Content', 'platform', 'menu', '', '', '', '1', '0', 'expert.content', '', 120, '1', '392ca2ab267e447babfb2ecba0f3139a', 1506130269, '0')
/* svrMenu61 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('b2ba5c7b977b41a7bdeeac12d1874aaf', '0e0543b5aa6743fd98cc5ffbf0537451', '000400040001', '专家文章审核', 'ContentCheck', 'platform', 'menu', '/platform/svr/expert/content', '', '', '1', '0', 'platform.svr.expert.content', '', 121, '0', '392ca2ab267e447babfb2ecba0f3139a', 1506130662, '0')
/* svrMenu62 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('61e8c90d1d844c9b89828c69de1d3f22', '', '0007', '咨询交流', 'zxjl', 'platform', 'menu', '', '', '', '1', '0', 'expert', '', 3, '1', '4c42ee2c63e54df6a27bbe222991b6a6', 1504575852, '0')
/* svrMenu63 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('2d70c6f3d6e8489e8f27a09250390af9', '61e8c90d1d844c9b89828c69de1d3f22', '00070001', '咨询交流管理', 'zxjlgl', 'platform', 'menu', '', '', '', '1', '0', 'expert.manager', '', 44, '0', '4c42ee2c63e54df6a27bbe222991b6a6', 1504578203, '0')
/* svrMenu64 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('5a1537ca4e2041bcb8570add340d3f59', '2d70c6f3d6e8489e8f27a09250390af9', '000700010001', '咨询专家管理', 'expert', 'platform', 'menu', '/platform/svr/expert/info', 'data-pjax', '', '1', '0', 'platform.svr.expert.info', '', 45, '0', '4c42ee2c63e54df6a27bbe222991b6a6', 1504576081, '0')
/* svrMenu65 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('099a8403038f45dfbd5ffac75929e54f', '5a1537ca4e2041bcb8570add340d3f59', '0007000100010001', '添加', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.expert.info.add', '', 0, '0', '4c42ee2c63e54df6a27bbe222991b6a6', 1504576053, '0')
/* svrMenu66 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('962663ba87e1406392ac4c6ff5d09c74', '5a1537ca4e2041bcb8570add340d3f59', '0007000100010002', '审核', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.expert.info.check', '', 0, '0', '4c42ee2c63e54df6a27bbe222991b6a6', 1504576081, '0')
/* svrMenu67 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('a8584cc7295b4e2f8d8f0b3e15ec7c56', '2d70c6f3d6e8489e8f27a09250390af9', '000700010002', '咨询单管理', 'zxdgl', 'platform', 'menu', '/platform/svr/expert/order', 'data-pjax', '', '1', '0', 'platform.svr.expert.order', '', 46, '0', '4c42ee2c63e54df6a27bbe222991b6a6', 1504576276, '0')
/* svrMenu68 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('d298e1835a844cc58a2c9de978bd80d5', '2d70c6f3d6e8489e8f27a09250390af9', '000700010004', '咨询评价审核', 'CheckAppraise', 'platform', 'menu', '/platform/svr/expert/order/appraise', '', '', '1', '0', 'platform.svr.expert.order.appraise', '', 47, '0', '59015fc95f0841088d2ac243fa2aaba1', 1505444038, '0')
/* svrMenu69 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('d124be6f488b4673aa08776f80c6f2ad', 'd298e1835a844cc58a2c9de978bd80d5', '0007000100040001', '评价审核', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.expert.order.appraise.saveCheck', '', 0, '0', '59015fc95f0841088d2ac243fa2aaba1', 1505444037, '0')
/* svrMenu70 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('21f19c2ed53a4ee9988564761c0d3ec7', 'b2ba5c7b977b41a7bdeeac12d1874aaf', '0004000400010001', '专家文章审核', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.expert.content.edit', '', 122, '0', '06ed2795b1344e38aa5b8fd8b93ba3f2', 1507687673, '0')
/* svrMenu71 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('8fae08a6bace46dfbf55b7a23826fb76', '64e1d25a73ed4e50a170d585491f359d', '0004000100140004', '服务需求排序', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.org.requirement.class.sort', '', 494, '0', '392ca2ab267e447babfb2ecba0f3139a', 1507702920, '0')
/* svrMenu72 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('64e1d25a73ed4e50a170d585491f359d', '03eec58e79724923b60ae9722c302d3d', '000400010014', '需求分类', 'RequirementClass', 'platform', 'menu', '/platform/svr/org/requirement/class', '', '', '1', '0', 'platform.svr.org.requirement.class', '', 490, '0', '392ca2ab267e447babfb2ecba0f3139a', 1507702920, '0')
/* svrMenu73 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('a5704f6a318c4a57869bb6a2a6ae66c3', '64e1d25a73ed4e50a170d585491f359d', '0004000100140003', '删除需求分类', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.org.requirement.class.delete', '', 493, '0', '392ca2ab267e447babfb2ecba0f3139a', 1507690450, '0')
/* svrMenu74 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('677b6ab2f9c04297aeaa060a0e48598d', '64e1d25a73ed4e50a170d585491f359d', '0004000100140002', '编辑需求分类', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.org.requirement.class.edit', '', 492, '0', '392ca2ab267e447babfb2ecba0f3139a', 1507690435, '0')
/* svrMenu75 */
insert into sys_menu (ID, PARENTID, PATH, NAME, ALIASNAME, SYSTEM, TYPE, HREF, TARGET, ICON, ISSHOW, DISABLED, PERMISSION, NOTE, LOCATION, HASCHILDREN, OPBY, OPAT, DELFLAG)
values ('30a4e5a1ab3c45128613c391548f46be', '64e1d25a73ed4e50a170d585491f359d', '0004000100140001', '新增需求分类', '', 'platform', 'data', '', '', '', '0', '0', 'platform.svr.org.requirement.class.add', '', 491, '0', '392ca2ab267e447babfb2ecba0f3139a', 1507690406, '0')