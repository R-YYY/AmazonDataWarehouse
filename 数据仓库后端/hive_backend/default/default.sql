-- we don't know how to generate root <with-no-name> (class Root) :(
create schema default
	comment 'Default Hive database'
	location 'hdfs://master:9000/user/hive/warehouse';

