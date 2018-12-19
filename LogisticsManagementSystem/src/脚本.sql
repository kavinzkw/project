--不同水果在不同月份的不同销量
create table fruit(fname varchar2(50),one1 number,two1 number,three number,four number,five number,six number,seven number,eight number,nine number,ten number,eleven number,twlen number);
insert into fruit values('香蕉',10,12,16,19,17,18,16,15,20,22,24,20);
insert into fruit values('苹果',14,15,16,20,22,24,22,20,19,18,19,18);
insert into fruit values('猕猴桃',20,21,22,25,20,18,16,15,14,12,13,15);
commit;

select * from shopdata
