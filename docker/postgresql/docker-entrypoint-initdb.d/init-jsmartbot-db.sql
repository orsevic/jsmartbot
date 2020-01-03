ALTER SYSTEM SET max_connections = 150;

\connect jsmartbot;

create extension if not exists "uuid-ossp" schema pg_catalog;
create extension if not exists "hstore" schema pg_catalog;

\connect jsmartbot jsmartbot;

create schema "auth";
grant all privileges on                  schema "auth" to jsmartbot;
grant all privileges on all tables    in schema "auth" to jsmartbot;
grant all privileges on all sequences in schema "auth" to jsmartbot;

create schema "bot";
grant all privileges on                  schema "bot" to jsmartbot;
grant all privileges on all tables    in schema "bot" to jsmartbot;
grant all privileges on all sequences in schema "bot" to jsmartbot;