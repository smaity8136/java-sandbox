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
