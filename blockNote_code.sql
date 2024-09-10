DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(50) NOT NULL
);

INSERT INTO users (name, phone) VALUES ('Jevgenij', '+34701254177');
INSERT INTO users (name, phone) VALUES ('DeniZka', '+34607295319');
INSERT INTO users (name, phone) VALUES ('Vladimir', '+344072175523');
INSERT INTO users (name, phone) VALUES ('Olga', '+354273874575');
INSERT INTO users (name, phone) VALUES ('Marina', '+346298173942');
