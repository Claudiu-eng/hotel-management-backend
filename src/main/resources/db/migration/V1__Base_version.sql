create table hotel
(
    id        bigint       not null
        primary key,
    latitude  double       null,
    longitude double       null,
    name      varchar(255) null
);

create table room
(
    id           binary(16) not null
        primary key,
    is_available bit        null,
    price        float        null,
    room_number  int        null,
    type         tinyint    null,
    hotel        bigint     not null,
    constraint FKtk0m49c4l9t3rppjso6909g4m
        foreign key (hotel) references hotel (id)
);

create table _user
(
    email    varchar(255) not null
        primary key,
    name     varchar(255) null,
    password varchar(255) null
);





create table feedback
(
    rating  int          null,
    date    bigint       null,
    hotel   bigint       not null,
    id      binary(16)   not null
        primary key,
    comment varchar(255) null,
    user    varchar(255) not null,
    constraint FKl1bia38d3q5xyh6hjv0dbpmfc
        foreign key (hotel) references hotel (id),
    constraint FKn6c5lcxu5qyk33c085w8dma0g
        foreign key (user) references _user (email)
);



create table booking
(
    date bigint       null,
    id   binary(16)   not null
        primary key,
    room binary(16)   not null,
    user varchar(255) not null,
    constraint FK64jp8eqj2csc9q8lkmeviwk5v
        foreign key (user) references _user (email),
    constraint FK68j4dbcfhkjibnpw3dcbh9clb
        foreign key (room) references room (id)
);








