
-- jsp10
create table BIKE_TB(
	ADDR_GU VARCHAR(4000),
	CONTENT_ID INT primary key,
	CONTENT_NM VARCHAR(4000),
	NEW_ADDR VARCHAR(4000),
	CRADLE_COUNT INT,
	LONGITUDE INT,
	LATITUDE INT
);
select *from BIKE_TB;