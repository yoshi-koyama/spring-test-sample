DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id INT unsigned AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY(id)
);
INSERT INTO users (name) VALUES ("小山");
INSERT INTO users (name) VALUES ("田中");
INSERT INTO users (name) VALUES ("山田");
