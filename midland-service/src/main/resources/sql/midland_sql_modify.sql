
  ALTER table user MODIFY `audit_status` int(11) DEFAULT 0 COMMENT '审核状态';
  ALTER table user MODIFY `is_black` varchar(255) DEFAULT 0 COMMENT '是否加入黑名单';
  ALTER table user add `delete_flag` int(2) DEFAULT 0 COMMENT '0未删除，1删除';
  alter table appointment CHANGE CALL nick_name int;
