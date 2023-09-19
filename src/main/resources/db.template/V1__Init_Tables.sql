CREATE TABLE IF NOT EXISTS company (
    id serial PRIMARY KEY,
    is_enabled boolean not null,
    name varchar(2048),
    symbol VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS stock (
    id serial PRIMARY KEY,
    symbol VARCHAR(255) not null,
    change NUMERIC(38,2),
    latest_price NUMERIC(38,2)NOT NULL,
    previous_volume INT NOT NULL,
    volume INT,
    company_name VARCHAR(2048) NOT NULL
    );