insert into users (USER_ID,	CREATED,	EMAIL,	ISACTIVE,	LAST_LOGIN,	MODIFIED,	NAME,	PASSWORD,	TOKEN) values(
'939dfc5c-ae6b-474f-aed0-ee91eee28bdc',now(),'admin@bci.com',TRUE,now(),now(),'admin','$2a$10$NAZA1.G7A8W7MdmSKdKISOOq.gqBCmpSAWzxYvITtzR.HdK1foR/u','Init');

insert into phones (number, citycode, contrycode, user_id) values(12345678, '75', '56', '939dfc5c-ae6b-474f-aed0-ee91eee28bdc')

