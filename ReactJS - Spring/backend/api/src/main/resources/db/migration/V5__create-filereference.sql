create table file_reference(
    id bigint not null auto_increment,
    content_length bigint not null,
    content_type varchar(100) not null,
    created_at date,
    file_name varchar(500) not null,
    temp bit not null,
    type varchar(100) not null,
    primary key(id)
);

alter table receitas add column photo_id bigint;
alter table receitas add foreign key (photo_id) references file_reference(id);
alter table usuarios add column photo_id bigint;
alter table usuarios add foreign key (photo_id) references file_reference(id);
