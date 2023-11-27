-- Banco de dados: segb12
--
CREATE DATABASE segb12;
USE segb12;

CREATE TABLE b12 (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  resultado varchar(255) NOT NULL
);


CREATE TABLE medicos (
  id int(11) Primary key AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  cpf varchar(11) NOT NULL,
  especialidade varchar(255) DEFAULT NULL
);

CREATE TABLE pacientes (
  id int(11) Primary key AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  cpf varchar(11) NOT NULL
);


CREATE TABLE senhas (
  id int(11) Primary key AUTO_INCREMENT,
  chave_secreta varchar(255) NOT NULL
);


CREATE TABLE usuarios (
id int(11) Primary key AUTO_INCREMENT,
  login varchar(255) NOT NULL,
  senha varchar(255) NOT NULL
);


CREATE TABLE valorespadroes (
  id int(11) NOT NULL,
  limite float DEFAULT NULL,
  unidade varchar(255) NOT NULL,
  referencia float NOT NULL
);

INSERT INTO valorespadroes (id,limite, unidade, referencia)
VALUES (1, 900, 'pg/mL', 200);


