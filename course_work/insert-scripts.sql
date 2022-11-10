INSERT INTO customer (login, name, surname) VALUES 
('conan', 'Conan', 'O’Brien'),
('hirokazu', 'Hirokazu', 'Hamamura'),
('jordan', 'Jordan', 'Vogt-Roberts'),
('daichi', 'Daichi', 'Miura'),
('tommy', 'Tommy', 'Wirkola'),
('edgar', 'Edgar', 'Wright'),
('geoff', 'Geoff', 'Keighley'),
('manabu', 'Manabu', 'Makime');

INSERT INTO location (name) VALUES 
('Capital Knot City'),
('Central Knot City'),
('Middle Knot City'),
('Mountain Knot City'),
('Port Knot City'),
('South Knot City'),
('Edge Knot City'),
('Lake Knot City');

INSERT INTO ordering (customer_id, departure_point_id, destination_point_id) VALUES
(1, 1, 2),
(1, 4, 3),
(2, 5, 1),
(5, 1, 6),
(6, 7, 8),
(3, 1, 2);

INSERT INTO request (order_id, description, weight, length, width, height) VALUES
(1, 'Present for my dad',20.0, 35.2, 17.4, 5.5),
(2, 'Some important documents', 6.0, 5.2, 2.4, 1.5),
(3, 'Set of fragile tableware',2.0, 7.2, 8.4, 1.5),
(4, 'Big black plastic packet',16.5, 17.3, 18.7, 0.9),
(5, 'Box of dry ice',15.0, 15.0, 15.0, 15.0),
(6, 'Stack of pizza',3.0, 8.4, 9.2, 0.5);



INSERT INTO request_condition (request_id, condition) VALUES 
(1, $$DON'T SHAKE$$),
(3, 'FRAGILE'),
(4, $$DON'T IMMERSE$$),
(5, $$DON'T IMMERSE$$),
(6, $$DON'T SHAKE$$);

INSERT INTO courier (name, surname, rating, balance) VALUES
('Egor', 'Stukov', 4.92, 99000),
('Ivan', 'Nesterov', 4.92, 99000);


