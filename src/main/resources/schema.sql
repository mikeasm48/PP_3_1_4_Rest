CREATE DATABASE IF NOT EXISTS test;
USE test;

-- Создание таблицы "users"
CREATE TABLE IF NOT EXISTS users (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      email VARCHAR(255) NULL,
                                      first_name VARCHAR(255) NULL,
                                      last_name VARCHAR(255) NULL,
                                      age INT,
                                      password VARCHAR(255) NULL
);

-- Создание таблицы "roles"
CREATE TABLE IF NOT EXISTS roles (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255) PRIMARY KEY
    );

-- Создание таблицы "users_roles"
CREATE TABLE IF NOT EXISTS users_roles (
                                           user_id BIGINT NOT NULL,
                                           role_id INT NOT NULL,
                                           PRIMARY KEY (user_id, role_id)
);

-- data.sql
INSERT IGNORE INTO users(id, email,first_name,last_name, age, password) VALUES (1,'admin@mail.ru','Admin','Securny',18,'$2a$10$dCo5DYNZPxq23u8BAJOJDejIYgUCRkVzGH/AQ4KV1YqQzXDtKKmOy');
INSERT IGNORE INTO users(id, email,first_name,last_name, age, password) VALUES (2,'mike@mail.ru','Mike','Asm', 0,'$2a$10$VKV//sGgoyEV9XM6xpNLl.GYGZph/tnR7rLO1AQXO7EHrr5HC/J6.');

INSERT IGNORE INTO roles(id, name) VALUES (1,'ROLE_ADMIN');
INSERT IGNORE INTO roles(id, name) VALUES (2,'ROLE_USER');


INSERT IGNORE INTO users_roles(user_id, role_id) VALUES(1,1);
INSERT IGNORE INTO users_roles(user_id, role_id) VALUES(1,2);
INSERT IGNORE INTO users_roles(user_id, role_id) VALUES(2,1);