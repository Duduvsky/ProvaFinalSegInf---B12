-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 27/11/2023 às 22:43
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: segdb
--
CREATE DATABASE IF NOT EXISTS segb12 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE segb12;

-- --------------------------------------------------------

--
-- Estrutura para tabela b12
--

CREATE TABLE b12 (
  id int(11) NOT NULL,
  resultado varchar(80) NOT NULL
) 

-- --------------------------------------------------------

--
-- Estrutura para tabela medicos
--

CREATE TABLE medicos (
  id int(11) NOT NULL,
  nome varchar(255) NOT NULL,
  cpf varchar(11) NOT NULL,
  especialidade varchar(255) DEFAULT NULL
)

-- --------------------------------------------------------

--
-- Estrutura para tabela pacientes
--

CREATE TABLE pacientes (
  id int(11) NOT NULL,
  nome varchar(255) NOT NULL,
  cpf varchar(11) NOT NULL
)

-- --------------------------------------------------------

--
-- Estrutura para tabela senhas
--

CREATE TABLE senhas (
  id int(11) NOT NULL,
  chave_secreta varchar(255) NOT NULL
)

-- --------------------------------------------------------

--
-- Estrutura para tabela usuarios
--

CREATE TABLE usuarios (
  id int(11) NOT NULL,
  login varchar(80) NOT NULL,
  senha varchar(255) NOT NULL
)

-- --------------------------------------------------------

--
-- Estrutura para tabela valorespadroes
--

CREATE TABLE valorespadroes (
  id int(11) NOT NULL,
  limite double NOT NULL,
  unidade varchar(255) NOT NULL,
  referencia double NOT NULL
)

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela b12
--
ALTER TABLE b12
  ADD PRIMARY KEY (id);

--
-- Índices de tabela medicos
--
ALTER TABLE medicos
  ADD PRIMARY KEY (id);

--
-- Índices de tabela pacientes
--
ALTER TABLE pacientes
  ADD PRIMARY KEY (id);

--
-- Índices de tabela senhas
--
ALTER TABLE senhas
  ADD PRIMARY KEY (id);

--
-- Índices de tabela usuarios
--
ALTER TABLE usuarios
  ADD PRIMARY KEY (id);

--
-- Índices de tabela valorespadroes
--
ALTER TABLE valorespadroes
  ADD PRIMARY KEY (id);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela b12
--
ALTER TABLE b12
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela medicos
--
ALTER TABLE medicos
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela pacientes
--
ALTER TABLE pacientes
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela usuarios
--
ALTER TABLE usuarios
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela senhas
--
ALTER TABLE senhas
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

--
-- INSERT tabela valorespadroes
--
INSERT INTO valorespadroes(id, limite, unidade, referencia) VALUES (1, 900, 'pg/mL', 200);


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
