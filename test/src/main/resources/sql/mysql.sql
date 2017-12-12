create table IF NOT EXISTS M_ACTION
(
  actionsn    INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  actiontype  INT UNSIGNED not null,
  actiontime  BIGINT,
  merid       VARCHAR(12) not null,
  batchno     INT UNSIGNED,
  orderno     INT UNSIGNED not null,
  tranamt     INT UNSIGNED,
  rspcode     VARCHAR(6),
  rspdesc     VARCHAR(128),
  isagreement INT UNSIGNED not null,
  ip          VARCHAR(40),
  msgid       VARCHAR(32),
  issuccess   INT UNSIGNED,
  isrefund    INT UNSIGNED
)ENGINE=InnoDB DEFAULT CHARSET=utf8;