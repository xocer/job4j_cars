create database site_cars;

create table brands
(
    id   serial primary key,
    name varchar not null
);
create table models
(
    id   serial primary key,
    name varchar not null
);
create table photos
(
    id   serial primary key,
    path varchar not null
);

create table users
(
    id serial primary key,
    name varchar not null ,
    email varchar not null UNIQUE ,
    password varchar not null
);

create table announcements
(
    id          serial primary key,
    description text not null,
    brand_id    int not null references brands (id),
    model_id    int not null references models (id),
    photo_id    int not null references photos (id),
    sold      boolean default false
);
