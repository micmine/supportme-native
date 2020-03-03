-------------------------------------------------------
-- Add groups
-------------------------------------------------------
insert into persongroup (name, personscanseeothers, personscanseegroup) values 
    ('user', false, false);

insert into persongroup (name, personscanseeothers, personscanseegroup) values 
    ('level-1', false, false);

insert into persongroup (name, personscanseeothers, personscanseegroup) values 
    ('level-2', false, false);


insert into template (name, value) values ('support-level-2', 'Support level chaged to level 2');
insert into form (kind, name, value) values (1, 'usedata', 'Can you reproduce this error');
insert into formfield (formid, value) values (1, 'yes');
insert into formfield (formid, value) values (1, 'No');
-------------------------------------------------------
-- New user
-------------------------------------------------------
-- (select id from person where name = 'micmine')
-- Add new person
insert into person (name,email) values
    ('micmine', 'micmine4@gmail.com');                                                                                     
insert into personinformation (id, ip) values
    (1 , '178.197.224.186');                      
insert into personcredentials (id, password) values
    (1 , 'someEncyptedPassword');                      

-- (select id from persongroup where name = 'user')
-- Add person in persongroup
insert into persontogroup (personid, groupid) values 
    (1, 1);

-- (select id from chat  where name = 'micmine')
-- Add Chat
insert into chat (name) values ('micmine');

-- Add user to chat
insert into entitytochat (personid, chatid) values 
    (1, 1);

-- Add level-1 to chat
insert into entitytochat (persongroupid, chatid) values 
    (2, 1);

-------------------------------------------------------
-- User write messaage and level-2 to support
-------------------------------------------------------
-- User writes message
insert into message (chatid, personid, value, time) values 
    (1,1,'Hallo i have a problem.\n On the Homeage apears \"databse not found\" ', now());

-- Add level-2 to chat
insert into entitytochat (persongroupid, chatid) values 
    (3, 1);

-- Send template
insert into message (chatid, personid, templateid, time) values (1,1,1, now());

-- Send form
insert into message (chatid, personid, formid, time) values (1,1,1, now());