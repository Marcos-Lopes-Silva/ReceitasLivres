alter table receitas add titulo varchar(100);
alter table receitas add descricao varchar(250);
alter table receitas add serve int;
create table categorias(

    id bigint not null auto_increment,
    nome varchar(100),
    receitas_id bigint,

    primary key(id)
);
alter table receitas add categoria_id bigint;
alter table receitas add foreign key (categoria_id) references categorias(id);
alter table categorias add foreign key (receitas_id) references receitas(id);
alter table receitas add urlImage varchar(500);
alter table receitas add size varchar(50);