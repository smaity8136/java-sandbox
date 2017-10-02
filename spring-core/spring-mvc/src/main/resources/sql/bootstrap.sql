CREATE TABLE IF NOT EXISTS member (
id bigint PRIMARY KEY,
username character varying(255),
password character varying(255),
email character varying(255),
registration_date timestamp,
active boolean
);

CREATE TABLE IF NOT EXISTS weapon (
id bigint PRIMARY KEY,
name character varying(255),
description character varying(255),
damage_indicator int,
price float,
in_stock boolean
);

INSERT INTO weapon (id, name, description, damage_indicator, price, in_stock) VALUES(1, 'Bazooka', 'Blow sh1t up!', 10, 9500.00, true);
INSERT INTO weapon (id, name, description, damage_indicator, price, in_stock) VALUES(2, 'Dynamite', 'Blow bigger sh1t up!', 25, 18000.00, true);
INSERT INTO weapon (id, name, description, damage_indicator, price, in_stock) VALUES(3, 'AK 47', 'Never jams', 3, 2480.40, true);
INSERT INTO weapon (id, name, description, damage_indicator, price, in_stock) VALUES(4, 'Chainsaw', 'Cut his head off!', 5, 3790.56, false);
INSERT INTO weapon (id, name, description, damage_indicator, price, in_stock) VALUES(5, 'Desert Eagle', 'Put some holes in em', 2, 1799.00, true);
INSERT INTO weapon (id, name, description, damage_indicator, price, in_stock) VALUES(6, 'Grenade', 'Torso Ripper', 4, 700.00, false);
