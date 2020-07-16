create table "user" (
id bigserial not null,
email varchar(255) not null,
name varchar(255) not null,
password varchar(255) not null,
primary key (id));

alter table "user" add constraint uk_email unique (email);