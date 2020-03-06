use Lab3
go

select * from Views


select * from CaposSoldiers
select * from Capos
select * from Underbosses
select * from MafiaBosses

create view View1 as
	select Name from Underbosses
go

select * from View1


create view View2 as
	select c.Surname,u.Name from Capos c inner join Underbosses u on c.Uid=u.Uid
go

select * from View2

---how many soldiers belong to a capo
create view View3 as
	select Cs.Cpid as CapoId, count(GroupName) as NumberOfSoldiers from CaposSoldiers Cs
	inner join
	Capos c on c.Cpid=Cs.Cpid
	group by Cs.Cpid

go

select * from View3

insert into Views values ('View1') -- view with 1 table
insert into Views values ('View2') -- view with 2 tables
insert into Views values ('View3') -- view with 2 tables and group by
select * from Views

insert into Tests values ('EvaluateView1') -- eval for view1
insert into Tests values ('EvaluateView2') -- eval for view2
insert into Tests values ('EvaluateView3') -- eval for view3
select * from Tests

insert into TestViews values (1,1) -- view 1 with eval1
insert into TestViews values (2,2) -- view2 with eval2
insert into TestViews values (3,3) -- view3 with eval3
select * from TestViews


--- procedures dor Capos, CapoSoldiers and Underbosses
create procedure deleteMafiaBosses as
	delete from MafiaBosses
	where Bid>=100
go
create procedure deleteCapos as
	delete from Capos
	where Cpid>=100
go
create procedure deleteUnderbosses as
	delete from Underbosses
	where Uid>=100
go
create procedure deleteCaposoldiers as
	delete from CaposSoldiers
	where Cpid>=100
go
create procedure insertMafiaBosses as
	declare @name varchar(50)
	declare @surname varchar(50)
	declare @age int=50
	declare @i int=100
	while @i < 300 
	begin
		set @name = 'Name' + cast(@i as varchar)
		set @surname = 'Surname' + cast(@i as varchar)
		insert into MafiaBosses(Bid,Name,Surname,Age) values (@i , @name,@surname,@age)
		set @i=@i+1
	end
go
exec insertMafiaBosses
create procedure insertUnderbosses as
	declare @name varchar(50)
	declare @surname varchar(50)
	declare @age int=50
	declare @i int=100
	while @i < 300 
	begin
		set @name = 'Name' + cast(@i as varchar)
		set @surname = 'Surname' + cast(@i as varchar)
		insert into Underbosses(Uid,Name,Surname,Age) values (@i,@name,@surname,@age)
		set @i=@i+1
	end
go
exec insertUnderbosses
select * from Underbosses
select * from MafiaBosses
exec deleteMafiaBosses
exec deleteUnderbosses
select * from Underbosses


create procedure insertCapos as
	declare @name varchar(50)
	declare @surname varchar(50)
	declare @age int=50
	declare @i int=100
	while @i < 300 
	begin
		set @name = 'Name' + cast(@i as varchar)
		set @surname = 'Surname' + cast(@i as varchar)
		insert into Capos(Cpid,Name,Surname,Age,Uid) values (@i , @name,@surname,@age,@i)
		set @i=@i+1
	end
go

exec insertCapos
select * from Capos
exec deleteCapos

create procedure insertCaposSoldiers as
	declare @alphabet varchar(36) = 'ABCDEFGHIJKLMN'
	declare @group varchar(50)
	insert into CaposSoldiers(Cpid,Sid,GroupName)
	select Cpid, Sid, char(cast((90 - 65 )*rand() + 65 as integer))
	from Capos cross join Soldiers
	where Cpid>=100
	
go

exec insertCaposSoldiers
select * from CaposSoldiers
exec deleteCaposoldiers

insert into Tables values ('Underbosses') -- 1 pk, no fk
insert into Tables values ('Capos') -- 1 pk, 1 fk
insert into Tables values ('CaposSoldiers') -- 2 pk(fk)
select * from Tables
delete from Tables where TableID >=4

