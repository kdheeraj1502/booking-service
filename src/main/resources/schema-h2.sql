CREATE TABLE DRIVER
        (
        id int auto_increment NOT NULL,
        name VARCHAR2(250) NOT NULL,
		email VARCHAR2(250) NOT NULL,
		phone NUMBER(250) NOT NULL,
		license VARCHAR2(250) NOT NULL,
		carnumber VARCHAR2(250) NOT NULL,
		longitude NUMBER(20,18) NOT NULL,
		latitude NUMBER(20,18) NOT NULL,
        PRIMARY KEY(id)
        );