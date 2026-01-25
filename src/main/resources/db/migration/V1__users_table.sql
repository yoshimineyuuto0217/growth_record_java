create table users (
    id bigint generated always as identity primary key,
    email varchar(255) not null,
    name varchar(100) not null,
    profile_image varchar(255),
    self_introduction varchar(500),
    password varchar(255) not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);
