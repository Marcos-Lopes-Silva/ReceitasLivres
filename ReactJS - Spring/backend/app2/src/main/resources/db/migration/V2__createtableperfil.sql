create table perfil(
    id bigint not null auto_increment,
    nome varchar(100) not null,

    primary key(id)
);

alter table user modify column tipo bigint not null;

alter table user add foreign key(tipo) references perfil(id);