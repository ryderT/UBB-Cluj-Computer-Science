use Lab3
go

create table MafiaBosses(
Bid int primary key,
Name varchar(50),
Surname varchar(50),
Age int not null
)

create table Underbosses(
Uid int foreign key references MafiaBosses(Bid),
Name varchar(50),
Surname varchar(50),
Age int not null,
constraint pk_Underbosses primary key(Uid)
)

create table Consiglieres(
Cid int foreign key references MafiaBosses(Bid),
Name varchar(50),
Surname varchar(50),
Age int not null,
constraint pk_Consiglieres primary key(Cid)
)

create table Capos(
Cpid int primary key,
Name varchar(50),
Surname varchar(50),
Age int not null,
Uid int foreign key references Underbosses
)

create table Soldiers(
Sid int primary key identity,
Name varchar(50),
Surname varchar(50),
Age int not null,
Rank int check(Rank>=5)
)

create table CaposSoldiers(
Cpid int foreign key references Capos(Cpid),
Sid int foreign key references Soldiers(Sid),
GroupName varchar(50) not null,
constraint pk_CapoSoliders primary key(Cpid,Sid)
)

create table Associates(
Aid int primary key identity,
Name varchar(50),
Surname varchar(50),
Age int not null,
Rank int check(Rank>0 and Rank<5),
JobCategory varchar(50),
)

create table SoldiersAssociate(
Sid int foreign key references Soldiers(Sid),
Aid int foreign key references Associates(Aid),
GangName varchar(50),
constraint pk_SoldiersAssociate primary key(Sid,Aid)
)

create table Guns(
Gid int primary key,
GunType varchar(50),
Model varchar(50),
Ammo int,
)

create table Drugs(
Did int primary key,
DrugName varchar(50),
Potency varchar(50),
Price int not null,
)

create table SoldierGuns(
Sid int foreign key references Soldiers(Sid),
Gid int foreign key references Guns(Gid),
DaysOfOwning date,
Reason varchar(50)
constraint pk_SoldierGuns primary key(Sid,Gid)
)

create table AssociateDrugs(
Aid int references Associates(Aid),
Did int references Drugs(Did),
Quantity int,
constraint pk_AssociateDrugs primary key(Aid,Did)
)

create table BossHats(
BHid int primary key,
Brand varchar(50),
Material varchar(50),
Inherited bit,
SwagLevel int,
Bid int foreign key references MafiaBosses(Bid)
)