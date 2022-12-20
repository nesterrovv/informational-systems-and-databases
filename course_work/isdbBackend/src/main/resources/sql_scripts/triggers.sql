-- CHECK TOTAL ORDER WEIGHT
CREATE OR REPLACE FUNCTION check_total_order_weight()
    RETURNS TRIGGER
    LANGUAGE PLPGSQL
    AS
'
DECLARE
    total_weight REAL;
BEGIN
    total_weight := (SELECT SUM(weight)
                     FROM request
                     WHERE order_id = NEW.order_id);
    IF (total_weight + NEW.weight > 150) THEN
        RETURN NULL;
    ELSE
        RETURN NEW;
    end if;
END;
';

-- DETERMINE DIMENSIONS FUNCTION --
CREATE OR REPLACE FUNCTION determine_dimensions(width REAL, height REAL, length REAL)
    RETURNS DIMENSIONS
    LANGUAGE PLPGSQL
AS
'
DECLARE
    greatest_dimension REAL;
    result             DIMENSIONS;
BEGIN
    greatest_dimension := GREATEST(width, height);
    greatest_dimension := GREATEST(greatest_dimension, length);
    IF greatest_dimension < 20 THEN
        result := ''S'';
    ELSIF greatest_dimension < 35 THEN
        result := ''M'';
    ELSIF greatest_dimension < 55 THEN
        result := ''L'';
    ELSE
        result := ''XL'';
    END IF;
    RETURN result;
END;
';

-- INSERT GOOD BY REQUEST  --
CREATE OR REPLACE FUNCTION create_good_from_request()
    RETURNS TRIGGER AS
'
DECLARE
    dimension dimensions;
BEGIN
    dimension := determine_dimensions(NEW.width, NEW.height, NEW.length);
    IF TG_OP = ''INSERT'' THEN
        BEGIN
            INSERT INTO good(status, dimensions, request_id)
            VALUES (''WAITING'', dimension, NEW.request_id);
            RETURN NEW;
        END;
    ELSIF TG_OP = ''UPDATE'' THEN
        BEGIN
            UPDATE good
            SET good.dimensions = dimension
            WHERE good_id = NEW.good_id;
            RETURN NEW;
        END;
    END IF;
END;
'
    LANGUAGE PLPGSQL;

-- TRIGGER TO CREATE GOOD
CREATE TRIGGER create_good_from_request_trigger
    AFTER INSERT
    ON request
    FOR EACH ROW
EXECUTE PROCEDURE create_good_from_request();


CREATE TRIGGER check_total_order_weight_trigger
    BEFORE INSERT OR UPDATE
    ON request
    FOR EACH ROW
EXECUTE PROCEDURE check_total_order_weight();

CREATE OR REPLACE FUNCTION check_extra_conditions(order_id INTEGER)
    RETURNS BOOLEAN
    LANGUAGE PLPGSQL
AS
'
DECLARE
    extra_cond_count INTEGER;
BEGIN
    extra_cond_count :=
            (SELECT COUNT(condition)
             FROM request
                      INNER JOIN request_condition USING (request_id)
             WHERE request.order_id = order_id);
    IF extra_cond_count > 0 THEN
        RETURN TRUE;
    ELSE
        RETURN TRUE;
    end if;
END;
';

CREATE OR REPLACE FUNCTION is_courier_compatible(courier_id INTEGER, order_id INTEGER)
    RETURNS BOOLEAN
    LANGUAGE PLPGSQL
AS
'
DECLARE
    courier_rating REAL;
BEGIN
    courier_rating := (SELECT rating
                       FROM courier
                       WHERE courier.courier_id = courier_id);
    IF courier_rating < 3.5 THEN
        BEGIN
            IF check_extra_conditions(order_id) THEN
                RETURN FALSE;
            ELSE
                RETURN TRUE;
            END IF;
        END;
    ELSE
        RETURN TRUE;
    END IF;
END;
';

CREATE OR REPLACE FUNCTION assign_order_to_courier()
    RETURNS TRIGGER
    LANGUAGE PLPGSQL
AS
'
BEGIN
    IF is_courier_compatible(NEW.courier_id, NEW.order_id) THEN
        BEGIN
            UPDATE ordering
            SET status = "DELIVERING"
            WHERE order_id = NEW.order_id;

            UPDATE good
            SET status = "DELIVERING"
            WHERE status = "WAITING"
              AND request_id IN (SELECT request_id
                                 FROM request
                                 WHERE order_id = NEW.order_id);

            RETURN NEW;
        END;
    ELSE
        BEGIN
            RAISE NOTICE ''Courier with id % is incompatible to get order %'', NEW.courier_id, NEW.order_id;
            RETURN NULL;
        END;
    END IF;
END;
';

CREATE TRIGGER assign_order_to_courier_trigger
    BEFORE INSERT OR UPDATE
    ON courier_order
    FOR EACH ROW
EXECUTE PROCEDURE assign_order_to_courier();