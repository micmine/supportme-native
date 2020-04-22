create database supportme;

\c supportme

create user supportme with encrypted password 'pass';
grant all privileges on database supportme to supportme;

create table person(
	id serial primary key,
	name varchar(50) not null,
	email varchar unique not null
);

create table language(
	id serial primary key,
	short varchar(3) not null,
	value varchar(10) not null
);

create table personinformation(
	id serial not null references person(id),
	client varchar(50),
	ip varchar(31),
	language integer references language(id)
);

create table personcredentials(
	id serial not null references person(id),
	password varchar(500)
);

create table persongroup(
	id serial primary key,
	name varchar(50) not null,
	supportlevel integer,
	personscanseeothers boolean,
	personscanseegroup boolean
);

create table grouppersmission(
	groupid serial not null references persongroup(id),
	permissionname varchar (100) not null
);

create table persontogroup(
	personid integer not null references person(id),
	groupid integer not null references persongroup(id)
);

create table chat(
	id serial primary key,
	name varchar not null
);

create table entitytochat(
	personid serial,
	persongroupid serial,
	chatid integer not null references chat(id)
);

create table template(
	id serial primary key,
	name varchar,
	value varchar
);

create table form(
	id serial primary key,
	kind integer not null,
	name varchar not null,
	value varchar not null
);

create table formfield(
	id serial primary key,
	formid integer not null references form(id),
	value varchar
);

create table message(
	id serial primary key,
	chatid integer not null references chat(id),
	personid integer not null references person(id),
	templateid integer references template(id),
	formid integer references form(id),
	value varchar,
	time date not null
);
