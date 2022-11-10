-- DETERMINE DIMENSIONS FUNCTION --
CREATE OR REPLACE FUNCTION determine_dimensions(width REAL, height REAL, length REAL)
    RETURNS DIMENSIONS
    LANGUAGE PLPGSQL
AS
$$
DECLARE
    greatest_dimension REAL;
    result             DIMENSIONS;
BEGIN
    greatest_dimension := GREATEST(width, height);
    greatest_dimension := GREATEST(greatest_dimension, length);
    IF greatest_dimension < 20 THEN
        result := 'S';
    ELSIF greatest_dimension < 35 THEN
        result := 'M';
    ELSIF greatest_dimension < 55 THEN
        result := 'L';
    ELSE
        result := 'XL';
    END IF;
    RETURN result;
END;
$$;

-- INSERT GOOD BY REQUEST  --
CREATE OR REPLACE FUNCTION create_good_from_request()
    RETURNS TRIGGER AS
$$
DECLARE
    dimension dimensions;
    count     INTEGER;
BEGIN
    dimension := determine_dimensions(NEW.width, NEW.height, NEW.length);
    INSERT INTO good(status, dimensions, request_id)
    VALUES ('WAITING', dimension, NEW.request_id);
    RETURN NEW;
END;
$$
    LANGUAGE PLPGSQL;

-- TRIGGER TO CREATE GOOD
CREATE TRIGGER create_good_from_request_trigger
    AFTER INSERT
    ON request
    FOR EACH ROW
EXECUTE PROCEDURE create_good_from_request();

-- CHECK TOTAL ORDER WEIGHT
CREATE OR REPLACE FUNCTION check_total_order_weight()
    RETURNS TRIGGER AS
$$
DECLARE
    total_weight REAL;
--     order_id INTEGER;
BEGIN
--     order_id := NEW.order_id;
    total_weight := (SELECT SUM(weight)
                     FROM request
                     WHERE order_id = NEW.order_id);
    IF (total_weight + NEW.weight > 150) THEN
        RETURN NULL;
        ELSE
        RETURN NEW;
    end if;
END;
$$
LANGUAGE PLPGSQL;

CREATE TRIGGER check_total_order_weight_trigger
    AFTER INSERT
    ON request
    FOR EACH ROW
EXECUTE PROCEDURE check_total_order_weight();
