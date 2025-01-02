
create database flight_java;
use flight_java;

create table flights
(
    flight_id       int auto_increment
        primary key,
    flight_number   int                        not null,
    origin          varchar(255)               not null,
    destination     varchar(255)               not null,
    departure_time  time                       null,
    arrival_time    time                       null,
    available_seats int                        null,
    total_seats     int                        not null,
    created_at      datetime default curtime() null,
    update_at       datetime default curtime() null
);

create table users
(
    user_id      int auto_increment
        primary key,
    first_name   varchar(100)           not null,
    last_name    varchar(100)           not null,
    phone_number varchar(13)            not null,
    email        varchar(255)           not null,
    nationality  varchar(255)           not null,
    passport     varchar(255)           not null,
    password     varchar(255)           not null,
    created_at   time default curtime() not null,
    update_at    time default curtime() not null,
    constraint email
        unique (email),
    constraint passport
        unique (passport)
);

create table reservations
(
    reservation_id int auto_increment
        primary key,
    seat_number    int                                                   null,
    status         enum ('pending', 'approved', 'available', 'declined') null,
    user_id        int                                                   null,
    flight_id      int                                                   null,
    constraint reservations_ibfk_1
        foreign key (user_id) references users (user_id),
    constraint reservations_ibfk_2
        foreign key (flight_id) references flights (flight_id)
);

create index flight_id
    on reservations (flight_id);

create index user_id
    on reservations (user_id);


