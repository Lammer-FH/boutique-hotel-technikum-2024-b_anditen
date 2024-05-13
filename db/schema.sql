create table bed_types
(
    id          varchar(255) not null
        primary key,
    name        tinytext     not null,
    description text         not null
);

create table guests
(
    id         int auto_increment
        primary key,
    first_name text not null,
    last_name  text not null,
    birthday   date not null,
    account    uuid null,
    constraint guests_account_key
        unique (account)
);

create table room_features
(
    feature varchar(255) not null
        primary key,
    name    tinytext     not null
);

create table room_types
(
    type        varchar(255) not null
        primary key,
    name        tinytext     not null,
    description text         not null
);

create table rooms
(
    id    int auto_increment
        primary key,
    hotel int          not null,
    type  varchar(255) null,
    constraint rooms_room_types_type_fk
        foreign key (type) references room_types (type)
);

create table beds_in_rooms
(
    room_id  int           not null,
    bed_type varchar(255)  null,
    count    int default 1 not null,
    constraint beds_in_rooms_bed_types_id_fk
        foreign key (bed_type) references bed_types (id),
    constraint beds_in_rooms_rooms_id_fk
        foreign key (room_id) references rooms (id)
);

create table room_booking
(
    id            int auto_increment
        primary key,
    room          int        not null,
    from_date     date       not null,
    to_date       date       not null,
    primary_guest int        not null,
    guest_count   int        not null,
    breakfast     tinyint(1) not null,
    constraint room_booking_guests_id_fk
        foreign key (primary_guest) references guests (id),
    constraint room_booking_rooms_id_fk
        foreign key (room) references rooms (id),
    constraint dates_sane
        check (`to_date` >= `from_date`)
);

create table rooms_to_features
(
    room    int          not null,
    feature varchar(255) null,
    constraint rooms_to_features_room_features_feature_fk
        foreign key (feature) references room_features (feature),
    constraint rooms_to_features_rooms_id_fk
        foreign key (room) references rooms (id)
);

