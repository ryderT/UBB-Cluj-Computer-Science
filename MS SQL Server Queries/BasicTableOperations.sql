use Lab2


select * from Students
--1
alter table Students
alter column Name smallint

alter table Students
alter column Name varchar(50)

--2
alter table Students
add PhoneNumber varchar(11)

alter table Students
drop column PhoneNumber

--3
alter table Students
add GroupID int

alter table Students
add constraint def_Students default 921 for GroupID
				--the name of the constraint

alter table Students
drop constraint def_Students

--4

create table Grades(
Gid int not null,
Grade int,
constraint pk_Grades primary key(Gid)
)
alter table Grades
drop constraint pk_Grades

--5
alter table Students
add constraint uk_Students unique(PhoneNumber)

alter table Students
drop constraint uk_Students

--6
alter table Grades
add Sid int not null

alter table Grades
add constraint fk_Grades foreign key(Sid) references Students(Sid)

alter table Grades
drop constraint fk_Grades
alter table Grades
drop column Sid

--7

create table Cafetaria(
Cid int primary key identity,
CName varchar(40)
)

drop table Cafetaria

