use Lab3
go

create table Versions(
CurrentVersion INT)

insert into Versions values (0)
--version 0

select * from Versions

--V1 - modify the type of a column
go
create procedure Version1
as
	begin
		alter table BossHats
		alter column SwagLevel smallint
		print '1 - modified type of column'
	end
--reverse
go
create procedure Reverse1
as
	begin
		alter table BossHats
		alter column SwagLevel int
		print '1 - reversed type of column'
	end

exec Version1
exec Reverse1

--V2 add a column

go
create procedure Version2
as
	begin
		alter table MafiaBosses
		add IsDeceased bit
		print '2 - added a column'
	end
-- RV2 drop a column
go
create procedure Reverse2
as
	begin
		alter table MafiaBosses
		drop column IsDeceased
		print '2 - removed a column'
	end

exec Version2
exec Reverse2

--V3 add default constraint

go
create procedure Version3
as
	begin
	alter table BossHats
	add constraint df_BossHats default 1000 for SwagLevel
	print '3 - added default constraint'
	end

--Rv3 remove default constraint

go
create procedure Reverse3
as
	begin
		alter table BossHats
		drop constraint df_BossHats
		print '3 - removed default constraint'
	end

exec Version3
exec Reverse3

--V4 add pk
go
create procedure Version4
as
	begin
		create table BossShoes(
		BSid int not null,
		Brand varchar(50),
		Price int,
		constraint pk_BossShoes primary key(BSid))
		print '4 - added primakry key and table'
	end
-- Rv4 remove pk and table
go 
create procedure Reverse4
as
	begin
		alter table BossShoes
		drop constraint pk_BossShoes
		drop table BossShoes
		print '4 - removed primary key and table'
	end

exec Version4
exec Reverse4

--V5 add candidate key
go
create procedure Version5
as
	begin
		alter table BossHats
		add constraint uk_BossHats unique(SwagLevel)
		print '5-added candidate key'
	end

go 
create procedure Reverse5
as
	begin
		alter table BossHats
		drop constraint uk_BossHats
		print '5 - removed cand key'
	end
select * from BossHats
delete from BossHats
where BossHats.Material='Premium Silk'
exec Version5
exec Reverse5

create table BossPens(
		BSid int not null primary key,
		Brand varchar(50),
		Price int,
)
select * from BossPens
--V6 add foreign
go 
create procedure Version6
as
	begin
		alter table BossPens
		add Bid int not null
		alter table BossPens
		add constraint fk_BossPens foreign key(Bid) references MafiaBosses(Bid)
		print '6-added fk'
	end
go
create procedure Reverse6
as
	begin
		alter table BossPens
		drop constraint fk_BossPens
		alter table BossPens
		drop column Bid
		print'6-removed fk'
	end

exec Version6
exec Reverse6

--V7 create table
go
create procedure Version7
as
	begin
		create table BossCars(
		BCid int primary key identity,
		Brand varchar(30),
		Model varchar(30),
		Year int)
		print'7-added table'
	end
go
create procedure Reverse7
as
	begin
		drop table BossCars
		print '7-removed table'
	end

exec Version7
exec Reverse7


--main

go
create procedure main @v int
as
	if @v < 0 or @v >7
		print 'Version must be between 0 and 7'
	else
		begin
			declare @currentV int = (select CurrentVersion from Versions)
			declare @toExec varchar(20)
			while @currentV < @v
				begin
					set @currentV=@currentV+1
					set @toExec='Version'+convert(varchar(20),@currentV)
					exec @toExec
				end
			while @currentV > @v
				begin
					set @toExec='Reverse'+convert(varchar(20),@currentV)
					exec @toExec
					set @currentV=@currentV-1
				end
			update Versions
			set CurrentVersion=@v
			print 'Version:'+convert(varchar(20),@v)
		end
exec main 1