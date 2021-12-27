create temporary table hive.reviewer
(
	reviewer_id varchar(50),
	reviewer_name varchar(50),
	constraint reviewer_pk
		primary key (reviewer_id) disable novalidate
);

