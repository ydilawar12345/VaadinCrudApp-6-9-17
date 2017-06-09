CREATE TABLE user (
  id int auto_increment,
  birth_day date,
  name varchar(50) not null,
  phone_number varchar(20),
  eye_color varchar(10),
  height varchar(10),
  score varchar(1),
  rank varchar(1),
  type varchar(4),
  primary key (id));

 INSERT INTO user (id,name,phone_number,birth_day,eye_color,height,score,rank,type) VALUES (1,'Yusuf Dilawar','2488021890','1997-02-26','brown','5.11',4,0,'rank');
 INSERT INTO user (id,name,phone_number,birth_day,eye_color,height,score,rank,type) VALUES (2,'test1','0','1997-02-26','brown','5',2,NULL,'rank');
 INSERT INTO user (id,name,phone_number,birth_day,eye_color,height,score,rank,type) VALUES (3,'test2','2488021890',NULL ,'brown','5.11',3,NULL,'rank');
 INSERT INTO user (id,name,phone_number,birth_day,eye_color,height,score,rank,type) VALUES (4,'test3',NULL,'1997-02-26',NULL,NULL,1,NULL,'rank');
