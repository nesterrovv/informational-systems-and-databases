CREATE TABLE IF NOT EXISTS water (
  id serial PRIMARY KEY, 
  composition varchar(150) NOT NULL, 
  temperature smallint NOT NULL, 
  CHECK(
    temperature > 0 
    AND temperature < 100
  )
);
CREATE TABLE IF NOT EXISTS object_of_influence (
  id serial PRIMARY KEY, 
  description varchar(150) NOT NULL, 
  effect_of_influence varchar(150) NOT NULL
);
CREATE TABLE IF NOT EXISTS location (
  id serial PRIMARY KEY, 
  description varchar(50) NOT NULL, 
  area real NOT NULL, 
  CHECK(
    area > 0 
    AND area <= 20
  )
);
CREATE TABLE IF NOT EXISTS wind (
  id serial PRIMARY KEY, 
  direction varchar(50) NOT NULL, 
  speed smallint NOT NULL, 
  object_of_influence_id integer REFERENCES object_of_influence, 
  CHECK(
    speed > 0 
    AND speed <= 120
  )
);
CREATE TABLE IF NOT EXISTS rain (
  id serial PRIMARY KEY, 
  rain_force smallint NOT NULL, 
  duration smallint NOT NULL, 
  water_id integer UNIQUE REFERENCES water, 
  location_id integer UNIQUE REFERENCES location, 
  wind_id integer REFERENCES wind, 
  CHECK(
    (
      rain_force >= 0 
      AND rain_force <= 5
    ) 
    AND (
      duration > 0 
      AND duration <= 365
    )
  )
);
CREATE TABLE IF NOT EXISTS illusion (
  id serial PRIMARY KEY, 
  description varchar(50) NOT NULL, 
  is_sharp boolean NOT NULL, 
  rain_id integer UNIQUE REFERENCES rain
);
