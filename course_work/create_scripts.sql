CREATE TYPE DIMENSIONS AS ENUM ('S', 'M', 'L', 'XL');
CREATE TYPE GOOD_STATUS AS ENUM ('WAITING', 'DELIVERING', 'DELIVERED', 'LOST', 'DESTROYED');
CREATE TYPE ORDER_STATUS AS ENUM ('WAITING', 'DELIVERING', 'DELIVERED', 'LOST', 'DESTROYED');


CREATE TABLE IF NOT EXISTS customer (
    customer_id SERIAL PRIMARY KEY,
    login VARCHAR(50) UNIQUE NOT NULL, 
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL
);

CREATE TABLE  IF NOT EXISTS order (
    order_id SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customer(customer_id) NOT NULL,
    departure_point_id INTEGER REFERENCES location(location_id) NOT NULL,
    destination_point_id INTEGER REFERENCES location(location_id) NOT NULL,
    status ORDER_STATUS,
    description VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS request (
    request_id SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customer(customer_id)
    description VARCHAR(512),
    length REAL NOT NULL CHECK(length > 0),
    width REAL NOT NULL CHECK(width > 0),
    height REAL NOT NULL CHECK(height > 0)
);

-- -- WEAK ENTITY TO CONNECT USER AND USER REQUEST --
-- CREATE TABLE IF NOT EXISTS customer_request (
--     customer_id INTEGER REFERENCES ON DELETE CASCADE customer(customer_id),
--     request_id INTEGER REFERENCES ON DELETE CASCADE request(request_id),
--     PRIMARY KEY(customer_id, request_id)
-- );

CREATE TABLE IF NOT EXISTS location (
    location_id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS good (
    good_id SERIAL PRIMARY KEY,
    description VARCHAR(128),
    weight REAL NOT NULL CHECK(weigh > 0),
    status GOOD_STATUS NOT NULL,
    dimensions dimensions,
    request_id INTEGER REFERENCES request(request_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS order_good (
    order_id INTEGER REFERENCES order(order_id),
    good_id INTEGER REFERENCES good(good_id),
    PRIMARY KEY(order_id, good_id)
);

CREATE TABLE IF NOT EXISTS courier (
    customer_id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    rating REAL NOT NULL CHECK(rating > 0), 
    balance INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS courier_order (
    courier_id INTEGER REFERENCES courier(courier_id),
    order_id INTEGER REFERENCES order(order_id),
    PRIMARY KEY(courier_id, order_id)
);

-- Комплексные ограничения целостности: --
-- В одном заказе не должно быть больше 10 товаров --
-- Курьеры с рейтингом ниже 3,5 не могут доставлять хрупкий груз --


-- Процедуры: --

-- №1 Определение размера на основе габаритова --

CREATE OR REPLACE FUNCTION 
-- №2 Изменение рейтинга и баланса курьера на основе изменения статуса заказа --

-- Часто используемые операции: --
-- №1 Вывод курьрами заказов по статусу, весу, локации
-- №2 Поиск клиентом курьера по номеру заказа
-- №3 Поиск клиентом курьеров по рейтингу