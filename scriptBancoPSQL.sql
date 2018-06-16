--CRIA O BANCO DE DADOS
CREATE DATABASE casaBancaria;

--ACESSO O BANCO DE DADOS
\c casabancaria

--CRIA A TABELA DE ATENDENTES
CREATE TABLE atendente(
	login varchar(30) NOT NULL,
	nome varchar(60) NOT NULL,
	senha varchar(15) NOT NULL,
	CONSTRAINT pkAtendente PRIMARY KEY (login)
);

--CRIA A TABELA DE CLIENTE
CREATE TABLE cliente(
	cpf varchar(11) NOT NULL,
	nome varchar(60) NOT NULL,
	tipo int NOT NULL,
	telefone varchar(11) NOT NULL,
	prestacaoTerceiro numeric(7,2),
	renda numeric(8,2) NOT NULL,
	email varchar(50),
	cep numeric(8) NOT NULL,
	logradouro varchar(40) NOT NULL,
	numero int,
	complemento varchar(40),
	bairro varchar(20) NOT NULL,
	cidade varchar(20) NOT NULL,
	estado char(2) NOT NULL,
	atendente varchar(30),
	CONSTRAINT pkCliente PRIMARY KEY (cpf),
	CONSTRAINT fkClienteAtendente FOREIGN KEY (atendente) REFERENCES atendente (login)
);

--CRIA A TABELA DE EMPRESTIMOS
CREATE TABLE emprestimo(
	codigo int NOT NULL,
	cliente varchar(11),
	atendente varchar(30),
	txJuros numeric(5,4) NOT NULL,
	unidMedida char(1) NOT NULL,
	valorEmprestimo numeric(9,2) NOT NULL,
	prazo int NOT NULL,
	tipo char(1) NOT NULL,
	valorAmortiz numeric(9,2),
	valorParcela numeric(9,2),
	CONSTRAINT pkEmprestimo PRIMARY KEY (codigo),
	CONSTRAINT fkEmprestimoCliente FOREIGN KEY (cliente) REFERENCES cliente(cpf),
	CONSTRAINT fkEmprestimoAtendente FOREIGN KEY (atendente) REFERENCES atendente(login)
);

--CRIA A TABELA DE REGISTRO DOS LOGS
CREATE TABLE registraLog(
	tabela varchar(25),
	data date,
	usuario varchar(30),
	op varchar(10)
);


--CRIAÇÃO DA FUNÇÃO QUE A TRIGGER DE CLIENTE ACIONARÁ
CREATE OR REPLACE FUNCTION salvaLogCliente() 
RETURNS TRIGGER AS
$BODY$
BEGIN
IF (TG_OP = 'INSERT') THEN
	INSERT INTO registraLog VALUES	('cliente', CURRENT_TIMESTAMP, new.atendente, 'INSERT');
RETURN NEW;
ELSIF (TG_OP = 'UPDATE') THEN
	INSERT INTO registraLog VALUES	('cliente', CURRENT_TIMESTAMP, new.atendente, 'UPDATE');
RETURN NEW;
ELSIF (TG_OP = 'DELETE') THEN
	INSERT INTO registraLog VALUES	('cliente', CURRENT_TIMESTAMP, old.atendente, 'DELETE');
RETURN OLD;
END IF;
RETURN null;
END;
$BODY$ LANGUAGE 'plpgsql';

--CRIAÇÃO DA TRIGGER DE CLIENTE
CREATE TRIGGER triggerClienteLog
AFTER INSERT OR UPDATE OR DELETE ON cliente
FOR EACH ROW
EXECUTE PROCEDURE salvaLogCliente();


--CRIAÇÃO DA FUNÇÃO QUE A TRIGGER DE EMPRESTIMOS ACIONARÁ
CREATE OR REPLACE FUNCTION salvaLogEmprestimo() 
RETURNS TRIGGER AS
$BODY$
BEGIN
IF (TG_OP = 'INSERT') THEN
	INSERT INTO registraLog VALUES	('emprestimo', CURRENT_TIMESTAMP, new.atendente, 'INSERT');
RETURN NEW;
ELSIF (TG_OP = 'DELETE') THEN
	INSERT INTO clienteLog VALUES	('emprestimo', CURRENT_TIMESTAMP, old.atendente, 'DELETE');
RETURN OLD;
END IF;
RETURN null;
END;
$BODY$ LANGUAGE 'plpgsql';

--CRIAÇÃO DA TRIGGER DE EMPRESTIMO
CREATE TRIGGER triggerEmprestimoLog
AFTER INSERT OR DELETE ON emprestimo
FOR EACH ROW
EXECUTE PROCEDURE salvaLogEmprestimo();

--INSERE O ATENDENTE PADRÃO
INSERT INTO atendente VALUES ('admin', 'admin', 'admin');

--INSERE UM CLIENTE INICIAL
INSERT INTO cliente
VALUES('191', 'Camila Freitas', 3, 629000, null, 100.6, 'camila@a.com.br', 74000000,
	'Rua 1', 0, 'Qd. 02', 'Jardim', 'Goiânia', 'GO', 'admin');
