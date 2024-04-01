CREATE TABLE users (
user_id UUID NOT NULL,
name VARCHAR(100) NOT NULL,
email VARCHAR(100) NOT NULL,
password VARCHAR(100) NOT NULL,
created timestamp NOT NULL,
modified timestamp NOT NULL,
last_login timestamp NOT NULL,
token VARCHAR(255) NOT NULL,
isactive BOOLEAN NOT NULL,
PRIMARY KEY (user_id)
);

CREATE TABLE phones (
number int NOT NULL,
citycode VARCHAR(10) NOT NULL,
contrycode VARCHAR(2) NOT NULL,
user_id UUID NOT NULL,
PRIMARY KEY (number),
CONSTRAINT fk_user_phones FOREIGN KEY (user_id) REFERENCES users(user_id)
);





