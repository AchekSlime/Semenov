CREATE TABLE user_tb
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(128),
    surname VARCHAR(128),
    mail VARCHAR(128),
    salary_per_hours INT
);

ALTER USER 'root'@'localhost' IDENTIFIED BY '95%*igyJ%_^2wP?6C&Pqg58Kp7vChz/1';

GRANT ALL PRIVILEGES ON semenov.* TO root@"%" IDENTIFIED BY '95%*igyJ%_^2wP?6C&Pqg58Kp7vChz/1' WITH GRANT OPTION;
GRANT ALL ON *.* to root@'localhost' IDENTIFIED BY '95%*igyJ%_^2wP?6C&Pqg58Kp7vChz/1';
GRANT ALL PRIVILEGES ON `semenov`.* TO achek@'%' IDENTIFIED BY 'impsstabb';
CREATE USER 'achek'@'%' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON semenov.* TO 'achek'@'%';