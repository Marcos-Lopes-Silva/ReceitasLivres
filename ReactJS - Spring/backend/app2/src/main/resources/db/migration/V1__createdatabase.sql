create table user(

    id bigint not null auto_increment,

    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(100) not null unique,
    tipo varchar(100) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    receitas_id bigint,


    primary key(id)
    
);

create table receitas(
    id bigint not null auto_increment,
    modopreparo varchar(500) not null,
    ingredientes varchar(500) not null,
    user_id bigint not null,

    primary key(id)
    
);

alter table user add foreign key(receitas_id) references receitas(id);

alter table receitas add foreign key(user_id) references user(id);