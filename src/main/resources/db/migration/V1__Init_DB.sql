CREATE SEQUENCE company_id_seq
    START 1
    INCREMENT 1
    MAXVALUE 9223372036854775807
    MINVALUE 1
    CACHE 1;


CREATE TABLE IF NOT EXISTS company (
       id BIGINT DEFAULT nextval('company_id_seq') PRIMARY KEY,
       is_enabled boolean not null,
       name varchar(2048),
       symbol VARCHAR(255)
);


CREATE SEQUENCE stock_id_seq
    START 1
    INCREMENT 1
    MAXVALUE 9223372036854775807
    MINVALUE 1
    CACHE 1;


CREATE TABLE IF NOT EXISTS stock (
     id BIGINT DEFAULT nextval('stock_id_seq') PRIMARY KEY,
     symbol VARCHAR(255) not null,
     change double precision,
     latest_price double precision NOT NULL,
     previous_volume BIGINT NOT NULL,
     volume BIGINT,
     company_name VARCHAR(2048) NOT NULL
);


CREATE TYPE dml_type AS ENUM ('INSERT', 'UPDATE', 'DELETE');

CREATE TABLE IF NOT EXISTS stock_audit_log (
    id serial PRIMARY KEY,
    symbol VARCHAR(255),
    old_row_data JSONB,
    new_row_data JSONB,
    dml_type dml_type,
    dml_timestamp TIMESTAMP
    );



CREATE OR REPLACE FUNCTION stock_audit_trigger_func()
RETURNS trigger AS $body$
BEGIN
  if (TG_OP = 'INSERT') then
      INSERT INTO stock_audit_log (
           id,
           symbol,
           old_row_data,
           new_row_data,
           dml_type,
           dml_timestamp
           )
      VALUES(
          NEW.id,
          NEW.symbol,
          null,
          to_jsonb(NEW),
          TG_OP::dml_type,
          CURRENT_TIMESTAMP
      );
RETURN NEW;

elsif (TG_OP = 'UPDATE') then
      INSERT INTO stock_audit_log (
           id,
           symbol,
           old_row_data,
           new_row_data,
           dml_type,
           dml_timestamp
      )
      VALUES(
          NEW.id,
          NEW.symbol,
          to_jsonb(OLD),
          to_jsonb(NEW),
          TG_OP::dml_type,
          CURRENT_TIMESTAMP
      );
RETURN NEW;

end if;
RETURN NULL;
END;
$body$
LANGUAGE plpgsql;

CREATE TRIGGER stock_audit_trigger
AFTER INSERT OR UPDATE ON stock
FOR EACH ROW EXECUTE FUNCTION stock_audit_trigger_func();