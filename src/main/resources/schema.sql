CREATE DATABASE IF NOT EXISTS test;
USE test;

-- Создание таблицы "users"
CREATE TABLE IF NOT EXISTS users (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      email VARCHAR(255) NULL,
                                      name VARCHAR(255) NULL,
                                      last_name VARCHAR(255) NULL,
                                      age INT,
                                      password VARCHAR(255) NULL
);

-- Создание таблицы "roles"
CREATE TABLE IF NOT EXISTS roles (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL UNIQUE
    );

-- Создание таблицы "users_roles"
CREATE TABLE IF NOT EXISTS users_roles (
                                           user_id INT NOT NULL,
                                           role_id VARCHAR(255) NOT NULL,
                                           PRIMARY KEY (user_id, role_id)
);

-- data.sql
INSERT IGNORE INTO users(id, email,name,last_name, age, password) VALUES (1,'mike@mail.ru','Mike', 'Asm', 50,'{noop}1');
INSERT IGNORE INTO users(id, email,name,last_name, age, password) VALUES (2, 'admin@mail.ru','Admin','Securny', 18,'{noop}1');

INSERT IGNORE INTO roles(name) VALUES ('ROLE_USER');
INSERT IGNORE INTO roles(name) VALUES ('ROLE_ADMIN');

INSERT IGNORE INTO users_roles(user_id, role_id) VALUES(1,'ROLE_USER');
INSERT IGNORE INTO users_roles(user_id, role_id) VALUES(2,'ROLE_ADMIN');
INSERT IGNORE INTO users_roles(user_id, role_id) VALUES(2,'ROLE_USER');