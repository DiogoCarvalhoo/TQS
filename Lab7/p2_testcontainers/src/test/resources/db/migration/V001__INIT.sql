CREATE TABLE employee
(
    id    BIGSERIAL PRIMARY KEY,
    name  varchar(25) NOT NULL,
    email varchar(50) NOT NULL,
    unique (email)
);

insert into employee(name, email)
values ('Diogo', 'diogo@something.com');
