drop table if exists Position;

create table Position(
  Id int not null AUTO_INCREMENT,
  Description varchar(100),
  Location varchar(100),
  PRIMARY KEY ( Id )
);