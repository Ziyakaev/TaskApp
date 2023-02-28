CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
DROP TABLE IF EXISTS tasks CASCADE;
CREATE TABLE tasks (
    id uuid DEFAULT public.uuid_generate_v4() not null,
    title varchar,
    notes varchar,
    created timestamp not null,
    updated varchar,
    parent varchar,
    status varchar,
    deleted boolean,
    due varchar,
    completed varchar,
    hidden boolean
);

select * from tasks;