/*
DROP TABLE IF EXISTS billionaires;
 
CREATE TABLE billionaires (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL
);
 
INSERT INTO billionaires (first_name, last_name, career) VALUES
  ('Aliko', 'Dangote', 'Billionaire Industrialist'),
  ('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
  ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');
  
 */
 DROP TABLE IF EXISTS Customer;
 
CREATE TABLE Customer (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  address VARCHAR(250) NOT NULL,
  age NUMBER NOT NULL
);
INSERT INTO Customer (name, address, age) VALUES
  ('Jack Smith', 'Massachusetts', 23),
  ('Adam Johnson', 'New York', 27),
  ('Katherin Carter', 'Washington DC', 26),
  ('Jack London', 'Nevada', 33),
  ('Tamal Nayek', 'India', 32);
 