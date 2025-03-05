ALTER TABLE event
    DROP CONSTRAINT fk_event_on_feedback;

ALTER TABLE feedback
    DROP CONSTRAINT fk_feedback_on_user;

CREATE SEQUENCE IF NOT EXISTS event_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS review_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS user_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE review
(
    review_id  BIGINT NOT NULL,
    comment    VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    rating     INTEGER,
    user_id    BIGINT,
    event_id   BIGINT,
    CONSTRAINT pk_review PRIMARY KEY (review_id)
);

ALTER TABLE event
    ADD event_date TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE event
    ADD organization_id BIGINT;

ALTER TABLE event
    ADD title VARCHAR(255);

ALTER TABLE reservation
    ADD id BIGINT;

ALTER TABLE "user"
    ADD id BIGINT;

ALTER TABLE review
    ADD CONSTRAINT FK_REVIEW_ON_EVENT FOREIGN KEY (event_id) REFERENCES event (event_id);

ALTER TABLE review
    ADD CONSTRAINT FK_REVIEW_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);

DROP TABLE feedback CASCADE;

ALTER TABLE reservation
    DROP COLUMN reservation_id;

ALTER TABLE "user"
    DROP COLUMN user_id;

ALTER TABLE event
    DROP COLUMN data;

ALTER TABLE event
    DROP COLUMN feedback_id;

ALTER TABLE event
    DROP COLUMN name;

ALTER TABLE reservation
    ADD CONSTRAINT pk_reservation PRIMARY KEY (id);

ALTER TABLE "user"
    ADD CONSTRAINT pk_user PRIMARY KEY (id);