# Database design and setup

## Create new database
```
CREATE DATABASE group10;
USE group10;
```
## Run sql file in MariaDB command line
```
source full_path_to_sql_file
```
## Load data into tables
```
LOAD DATA LOCAL INFILE 'full_path_to_csv_file' INTO TABLE db_name.table_name FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n' IGNORE 1 LINES (columns, columns, ...);
```
> On windows backslashes need to be escaped

> If order of columns are the same the last part (columns, columns, ...) can be ignored