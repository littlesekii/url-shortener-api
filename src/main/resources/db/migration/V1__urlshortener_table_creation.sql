create table t_urlref (
    id bigserial primary key,
    urlRef varchar(255) not null,
    urlDest varchar not null
);

create table t_urlref_interaction (
    id bigserial primary key,
    idUrlRef bigserial not null,
    moment timestamp not null
);
