alter table pacientes add column ativo tinyint;
update pacientes set ativo = 1;
alter table pacientes alter column ativo tinyint not null;