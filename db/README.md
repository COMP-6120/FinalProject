# Database Design and Setup

**ER diagram** was created using [ERDPlus](https://erdplus.com/standalone).

## 1. Create new database:
```
CREATE DATABASE group10;
USE group10;
```
## 2. Run SQL file in MariaDB command line:
```
source full_path_to_sql_file
```
## 3. Load data into tables:
```
LOAD DATA LOCAL INFILE 'full_path_to_csv_file' INTO TABLE db_name.table_name FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n' IGNORE 1 LINES (columns, columns, ...);
```
> On Windows, backslashes need to be escaped.
> If order of columns are the same the last part, (columns, columns, ...) can be ignored.

## 4. Dump and restore:
```
mysqldump -u [user] -p database_name > filename.sql
mysql -u [user] -p newdatabase < database_name.sql
```