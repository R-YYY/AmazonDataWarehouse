create temporary table hive.director
(
	name varchar(50),
	constraint director_pk
		primary key (name) disable novalidate
);

