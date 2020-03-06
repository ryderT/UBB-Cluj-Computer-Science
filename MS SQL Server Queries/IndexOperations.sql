create database Lab4
go
use Lab4
go

drop table Perfume
drop table Customer
drop table PerfumeShop

create table Perfume(
	Pid int primary key,
	quantity int unique,
	brand varchar(20) unique
)
insert into Perfume values (1,100,'Gucci'),(2,150,'Versace'),(3,200,'Paco')
select * from Perfume

create table Customer(
	Cid int primary key,
	Credit int
)
insert into Customer values (1,100),(2,300),(3,0)
select * from Customer

create table PerfumeShop(
	PSid int primary key,
	Cid int foreign key references Customer(Cid),
	Pid int foreign key references Perfume(Pid)
)
insert into PerfumeShop values (1,1,1),(2,1,2),(3,2,1)
select * from PerfumeShop

---a)

--clustered index scan
select * from Perfume order by Pid

--clustered index seek
select * from Perfume where Pid>3


--nonclustered index scan 
drop index i1 on Perfume
drop index if exists i1 on Perfume
create nonclustered index i1 on Perfume(Brand)

select Perfume.Pid as 'Perfume ID', Perfume.brand as 'Brand' from Perfume


--nonclustered index seek
drop index i2 on Perfume
drop index if exists i2 on Perfume
create nonclustered index i2 on Perfume(quantity)

select Perfume.quantity as 'Quantity' from Perfume with (index=i2) where Perfume.quantity>50


--key look-up
select * from Perfume with(index=i2)


---b)
if exists (select * from sys.indexes where name = N'NCL_Customer')
		drop index NCL_Customer on Customer

--create the index
create nonclustered index NCL_Customer on Customer(Cid,Credit)
insert into Customer values (100,1000000000)
--Estimated cost -> 0.0032864
select Customer.Cid as 'Customer', Customer.Credit as 'Credit in shop' from Customer where Customer.Credit>1000000

drop index NCL_Customer on Customer

--clustered 0.0032853 :)
select Customer.Cid as 'Customer', Customer.Credit as 'Credit in shop' from Customer where Customer.Credit>1000000

delete from Customer
delete from Perfume
delete from PerfumeShop
---c)
go
CREATE PROCEDURE addIntoCustomer
AS
BEGIN
DECLARE @VAL int
SET @VAL = 0
	while @VAL < 200
	BEGIN
		INSERT INTO Customer
		Values(@VAL,@VAL)

		INSERT INTO Perfume
		VALUES(@VAL,@VAL,CAST(@VAL AS VARCHAR))

		INSERT INTO PerfumeShop
		VALUES(@VAL,(SELECT cid FROM Customer WHERE cid=@VAL),(SELECT Pid FROM Perfume WHERE Pid=@VAL))

		SET @VAL = @VAL + 1
	END
end

exec addIntoCustomer

select * from Customer
select * from PerfumeShop

delete from PerfumeShop where Cid>10

go
create or alter view tbView as
select Customer.Credit,Customer.Cid from Customer
inner join
PerfumeShop on PerfumeShop.Cid=Customer.Cid
where Customer.Credit>1

select * from tbView

create nonclustered index j1 on  Customer(Cid,Credit)
create nonclustered index j2 on Perfume(Pid,quantity)
create nonclustered index j3 on PerfumeShop(PSid,Cid,Pid)

drop index if exists j1 on Customer
drop index if exists j2 on Perfume
drop index if exists j3 on PerfumeShop