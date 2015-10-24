
CREATE TABLE users
(
  id serial NOT NULL,
  firstname character varying(40) NOT NULL,
  lastname character varying(40) NOT NULL,
  username character varying(40) NOT NULL,
  emailid character varying(40) NOT NULL,
  password character varying(40) NOT NULL,
  isactive boolean,
  lastlogin character varying(40) NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (id)
);

INSERT INTO users(firstname, lastname, username, emailid, password,isactive, lastlogin) VALUES ('Peter', 'Sotos', 'admin', 'Peter@mail.com', '21232f297a57a5a743894a0e4a801fc3', TRUE, '2014-01-20 12:46:54.396');

Select * from users;


CREATE TABLE role
(
  id serial NOT NULL,
  rolename character varying(40) NOT NULL,
  CONSTRAINT role_pkey PRIMARY KEY (id)
);

INSERT INTO ROLE(ROLENAME) VALUES ('ADMIN');
INSERT INTO ROLE(ROLENAME) VALUES ('USER');
INSERT INTO ROLE(ROLENAME) VALUES ('SALES');
INSERT INTO ROLE(ROLENAME) VALUES ('FINANCE');

CREATE TABLE usersandroles
(
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT usersandroles_pkey PRIMARY KEY (user_id, role_id),
  CONSTRAINT fk_role FOREIGN KEY (role_id)
      REFERENCES role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pk_user FOREIGN KEY (user_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO USERSANDROLES(USER_ID,ROLE_ID) VALUES (1,1);
INSERT INTO USERSANDROLES(USER_ID,ROLE_ID) VALUES (1,2);

