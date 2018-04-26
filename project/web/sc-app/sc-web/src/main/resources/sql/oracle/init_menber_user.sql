-- 初始化 服务机构、企业、专家、创业者帐号 cms_model jxx


insert  into sc_account_link
(id,account_id,type,table_id,basic,loginTheme,loginSidebar,loginBoxed,loginScroll,loginPjax,opBy,opAt,delFlag) values
('1','1',1,'1',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into sc_account_link
(id,account_id,type,table_id,basic,loginTheme,loginSidebar,loginBoxed,loginScroll,loginPjax,opBy,opAt,delFlag) values
('201709000000002','201709000000002',4,'0098',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
insert  into sc_account_link
(id,account_id,type,table_id,basic,loginTheme,loginSidebar,loginBoxed,loginScroll,loginPjax,opBy,opAt,delFlag) values
('2017090000000003','2',2,'1',NULL,NULL,0,0,0,0,'59015fc95f0841088d2ac243fa2aaba1',1505731123,0);
insert  into sc_account_link
(id,account_id,type,table_id,basic,loginTheme,loginSidebar,loginBoxed,loginScroll,loginPjax,opBy,opAt,delFlag) values
('23232','2017080000000013',3,'001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);


insert  into sc_account_user
(id,loginname,nickname,password,salt,mobile,email,disabled,province,city,area,address,login_num,last_ip,last_time,opBy,opAt,delFlag,duties) values
('1','qy','企业','smFL8sn8K6a3YWb4dAVek9TevKKJcW7dkDnzlwAqnl8=','KWhjT0y1kOOFs6Sws6gd4A==','22222','222@qq.com',0,NULL,NULL,NULL,NULL,1,'0:0:0:0:0:0:0:1','1503985857','',1503985834,0,NULL);
insert  into sc_account_user
(id,loginname,nickname,password,salt,mobile,email,disabled,province,city,area,address,login_num,last_ip,last_time,opBy,opAt,delFlag,duties) values
('201709000000002','zj','专家中心','VnKJ8B9Hw+JCRH18WT7HwgPiVK9k49zk0DhrLOonqow=','QLaq7PUzyG64tMEEvxpY1A==','15656543432',NULL,0,NULL,NULL,NULL,NULL,16,'0:0:0:0:0:0:0:1','1504768129',NULL,NULL,0,NULL);
insert  into sc_account_user
(id,loginname,nickname,password,salt,mobile,email,disabled,province,city,area,address,login_num,last_ip,last_time,opBy,opAt,delFlag,duties) values
('2','cyz','创业者','VnKJ8B9Hw+JCRH18WT7HwgPiVK9k49zk0DhrLOonqow=','QLaq7PUzyG64tMEEvxpY1A==',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL);
insert  into sc_account_user
(id,loginname,nickname,password,salt,mobile,email,disabled,province,city,area,address,login_num,last_ip,last_time,opBy,opAt,delFlag,duties) values
('2017080000000013','fwjg','服务机构','ikJsuNmkRw0LhtEfkF7O9xksySQFNxR79pai0v6DgJY=','QLaq7PUzyG64tMEEvxpY1A==','13111111111','11@qq.com',0,NULL,NULL,NULL,'4',1351,'0:0:0:0:0:0:0:1','1505918076','1503394347',0,NULL,NULL);


insert  into sc_startup_info(id,type,name,linkman,tel,email,address,city,area,remark,opBy,opAt,delFlag,account_id,check_status,province) values
('1','d0280f23fc2b4760b4f27307f1702b26','韩寒创业者','韩寒创业者','15656545434','4124@qq.com','合肥市蜀山区长江西路100号','350300','350304','<p><span style=\"color: rgb(89, 89, 90); font-family: &quot;microsoft yahei&quot;, 宋体, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 13px; font-weight: 600; text-align: right; background-color: rgb(255, 255, 255);\">备注</span></p>','2',1504832840,0,'1',1,'350000');

insert  into sc_org_info(id,org_name,org_property,industry_code1,org_code,legal_person,register_type,province,city,area_county,linkman,tel,fax,email,website,address,postcode,industry_code,main_remark,share_type,opened_time,photo,location,check_status,opBy,opAt,delFlag,register_type1,account_id,signed,checked,total,point,point_avg) values
('1','和信科技','bbb3b5880b314b939d6c569580e7752f','1c339549bc474925b82dde36304751ee','23334-X','韩寒','aa04c80826624c088d3ddfff747b6a9e','110000','110100','110101','张思宁1','15656523482','236600','eee@qq.com','http://baidud.com.cn','安徽省合肥市政务区祁门路9999号中小企业服务大厅','236600','05a7120a965b41ee87d5f577ef6c9d0e','<p>广播、电视、电影和影视录音制作业</p>','','2017-08','/open/file/download/5bqmae55oih02r8k2fqcon1524',1,1,'1',1504767260,0,'cbe9e86f6cf14a3d889bffceb8c9b2a5','1','0',NULL,NULL,NULL,NULL);

insert  into sc_expert_info
(id,name,expertin,note,total_num,total_point,avg_point,org_name,org_code,tel,titles,email,education,province,city,county,address,remark,head,status,apply_time,check_reason,opBy,opAt,delFlag,industry_code,industry_code1) values
('0098','韩专家你好','擅长领域','<p>sdfsdf</p>',NULL,NULL,NULL,NULL,NULL,'15656543221','职称','www','be5e95187b064523b675a6b3b81c4d46','340000','341200','341222','通讯地址','<p>eeeee</p>','/open/file/download/r9ov8291ech9porkut36imo191',0,1504768996,'','201709000000002',1504768996,0,'7cb08b0601c34b6baf4b90af53dabce3','2a34f19a8d8d4adaa593dc083eba543e');

insert  into sc_service_org_info(id,org_property,org_code,org_name,legal_person,register_type,province,city,area_county,linkman,tel,fax,email,website,address,postcode,industry_code,industry_code1,main_remark,share_type,opened_time,register_asset,qualification,remark,personnel,photo,org_grade,url,check_status,signed,reg_at,check_at,total,point,money,freeze_money,service_level,location,opBy,opAt,delFlag) values
('001','8f3fa74b68034017810d772f9734543e','3453','34245','345345','f6c462b2022e4f3fbdade1a64ceed310','340000','340200','340203','345','是','234234','45345','345','34534','5345','7cb08b0601c34b6baf4b90af53dabce3','2a34f19a8d8d4adaa593dc083eba543e','<p><span style=\"color: rgb(89, 89, 90); font-family: &quot;microsoft yahei&quot;, 宋体, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 13px; font-weight: 600; text-align: right; background-color: rgb(255, 255, 255);\">主要服务内容和特色</span></p>','9b94a426ed3a4d74b1919bded18f62cd','2017-09',3000000,'<p><span style=\"color: rgb(89, 89, 90); font-family: &quot;microsoft yahei&quot;, 宋体, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 13px; font-weight: 600; text-align: right; background-color: rgb(255, 255, 255);\">专业资质情况</span></p>','<p><span style=\"color: rgb(89, 89, 90); font-family: &quot;microsoft yahei&quot;, 宋体, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 13px; font-weight: 600; text-align: right; background-color: rgb(255, 255, 255);\">主要服务设备及条件</span></p>','<p><label for=\"email\" class=\"col-sm-2 control-label\" style=\"box-sizing: border-box; display: inline-block; max-width: 100%; margin-bottom: 0px; font-weight: 600; position: inherit; min-height: 1px; padding-right: 7.5px; padding-left: 7.5px; float: left; width: 158.047px; padding-top: 7px; text-align: right;\">人员素质构成</label></p><p>自定义标题</p><p>段落格式</p><p>字体</p><p>字号</p><p><iframe style=\"box-sizing: border-box; display: block; width: 20px; height: 20px; overflow: hidden; border-width: 0px; border-style: initial; margin: 0px; padding: 0px; position: absolute; top: 0px; left: 0px; opacity: 0; cursor: pointer;\"></iframe></p><p><br/></p>','/open/file/download/qs5oks8tpai8qrhbmufga7a7n3','8a82049171f44e5baf7635afefe28f56',NULL,1,0,NULL,NULL,NULL,NULL,33,0,NULL,NULL,'2017080000000013',1505916953,0);


insert into SC_SERVICE_ROLE (ID, NAME, CODE, DISABLED, DEFAULTVALUE, NOTE, OPBY, OPAT, DELFLAG,TYPE)
values ('2017090000000004', '服务机构角色', 't0lomimj1kg06ohdlvi1boferc', '0', '1', null, '76862304c43e4e75a0defb7bcd9424af', 1505986455, '0','1');
insert into SC_SERVICE_ROLE (ID, NAME, CODE, DISABLED, DEFAULTVALUE, NOTE, OPBY, OPAT, DELFLAG,TYPE)
values ('2017090000000005', '专家角色', '64bhkb4uimilaq5ku13e0ra9gd', '0', '0', null, '76862304c43e4e75a0defb7bcd9424af', 1505986446, '0','0');

insert into SC_SERVICE_USER_ROLE (ROLEID, LINKID)
values ('2017090000000004', '23232');
insert into SC_SERVICE_USER_ROLE (ROLEID, LINKID)
values ('2017090000000005', '201709000000002');
values ('2017090000000005', '201709000000002');