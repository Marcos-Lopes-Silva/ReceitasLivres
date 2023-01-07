alter table user_tipo add foreign key(user_id) references user(id);
alter table user_tipo add foreign key(perfil_id) references perfil(id);