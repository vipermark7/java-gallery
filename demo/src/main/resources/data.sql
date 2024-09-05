INSERT INTO countries (id, name)
VALUES (1, 'USA');
INSERT INTO countries (id, name)
VALUES (2, 'France');
INSERT INTO countries (id, name)
VALUES (3, 'Brazil');
INSERT INTO countries (id, name)
VALUES (4, 'Italy');
INSERT INTO countries (id, name)
VALUES (5, 'Canada');

CREATE TABLE PICTURES
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    description TEXT,
    category    VARCHAR(50),
    blob        TEXT
);