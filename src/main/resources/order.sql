create table order
(
   id integer not null,
   customername varchar(25) not null,
   orderdate DATE not null,
   shippingaddress varchar(255) not null,
   orderitems  varchar(10) not null,
   total varchar(10) not null,
   primary key(id)
)