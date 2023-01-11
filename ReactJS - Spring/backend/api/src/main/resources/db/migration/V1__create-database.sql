create table usuarios(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    login varchar(100) not null unique,
    ativo tinyint not null,
    receitas_id bigint,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,

    primary key(id)
);

create table receitas(

    id bigint not null auto_increment,
    modopreparo varchar(200),
    ingredientes varchar(100),
    usuario_id bigint,

    primary key(id)
);