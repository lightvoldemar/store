DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq start with 10000;

CREATE TABLE categories
(
    id    INT PRIMARY KEY DEFAULT nextval('global_seq'),
    title VARCHAR
);

CREATE TABLE products
(
    id          INT PRIMARY KEY DEFAULT nextval('global_seq'),
    price       INT NOT NULL CHECK ( price > 0 ),
    title       VARCHAR(100) NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);

CREATE TABLE users
(
    id       INT PRIMARY KEY DEFAULT nextval('global_seq'),
    email    VARCHAR(50),
    enabled  BOOL DEFAULT false,
    name     VARCHAR(100),
    password VARCHAR(100)
);

CREATE TABLE user_roles
(
    user_id INT NOT NULL,
    role   VARCHAR(100),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

