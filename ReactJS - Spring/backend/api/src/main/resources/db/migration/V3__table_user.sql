create table user_seq(
    
    next_val bigint
    

);

insert into user_seq (next_val) value(0);

create table hibernate_sequence(
    next_val bigint
);

insert into hibernate_sequence (next_val) value (0);

create table admin(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(100) not null unique,
    cpf varchar(11) not null unique,

    primary key(id)
);