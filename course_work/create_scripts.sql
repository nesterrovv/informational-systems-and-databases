CREATE TYPE DIMENSIONS AS ENUM ('S', 'M', 'L', 'XL');
CREATE TYPE GOOD_STATUS AS ENUM ('WAITING', 'DELIVERING', 'DELIVERED', 'LOST', 'DESTROYED');
CREATE TYPE ORDER_STATUS AS ENUM ('WAITING', 'DELIVERING', 'DELIVERED', 'LOST', 'DESTROYED');
CREATE TYPE EXTRA_CONDITION AS ENUM ('FRAGILE', $$DON'T IMMERSE$$, $$DON'T SHAKE$$);

CREATE TABLE IF NOT EXISTS customer (
    customer_id SERIAL PRIMARY KEY,
    login VARCHAR(50) UNIQUE NOT NULL, 
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS location (
    location_id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE  IF NOT EXISTS ordering (
    order_id SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customer(customer_id) NOT NULL,
    departure_point_id INTEGER REFERENCES location(location_id) NOT NULL,
    destination_point_id INTEGER REFERENCES location(location_id) NOT NULL,
    status ORDER_STATUS,
    description VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS request (
    request_id SERIAL PRIMARY KEY,
    order_id INTEGER REFERENCES ordering(order_id) NOT NULL,
    description VARCHAR(512),
    weight REAL NOT NULL CHECK(weight > 0),
    length REAL NOT NULL CHECK(length > 0),
    width REAL NOT NULL CHECK(width > 0),
    height REAL NOT NULL CHECK(height > 0)
);

CREATE TABLE IF NOT EXISTS request_condition (
    request_id INTEGER REFERENCES request(request_id) ON DELETE CASCADE NOT NULL,
    condition EXTRA_CONDITION NOT NULL,
    PRIMARY KEY(request_id, condition)
);


CREATE TABLE IF NOT EXISTS good (
    good_id SERIAL PRIMARY KEY,
    status GOOD_STATUS NOT NULL,
    dimensions dimensions,
    request_id INTEGER REFERENCES request(request_id) ON DELETE CASCADE
    );


CREATE TABLE IF NOT EXISTS courier (
    courier_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    rating REAL NOT NULL CHECK(rating > 0), 
    balance INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS courier_order (
    courier_id INTEGER REFERENCES courier(courier_id),
    order_id INTEGER REFERENCES ordering(order_id),
    PRIMARY KEY(courier_id, order_id)
);

-- Комплексные ограничения целостности: --
-- В одном заказе не должно быть больше 10 товаров --
-- Курьеры с рейтингом ниже 3,5 не могут доставлять грузы с дополнительными условиями --


-- Процедуры: --

-- №1 Определение размера на основе габаритова --
-- №2 Изменение рейтинга и баланса курьера на основе изменения статуса заказа --

-- Часто используемые операции: --
-- №1 Вывод курьрами заказов по статусу, весу, локации
-- №2 Поиск клиентом курьера по номеру заказа
-- №3 Поиск клиентом курьеров по рейтингу