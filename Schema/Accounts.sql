create database simpleSecondBrain; 

use simpleSecondBrain;

drop table if exists accounts;
create table accounts(
	id int unsigned not null auto_increment,
    user_name varchar(30) not null,
	user_password varchar(255) not null,
    acc_name varchar(80) not null,
    email varchar(50) not null,
    primary key (id),
    unique key (user_name),
    unique key (email)
);

insert into accounts (user_name, user_password, acc_name, email)
values ('tester01',sha2('test01', 256), 'tester_account', 'test01@gmail.com');

select * from accounts where user_password = sha2("test01", 256);
select * from accounts where user_password = sha2("test01", 256) and
user_name = 'tester01';
select * from accounts;

delete from accounts;