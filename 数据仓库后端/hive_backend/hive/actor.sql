create temporary table hive.actor
(
	name varchar(50),
	constraint actor_pk
		primary key (name) disable novalidate
);

