create table M_ACTION
(
  actionsn    NUMBER(12) PRIMARY KEY ,
  actiontype  NUMBER(1) not null,
  actiontime  NUMBER(14),
  merid       VARCHAR2(12) not null,
  batchno     NUMBER(12),
  orderno     NUMBER(12) not null,
  tranamt     NUMBER(12),
  rspcode     VARCHAR2(6),
  rspdesc     VARCHAR2(128),
  isagreement NUMBER(1) not null,
  ip          VARCHAR2(40),
  msgid       VARCHAR2(32),
  issuccess   NUMBER(1),
  isrefund    NUMBER(1)
);

-- Create sequence
create sequence SEQ_DEFAULT
minvalue 100000000000
maxvalue 999999999999
start with 100015058509
increment by 1
cache 1000;