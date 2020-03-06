use Lab3

-------------------------------------------------------------------------------Inserts----------------------------------------------------------------------/

--The Boss
insert into MafiaBosses(Bid,Name,Surname,Age)
values (1,'Al','Capone',55)

insert into MafiaBosses(Bid,Name,Surname,Age)
values (2,'Alonso','Caponelli',22)

--select * from MafiaBosses

--PK constraint
insert into MafiaBosses(Bid,Name,Surname,Age)
values (1,'Fake','Boss',69)


-- His Hats
insert	into BossHats(BHid,Brand,Material,Inherited,SwagLevel,Bid)
values(1,'Gucci','Leather',0,9000,1)

insert	into BossHats(BHid,Brand,Material,Inherited,SwagLevel,Bid)
values(2,'Borsalino','Premium Silk',1,10000,1)


--FK constraint
insert	into BossHats(BHid,Brand,Material,Inherited,SwagLevel,Bid)
values(3,'Fake Gucci','Synthetic Leather',0,9000,2)

insert	into BossHats(BHid,Brand,Material,Inherited,SwagLevel,Bid)
values(4,'Borsalino','Premium Leather',1,10001,1)

select * from BossHats


--Consiglieres


insert into Consiglieres(Cid,Name,Surname,Age)
values(1,'Democrito','Crimi',65)
--select * from Consiglieres

--UnderBosses
insert into Underbosses(Uid,Name,Surname,Age)
values(1,'Graciliano', 'Di Lallo',65)

insert into Underbosses(Uid,Name,Surname,Age)
values(2,'Rufino','Micucci',22)

select * from BossHats


--Capos

insert into Capos(Cpid,Name,Surname,Age,Uid)
values(1,'Cristoforo', 'Corda',45,1)

insert into Capos(Cpid,Name,Surname,Age,Uid)
values(2,'Albano','Sparacio',38,1)

delete from Capos where Cpid=1 or Cpid=2

select * from Capos





--Soldiers

--Identity insert error
insert into Soldiers(Sid,Name,Surname,Age,Rank)
values(1,'Davino', 'Governale',34,9)


insert into Soldiers(Name,Surname,Age,Rank)
values('Davino', 'Governale',34,9)

--Check constraint error
insert into Soldiers(Name,Surname,Age,Rank)
values('Loris', 'Sodano',37,2)

insert into Soldiers(Name,Surname,Age,Rank)
values('Loris', 'Sodano',37,5)

insert into Soldiers(Name,Surname,Age,Rank)
values('Otello','Pollino',39,8)

select * from Soldiers



--CapoSoldiers


insert into CaposSoldiers(Cpid,Sid,GroupName)
values(1,1,'Alfa')
insert into CaposSoldiers(Cpid,Sid,GroupName)
values(1,3,'Alfa')
insert into CaposSoldiers(Cpid,Sid,GroupName)
values(2,4,'Beta')

select * from CaposSoldiers



--Some updates/deletes

select * from MafiaBosses
select * from BossHats
select * from Underbosses
select * from Consiglieres

delete from BossHats where Bid=2
delete from Underbosses where Uid=2 or Uid>2
delete from Consiglieres where Cid>1
delete from MafiaBosses where Name like 'Alonso'

update BossHats
set SwagLevel=SwagLevel/10
where SwagLevel between 1000 and 100000
update MafiaBosses
set Age=99
where Age is NULL
update BossHats
set Inherited = 1
where Inherited is not NULL
update Underbosses
set Age=Age-12
where Age is not null and Age > 40


	
-----------------------------------------------------------------------------------A-------------------------------------------------------------------------/

select * from MafiaBosses
select * from BossHats
select * from Underbosses
select * from Consiglieres
--all hats gucci, all hats leather
select BH1.Brand from BossHats BH1 where BH1.Brand='Gucci'
union
select BH2.Material from BossHats BH2 where BH2.Material='Leather'
--all hats gucci or all hats swag>901
select * from BossHats where Brand = 'Gucci' or SwagLevel > 901


-----------------------------------------------------------------------------------B-------------------------------------------------------------------------/
select * from Capos
select * from CaposSoldiers
--all Capos that are in Group Beta
select * from Capos where Cpid in
(select Cpid from CaposSoldiers where GroupName='Beta')

--The capo id whose name is Cristoforo and is in Alfa
select Cpid from Capos where Name like 'Cristoforo'
intersect
select Cpid from CaposSoldiers where CaposSoldiers.GroupName like 'Alfa'


-----------------------------------------------------------------------------------C-------------------------------------------------------------------------/

select * from Soldiers

select s1.Name,s1.Surname from Soldiers s1
except
select s2.Name,s2.Surname from Soldiers s2
where s2.Age < 35

select * from Soldiers 
where Name  in (select Name from Soldiers where Surname not like 'Sodano')
order by Rank


-----------------------------------------------------------------------------------D-------------------------------------------------------------------------/
select * from Capos
select * from CaposSoldiers

