create table topicos(

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(100) not null,
    fecha_creacion datetime not null,
    status varchar(100) not null,
    autor varchar(100) not null,
    curso varchar(100) not null,
    respuestas varchar(100) not null,

    primary key(id)

);