CREATE SCHEMA PISMO;


-- PISMO.ACCOUNTS definition

set schema PISMO;

CREATE TABLE PISMO.ACCOUNTS
(
    ACCOUNT_ID      NUMERIC NOT NULL AUTO_INCREMENT,
    DOCUMENT_NUMBER VARCHAR NOT NULL,
    CONSTRAINT ACCOUNTS_PK PRIMARY KEY (ACCOUNT_ID)
);

CREATE UNIQUE INDEX ACCOUNTS_DOCUMENT_NUMBER_IDX ON PISMO.ACCOUNTS (DOCUMENT_NUMBER);


-- PISMO.OPERATIONS_TYPES definition

CREATE TABLE PISMO.OPERATIONS_TYPES
(
    OPERATION_TYPE_ID NUMERIC       NOT NULL AUTO_INCREMENT,
    DESCRIPTION       VARCHAR  NOT NULL,
    "TYPE" NUMERIC NOT NULL,
    CONSTRAINT OPERATIONS_TYPES_PK PRIMARY KEY (OPERATION_TYPE_ID)
);

-- PISMO.TRANSACTIONS definition


CREATE TABLE PISMO.TRANSACTIONS
(
    TRANSACTION_ID    NUMERIC   NOT NULL AUTO_INCREMENT,
    ACCOUNT_ID        NUMERIC   NOT NULL,
    OPERATION_TYPE_ID NUMERIC   NOT NULL,
    EVENT_DATE        TIMESTAMP NOT NULL,
    AMOUNT NUMERIC(20,2) NOT NULL,
    CONSTRAINT TRANSACTIONS_PK PRIMARY KEY (TRANSACTION_ID),
    CONSTRAINT TRANSACTIONS_ACCOUNTS_FK FOREIGN KEY (ACCOUNT_ID) REFERENCES PISMO.ACCOUNTS (ACCOUNT_ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT TRANSACTIONS_OPERATIONS_TYPES_FK FOREIGN KEY (OPERATION_TYPE_ID) REFERENCES PISMO.OPERATIONS_TYPES (OPERATION_TYPE_ID) ON DELETE RESTRICT ON UPDATE RESTRICT
);


commit;