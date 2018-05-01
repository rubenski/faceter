create table f_logged_out_token (
  token VARCHAR(100) PRIMARY KEY,
  logout_time TIMESTAMP
);

CREATE INDEX index_logged_out_token ON f_logged_out_token (token);