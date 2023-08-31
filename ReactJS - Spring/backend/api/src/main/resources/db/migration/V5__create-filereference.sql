create table file_reference(
    id bigint not null auto_increment,
    content_length numeric not null,
    content_type varchar(100) not null,
    created_at date,
    name varchar(500) not null,
    temp smallint not null,
    type varchar(100) not null,
    primary key(id)
);

alter table receitas add column photo_id bigint;
alter table receitas add foreign key (photo_id) references file_reference(id);