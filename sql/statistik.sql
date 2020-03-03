-- get messages sins date all users
select person.name, count(*) as count from message 
  inner join person on message.personid = person.id 
  where time > '2019-12-1' 
  group by person.name;

-- get messages sins date group
select persongroup.name, count(*) as count from message 
  inner join person on message.personid = person.id
  inner join persontogroup on person.id = persontogroup.personid
  inner join persongroup on persontogroup.groupid = persongroup.id
  where time > '2019-12-1' 
  group by persongroup.name;

-- number off template uses
-- number off form uses

-- who uses the most template
-- who uses the most form

-- afrige nachrichtgrösse nach user
select person.name, avg(length(message.value)) from message 
  inner join person on message.personid = person.id
  group by person.name
  order by avg desc;
