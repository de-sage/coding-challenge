#!/bin/bash
set -e
psql -v ON_ERROR_STOP=1 --username postgres --dbname $POSTGRES_DB <<-EOSQL
    CREATE ROLE "portal" WITH PASSWORD 'userAdmin@123';
    CREATE DATABASE user_portal_db OWNER="portal";
    GRANT ALL PRIVILEGES ON DATABASE user_portal_db to portal;
    ALTER ROLE "portal" WITH LOGIN;
EOSQL
