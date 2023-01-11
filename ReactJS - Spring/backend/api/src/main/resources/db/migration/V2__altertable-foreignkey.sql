alter table usuarios add foreign key(receitas_id) references receitas(id);

alter table receitas add foreign key(usuario_id) references usuarios(id);