drop table if exists Position;
drop table if exists Client;

create table Position(
  Id int not null AUTO_INCREMENT,
  Description varchar(100),
  Location varchar(100),
  PRIMARY KEY ( Id )
);

create table Client(
  Uuid varchar(100) not null,
  Name varchar(100),
  Email varchar(100),
  PRIMARY KEY ( Uuid )
);