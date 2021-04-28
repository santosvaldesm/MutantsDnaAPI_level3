CREATE DATABASE IF NOT EXISTS DB;
USE DB;
CREATE TABLE IF NOT EXISTS dna_records (id int NOT NULL AUTO_INCREMENT primary key, dna varchar(2000) NOT NULL, is_mutant boolean);
