create temporary table hive.review
(
	review_id int,
	reviewer_id varchar(50),
	movie_id varchar(50),
	`date` varchar(255),
	score varchar(255),
	text string,
	summary string,
	constraint review_pk
		primary key (review_id) disable novalidate
);

