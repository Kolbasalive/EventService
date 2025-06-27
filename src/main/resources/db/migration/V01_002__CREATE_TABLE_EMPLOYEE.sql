CREATE SEQUENCE IF NOT EXISTS employee_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS employee
(
    employee_id BIGINT PRIMARY KEY,
    name        VARCHAR(255),
    email       VARCHAR(255),
    login       VARCHAR(255),
    password    VARCHAR(255),
    role        VARCHAR(255)
)