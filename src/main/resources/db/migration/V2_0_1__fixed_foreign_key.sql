create table t_short_url (
    id bigserial primary key,
    short_hash varchar(5) not null,
    short_url varchar(255) not null,
    target_url varchar not null
);

create table t_short_url_interaction (
    id bigserial primary key,
    id_short_url bigint not null references t_short_url(id),
    moment timestamp not null
);

drop table t_urlref;
drop table t_urlref_interaction;