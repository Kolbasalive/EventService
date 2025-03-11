CREATE SEQUENCE IF NOT EXISTS event_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS event
(
    event_id    BIGINT PRIMARY KEY,
    title       VARCHAR(255),
    description VARCHAR(255),
    location    VARCHAR(255),
    max_user_size INT,
    event_date   TIMESTAMP,
    organization_id BIGINT,
    avg_rating  DOUBLE PRECISION,
    category    VARCHAR(255)
);