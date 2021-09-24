CREATE TABLE user_tb
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(128),
    surname VARCHAR(128),
    mail VARCHAR(128),
    salary_per_hours INT
);