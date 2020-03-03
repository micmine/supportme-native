-- Add new person
insert into person (name,email) values ('micmine', 'micmine4@gmail.com');
select id from person where name = 'micmine'; 

-- Set personinformation ip
insert into personinformation (id, ip) values (1, '178.197.224.186');

-- Change user Password
insert into personcredentials (id, password) values (1, 'someEncyptedPassword');

-- Create Group
insert into persongroup (name, personscanseeothers, personscanseegroup) values ('user', false, false);

-- Add person in persongroup
insert into persontogroup (personid, groupid) values (1, 1);

-- Add language
insert into language (short, value) values ("en", "englisch");

-- Add chat
insert into chat (name) values ('micmine');

-- Add person to chat
insert into entitytochat (personid, chatid) values (1, 1);

-- Add level1 to chat
insert into entitytochat (persongroupid, chatid) values (2, 1);

-- Add temlate 
insert into temlate (name, value) values ('Begrussung', 'Gutten Tag \n\n');

-- Add form
insert into form (kind, name, value) values (1, 'usedata', 'Can we use your data ?');
insert into formfield (formid, value) values (1, 'yes');
insert into formfield (formid, value) values (1, 'No');

-- Write own message
insert into message (chatid, personid, value, time) values (1,1,'Hallo', now());

-- Write temlate message
insert into message (chatid, personid, templateid, time) values (1,1,1, now());

-- Write form message
insert into message (chatid, personid, formid, time) values (1,1,1, now());

