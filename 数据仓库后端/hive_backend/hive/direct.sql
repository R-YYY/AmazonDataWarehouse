create temporary table hive.direct
(
	movie_id varchar(50),
	director_name varchar(50),
	constraint direct_pk
		primary key (movie_id) disable novalidate
);

