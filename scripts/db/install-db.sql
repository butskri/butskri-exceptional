create table called_method (
	id                  INT8 NOT NULL,
	name                VARCHAR(256) NOT NULL,
	declaring_class     VARCHAR(256) NOT NULL,
	parameter_types     VARCHAR,
--	date_created        TIMESTAMP NOT NULL,
--    date_modified       TIMESTAMP,
--    user_created        VARCHAR(256) NOT NULL,
--    user_modified       VARCHAR(256),
    version             INT8 NOT NULL,
    CONSTRAINT called_method_pk PRIMARY KEY (id)
);

create table used_parameter(
	id                  INT8 NOT NULL,
	order_number        SMALLINT NOT NULL,
	parameter_type      VARCHAR(256) NOT NULL,
	parameter_value     VARCHAR(256),
	fk_called_method    INT8 NOT NULL,
--	date_created        TIMESTAMP NOT NULL,
--    date_modified       TIMESTAMP,
--    user_created        VARCHAR(256) NOT NULL,
--    user_modified       VARCHAR(256),
    version             INT8 NOT NULL,
    CONSTRAINT used_parameter_pk PRIMARY KEY (id),
    CONSTRAINT used_parameter_called_method_fk FOREIGN KEY(fk_called_method) REFERENCES called_method
);

create table thrown_exception(
	id                  INT8 NOT NULL,
	fk_called_method    INT8,
	exception_type      VARCHAR(256) NOT NULL,
	message             VARCHAR,
	stacktrace          VARCHAR NOT NULL,
--	date_created        TIMESTAMP NOT NULL,
--    date_modified       TIMESTAMP,
--    user_created        VARCHAR(256) NOT NULL,
--    user_modified       VARCHAR(256),
    version             INT8 NOT NULL,
    CONSTRAINT thrown_exception_pk PRIMARY KEY (id),
    CONSTRAINT thrown_exception_called_method_fk FOREIGN KEY(fk_called_method) REFERENCES called_method
);

create table principal(
    username            VARCHAR(254)  NOT NULL,
    password            VARCHAR(254)  NOT NULL,
    CONSTRAINT principal_pk PRIMARY KEY (username)
);

create table role(
    username            VARCHAR(254)  NOT NULL,
    userrole            VARCHAR(254)  NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (username, userrole),
    CONSTRAINT role_principal_fk FOREIGN KEY(username) REFERENCES principal
);

CREATE SEQUENCE THROWN_EXCEPTION_SEQ INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE CALLED_METHOD_SEQ INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE USED_PARAMETER_SEQ INCREMENT BY 1 START WITH 1;
