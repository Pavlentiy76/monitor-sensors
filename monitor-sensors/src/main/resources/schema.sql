
create type sensor_type as enum ('PRESSURE', 'TEMPERATURE', 'VOLTAGE', 'HUMIDITY');

create type sensor_unit as enum ('BAR', 'VOLTAGE', 'CELSIUS', 'PERCENT');

create table if not exists ranges (
    id              SERIAL       PRIMARY KEY,
    range_from      int4,
    range_to        int4
);

create table if not exists sensors (
    id          SERIAL       PRIMARY KEY,
    location    varchar(40),
    model       varchar(15)  not null,
    title       varchar(30)  not null,
    sensor_type sensor_type,
    unit        sensor_unit,
    description varchar(200),
    range_id    bigint       not null,
    constraint fk_range foreign key(range_id) references ranges(id) on delete cascade on update cascade
);