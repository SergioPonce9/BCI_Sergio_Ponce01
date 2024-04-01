insert into users (created, email, isactive, last_login, modified, name, password, token, user_id) values (
now(),
'administrator1@gmail.com', 
true, 
now(),
now(),
'administrator', 
'pwd',
'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbmlzdHJhdG9yIiwiZXhwIjoxNjk0OTYxMDE5LCJpYXQiOjE2OTMxNjEwMTl9.vCnIZeQB6iMaD5KduTDEKNSs0goRY-YQ8mSJVBdWyUjDrx2dczsHA9uqx6pq8ODmKqsB37QYTE0Hxcxk_q_SsQ', 
'cf9fa6ca-cbaf-4a81-93a0-32b22fa5c160');


insert into phones (number, citycode, contrycode, user_id) values(12345678, '75', '56', 'cf9fa6ca-cbaf-4a81-93a0-32b22fa5c160')

