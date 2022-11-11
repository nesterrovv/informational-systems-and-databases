CREATE INDEX "order_departure_index" ON ordering USING BTREE(departure_point_id);
CREATE INDEX "order_destination_index" ON ordering USING BTREE(destination_point_id);
CREATE INDEX "order_status_index" ON ordering USING BTREE(status);
CREATE INDEX "courier_rating_index" ON courier USING BTREE(rating);