-- soldiers in which group with what boss
select Soldiers.Name, Soldiers.Surname, CaposSoldiers.GroupName, Capos.Name
from CaposSoldiers 
inner join Soldiers on CaposSoldiers.Sid=Soldiers.Sid
inner join Capos on Capos.Cpid=CaposSoldiers.Cpid

-- some more inseration needed for many to many -> many to many
insert into Guns(Gid,GunType,Model,Ammo)
values(1,'Tommy','Mk1',3550)

insert into Guns(Gid,GunType,Model,Ammo)
values(2,'Shotgun','Mk6',880)

insert into SoldierGuns(Sid,Gid,DaysOfOwning,Reason)
values(1,2,'12-Aug-95','Protection')
insert into SoldierGuns(Sid,Gid,DaysOfOwning,Reason)
values(1,1,'12-Aug-95','Assasination')
select * from SoldierGuns
--------------
-- many to many thngy
-- Soldier which belongs to a group, governed by a Capo using a gun for what reason smh
select Soldiers.Name as Soldier,CaposSoldiers.GroupName,Capos.Name as Capo,Guns.GunType,SoldierGuns.Reason
from CaposSoldiers
inner join Soldiers on Soldiers.Sid=CaposSoldiers.Sid
inner join Capos on Capos.Cpid=CaposSoldiers.Cpid
inner join SoldierGuns on SoldierGuns.Sid=Soldiers.Sid
inner join Guns on Guns.Gid=SoldierGuns.Gid

--all soldiers that have a gun
select * from Soldiers
right outer join SoldierGuns on Soldiers.Sid=SoldierGuns.Sid
-- hats owned by the boss
select * from BossHats
left outer join MafiaBosses on BossHats.Bid=MafiaBosses.Bid
-- All soldier guns rented
select * from Guns
full outer join SoldierGuns on SoldierGuns.Gid=Guns.Gid



-----------------------------------------------------------------------------------E-------------------------------------------------------------------------/
--all capos that have underlings rank >7
select * from Capos where Capos.Cpid in
(select CaposSoldiers.Cpid from CaposSoldiers where CaposSoldiers.Sid in
(select Soldiers.Sid from Soldiers where Soldiers.rank>7))

select * from Guns
where Guns.Gid in (select SoldierGuns.Gid from SoldierGuns
where SoldierGuns.Reason like 'Protection')


-----------------------------------------------------------------------------------F-------------------------------------------------------------------------/


select * from MafiaBosses where exists
(select * from BossHats where BossHats.Bid=MafiaBosses.Bid and BossHats.Inherited = 1 and BossHats.SwagLevel>100)

select * from Capos where exists
(select * from CaposSoldiers where Capos.Cpid=CaposSoldiers.Cpid and CaposSoldiers.GroupName like 'Beta')


-----------------------------------------------------------------------------------G-------------------------------------------------------------------------/

select * from Guns
select * from
(select * from Guns where Guns.GunType like 'Shotgun' and Guns.Ammo > 500)
as gun
where gun.Model like 'Mk6'

select * from
(select * from BossHats where BossHats.Material like 'Premium Leather') as bh
where bh.Inherited = 1

-----------------------------------------------------------------------------------H-------------------------------------------------------------------------/
--all soldiers having more than 1 gun
select count(Gid) as numberOfGuns,Sid as SoldierID
from SoldierGuns
group by Sid
having count(Gid)>1

--total swag level
select Bid as BossID,count(*) as numberOfHats,sum(SwagLevel) as totalSwagLevel
from BossHats
group by Bid

select Sid as SoldierID,min(Ammo) as minAmmo,max(Ammo) as maxAmmo,avg(Ammo) as average
from Guns,SoldierGuns
group by Sid
having (select Soldiers.Age from Soldiers where Soldiers.Age=39) >10

select * from Underbosses

select count(Uid) as UnderbossID
from Underbosses
group by Underbosses.Name
having(select Underbosses.Age from UnderBosses where Underbosses.Surname like 'Di Lallo') <60


-----------------------------------------------------------------------------------I-------------------------------------------------------------------------/

select * from Guns
where Guns.Ammo > any(select Guns.Ammo from Guns where Guns.GunType  not like 'Tommy')

select * from Guns
where Guns.Ammo > (select min(Guns.Ammo) from Guns where Guns.GunType  not like 'Tommy')



select * from BossHats
where BossHats.SwagLevel > any(select BossHats.SwagLevel from BossHats where BossHats.Brand like 'Gucci')

select * from BossHats
where BossHats.SwagLevel > (select max(BossHats.SwagLevel) from BossHats where BossHats.Brand like 'Gucci')



select * from Soldiers
where Soldiers.name <> all(select MafiaBosses.name from MafiaBosses)

select * from Soldiers
where Soldiers.name not like(select MafiaBosses.name from MafiaBosses)



select * from Capos
where Capos.Age <> all (select Capos.Age from Capos where Capos.Name like 'Albano')

select * from Capos
where Capos.Age not like (select Capos.Age from Capos where Capos.Name like 'Albano')