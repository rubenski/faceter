create table f_user (
  uuid varchar(36) PRIMARY KEY,
  email varchar(100) NOT NULL UNIQUE,
  first_name varchar(30) NOT NULL,
  last_name varchar(30) NOT null,
  roles varchar(150) NOT NULL,
  pw varchar(200) NOT NULL,
  account_expired boolean NOT NULL,
  locked boolean NOT NULL,
  enabled boolean NOT NULL
);


CREATE INDEX index_email ON f_user (email);