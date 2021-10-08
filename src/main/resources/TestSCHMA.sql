drop table if exists ships cascade;
create table ships (id bigint not null auto_increment, 
level integer not null,
limit_break varchar(255) not null,
rarity varchar(16) not null,
ship_name varchar(30) not null,
primary key (id));