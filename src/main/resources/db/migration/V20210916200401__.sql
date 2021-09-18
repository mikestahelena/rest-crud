CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE CUSTOMER (
  id BIGINT NOT NULL auto_increment,
  document VARCHAR(255),
  customer_type VARCHAR(255),
  name VARCHAR(255),
  birth_date date,
  email VARCHAR(255),
  phone_number VARCHAR(255),
  date_created TIMESTAMP,
  last_updated TIMESTAMP,
  CONSTRAINT pk_customer PRIMARY KEY (id)
);
