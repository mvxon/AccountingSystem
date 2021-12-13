CREATE DATABASE accounting_system ENCODING 'UTF-8';


CREATE TABLE IF NOT EXISTS houses (
  id   SERIAL PRIMARY KEY,
  houseNumber INTEGER NOT NULL,
  entrancesCount INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS entrances (
  id       SERIAL PRIMARY KEY,
  houseId INTEGER NOT NULL,
  entranceNumber INTEGER NOT NULL,
  floorsCount INTEGER NOT NULL,
  FOREIGN KEY (houseId) REFERENCES houses (id)
);

CREATE TABLE IF NOT EXISTS floors (
    id SERIAL PRIMARY KEY,
    entranceId INTEGER NOT NULL,
    floorNumber INTEGER NOT NULL,
    flatsCount INTEGER NOT NULL,
    FOREIGN KEY(entranceId) REFERENCES entrances(id)
);
CREATE TABLE IF NOT EXISTS flats (
    id SERIAL PRIMARY KEY,
    floorId INTEGER NOT NULL,
    flatNumber INTEGER NOT NULL,
    roomsCount INTEGER NOT NULL,
    residentsCount INTEGER NOT NULL,
    FOREIGN KEY(floorId) REFERENCES floors(id)
);
CREATE TABLE IF NOT EXISTS rooms (
    id SERIAL PRIMARY KEY,
    flatId INTEGER NOT NULL,
    roomNumber INTEGER NOT NULL,
    roomSquare REAL NOT NULL,
    FOREIGN KEY(flatId) REFERENCES flats(id)
);
