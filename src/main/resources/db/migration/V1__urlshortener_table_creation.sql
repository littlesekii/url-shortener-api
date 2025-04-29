create table t_urlref (
    id bigserial primary key,
    url_ref varchar(255) not null,
    url_dest varchar not null
);

create table t_urlref_interaction (
    id bigserial primary key,
    id_url_ref bigserial not null,
    moment timestamp not null
);
