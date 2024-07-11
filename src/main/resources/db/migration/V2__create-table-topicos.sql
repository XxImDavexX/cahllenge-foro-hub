create table topicos(

	id bigint not null auto_increment,
	titulo varchar(100) not null unique,
	mensaje varchar(500) not null unique,
	fechaCreacion datetime not null,
	estado tinyint not null,
	curso_id bigint not null,

	primary key(id),

	constraint fk_topicos_curso_id foreign key(curso_id) references cursos(id)

);