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

INSERT INTO ordering (customer_id, departure_point_id, destination_point_id, status, description) VALUES
((SELECT customer_id FROM customer WHERE customer.login = 'conan'), (SELECT location_id FROM location WHERE name = 'Mountain Knot City'), (SELECT location_id FROM location WHERE name = 'Middle Knot City'), 'WAITING', 'Present for my dad'),
((SELECT customer_id FROM customer WHERE customer.login = 'edgar'), (SELECT location_id FROM location WHERE name = 'Middle Knot City'), (SELECT location_id FROM location WHERE name = 'Mountain Knot City'), 'WAITING', 'Some important documents'),
((SELECT customer_id FROM customer WHERE customer.login = 'hirokazu'), (SELECT location_id FROM location WHERE name = 'Central Knot City'), (SELECT location_id FROM location WHERE name = 'Lake Knot City'), 'WAITING', 'Set of fragile tableware'),
((SELECT customer_id FROM customer WHERE customer.login = 'geoff'), (SELECT location_id FROM location WHERE name = 'Lake Knot City'), (SELECT location_id FROM location WHERE name = 'South Knot City'), 'WAITING', 'Big black plastic packet'),
((SELECT customer_id FROM customer WHERE customer.login = 'daichi'), (SELECT location_id FROM location WHERE name = 'South Knot City'), (SELECT location_id FROM location WHERE name = 'Central Knot City'), 'WAITING', 'Box of dry ice'),
((SELECT customer_id FROM customer WHERE customer.login = 'edgar'), (SELECT location_id FROM location WHERE name = 'Mountain Knot City'), (SELECT location_id FROM location WHERE name = 'Mountain Knot City'), 'WAITING', 'Stack of pizza');

INSERT INTO request (order_id, weight, length, width, height, description) VALUES
((SELECT order_id FROM ordering WHERE ordering.description = 'Present for my dad' AND ordering.customer_id = (SELECT customer_id FROM customer WHERE customer.login = 'conan')), 20.0, 35.2, 17.4, 5.5, 'Present for my dad'),
((SELECT order_id FROM ordering WHERE ordering.description = 'Some important documents' AND ordering.customer_id = (SELECT customer_id FROM customer WHERE customer.login = 'edgar')), 6.0, 5.2, 2.4, 1.5, 'Some important documents'),
((SELECT order_id FROM ordering WHERE ordering.description = 'Set of fragile tableware' AND ordering.customer_id = (SELECT customer_id FROM customer WHERE customer.login = 'hirokazu')), 2.0, 7.2, 8.4, 1.5, 'Set of fragile tableware'),
((SELECT order_id FROM ordering WHERE ordering.description = 'Big black plastic packet' AND ordering.customer_id = (SELECT customer_id FROM customer WHERE customer.login = 'geoff')), 16.5, 17.3, 18.7, 0.9, 'Big black plastic packet'),
((SELECT order_id FROM ordering WHERE ordering.description = 'Box of dry ice' AND ordering.customer_id = (SELECT customer_id FROM customer WHERE customer.login = 'daichi')), 15.0, 15.0, 15.0, 15.0, 'Box of dry ice'),
((SELECT order_id FROM ordering WHERE ordering.description = 'Stack of pizza' AND ordering.customer_id = (SELECT customer_id FROM customer WHERE customer.login = 'edgar')), 3.0, 8.4, 9.2, 0.5, 'Stack of pizza');



INSERT INTO request_condition (request_id, condition) VALUES 
((SELECT request_id FROM request WHERE request.description = 'Present for my dad'), $$DON'T SHAKE$$),
((SELECT request_id FROM request WHERE request.description = 'Box of dry ice'), $$DON'T IMMERSE$$),
((SELECT request_id FROM request WHERE request.description = 'Set of fragile tableware'), 'FRAGILE'),
((SELECT request_id FROM request WHERE request.description = 'Big black plastic packet'), $$DON'T IMMERSE$$),
((SELECT request_id FROM request WHERE request.description = 'Stack of pizza'), $$DON'T SHAKE$$);

INSERT INTO courier (name, surname, rating, balance) VALUES
('Egor', 'Stukov', 4.92, 99000),
('Ivan', 'Nesterov', 4.92, 99000);


