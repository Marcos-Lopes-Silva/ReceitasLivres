create table receitas(
    id bigint not null auto_increment,
    titulo varchar(255) not null,
    ingredientes varchar(255) not null,
    modo_preparo varchar(255) not null,
    cliente_id bigint not null,

    primary key(id),
    constraint fk_cliente foreign key(cliente_id) references clientes(cliente_id)
     
);

alter table clientes add column receitas_id bigint not null;

alter table clientes add constraint fk_receitas foreign key (receitas_id) references receitas(receitas_id);