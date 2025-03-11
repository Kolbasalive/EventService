CREATE SEQUENCE IF NOT EXISTS review_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS review
(
    review_id   BIGINT PRIMARY KEY,
    comment     VARCHAR(255),
    created_at  TIMESTAMP,
    rating      INT,
    employee_id BIGINT,
    event_id    BIGINT,
    CONSTRAINT fk_review_employee FOREIGN KEY (employee_id) REFERENCES employee (employee_id) ON DELETE CASCADE,
    CONSTRAINT fk_review_event FOREIGN KEY (event_id) REFERENCES event (event_id) ON DELETE CASCADE
);