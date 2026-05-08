create database subscription_db;
Use subscription_db;

create table roles(
	id bigint primary key auto_increment,
    name varchar(50)
);
drop table role;
create table users(
	id bigint primary key auto_increment,
    name varchar(100),
    email varchar(100) unique,
    role_id bigint,
    foreign key (role_id) references roles(id)
);

create table plans(
	id bigint primary key auto_increment,
    name varchar(100),
    price double,
    duration_days int
);

create table subscriptions(
	id bigint primary key auto_increment,
    user_id bigint,
    plan_id bigint,
    start_date date,
    end_date date,
    status varchar(20),
    foreign key (user_id) references users(id),
    foreign key (plan_id) references plans(id)
);

create table payments(
	id bigint primary key auto_increment,
    subscription_id bigint,
    amount double,
    payment_date date,
    status varchar(20),
    foreign key(subscription_id) references subscriptions(id)
);
UPDATE plans
SET duration_days = 220
WHERE id = 2;
show tables;
select * from roles;
select * from users;
select * from payments;
select * from plans;
select * from subscriptions;