insert into Tests values ('InsertUnderbosses')
insert into Tests values ('InsertCapos')
insert into Tests values ('insertCaposSoldiers')
insert into Tests values ('deleteUnderbosses')
insert into Tests values ('deleteCapos')
insert into Tests values ('deleteCaposoldiers')
select * from Tests
delete from Tests where TestId >=10

insert into TestTables values (4,1,200,1) -- test 4 (InsertUnderbosses), table 1 (UnderBosses), NoOfRows 200, position 1
insert into TestTables values (5,2,200,2)-- test 5 (InsertCapos), table 2 (Capos), NoOfRows 200, position 2
insert into TestTables values (6,3,600,3)-- test 6 (insertCaposSoldiers), table 3 (CaposSoldiers), NoOfRows 600, position 3
insert into TestTables values (7,1,200,3)-- test 7 (deleteUnderbosses), table 1 (UnderBosses), NoOfRows 200, position 3
insert into TestTables values (8,2,200,2)-- test 8 (deleteCapos), table 2 (Capos), NoOfRows 200, position 2
insert into TestTables values (9,3,600,1)-- test 9 (deleteCaposoldiers), table 3 (CaposSoldiers), NoOfRows 600, position 1
select * from TestTables
select * from TestRunTables
select * from TestRuns
select * from TestRunViews

create or alter procedure testInsert as
	declare @startTime datetime
	declare @endTime datetime

	SET @startTime = GETDATE()
	exec insertUnderbosses
	set @endTime=GETDATE()
	insert into TestRuns values('insertUnderbosses',@startTime,@endTime)
	insert into TestRunTables values (@@IDENTITY, 1, @startTime, @endTime)


	SET @starttime = GETDATE()
	exec insertCapos
	set @endTime=GETDATE()
	insert into TestRuns values('insertCapos',@startTime,@endTime)
	insert into TestRunTables values (@@IDENTITY, 2, @startTime, @endTime)

	SET @startTime = GETDATE()
	exec insertCaposSoldiers
	set @endTime=GETDATE()
	insert into TestRuns values('insertCaposSoldiers',@startTime,@endTime)
	insert into TestRunTables values (@@IDENTITY, 3, @startTime, @endTime)
go

create or alter procedure testDelete as
	declare @startTime datetime
	declare @endTime datetime

	SET @startTime = GETDATE()
	exec deleteCaposoldiers
	set @endTime=GETDATE()
	insert into TestRuns values('deleteCaposoliders',@startTime,@endTime)
	insert into TestRunTables values (@@IDENTITY, 3, @startTime, @endTime)

	SET @startTime = GETDATE()
	exec deleteCapos
	set @endTime=GETDATE()
	insert into TestRuns values('deleteCapos',@startTime,@endTime)
	insert into TestRunTables values (@@IDENTITY, 2, @startTime, @endTime)

	SET @startTime = GETDATE()
	exec deleteUnderbosses
	set @endTime=GETDATE()
	insert into TestRuns values('deleteUnderbosses',@startTime,@endTime)
	insert into TestRunTables values (@@IDENTITY, 1, @startTime, @endTime)
go

exec testDelete
exec testInsert


create or alter procedure main as
	declare @startTime datetime
	declare @endTime datetime
	declare @run int = 1

	
	SET @starttime = GETDATE()
	exec testInsert
	select * from View1
	exec testDelete
	set @endtime=GETDATE()
	insert into TestRunViews(TestRunID, ViewID, StartAt, EndAt) values (@run, 1, @starttime, @endtime)

	set @run=@run+1

	
	SET @starttime = GETDATE()
	exec testInsert
	select * from View2
	exec testDelete
	set @endtime=GETDATE()
	insert into TestRunViews(TestRunID, ViewID, StartAt, EndAt) values (@run, 2, @starttime, @endtime)

	set @run=@run+1

	SET @starttime = GETDATE()
	exec testInsert
	select * from View3
	exec testDelete
	set @endtime=GETDATE()
	insert into TestRunViews(TestRunID, ViewID, StartAt, EndAt) values (@run, 3, @starttime, @endtime)
go

exec main
select * from TestRunViews
delete from TestRunViews