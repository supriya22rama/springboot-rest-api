# springboot-rest-api

Create the table in your database

create table customer (
id integer not null auto_increment,
firstname varchar(50),
lastname varchar(50),
dob date not null,
email varchar(50),
password varchar(10),
primary key(id),
unique(email)
);
