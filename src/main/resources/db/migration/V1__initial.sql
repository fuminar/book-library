create table author
(
    id   binary not null,
    name varchar(255),
    primary key (id)
);

create table author_book
(
    book_id   binary not null,
    author_id binary not null,
    primary key (book_id, author_id)
);

create table book
(
    dtype             varchar(31) not null,
    id                binary      not null,
    name              varchar(255),
    publication_year  integer,
    publisher         varchar(255),
    summary           varchar(255),
    file_format       varchar(255),
    book_inventory_id binary,
    primary key (id)
);

create table book_inventory
(
    id       binary not null,
    quantity bigint,
    primary key (id)
);

alter table author_book
    add constraint FKg7j6ud9d32ll232o9mgo90s57 foreign key (author_id) references author;
alter table author_book
    add constraint FKn8665s8lv781v4eojs8jo3jao foreign key (book_id) references book;
alter table book
    add constraint FKg4fhamwesbgqm2wv7af9bnxou foreign key (book_inventory_id) references book_inventory;