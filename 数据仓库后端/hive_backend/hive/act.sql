create temporary table hive.act
(
	movie_id varchar(50),
	actor_name varchar(50),
	constraint act_pk
		primary key (actor_name) disable novalidate
);

