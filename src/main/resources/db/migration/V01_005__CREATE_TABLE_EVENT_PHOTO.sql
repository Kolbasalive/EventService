CREATE SEQUENCE IF NOT EXISTS photo_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS event_photo
(
    photo_id    BIGINT PRIMARY KEY,
    url         TEXT,
    event_id    BIGINT,
    CONSTRAINT fk_event_photo_event FOREIGN KEY (event_id) REFERENCES event (event_id) ON DELETE CASCADE
)