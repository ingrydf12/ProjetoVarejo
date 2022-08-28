-- CRIANDO BD PARA PROJETO GRUPO B
 
create database dbvarejo; -- CRIANDO ESSE BD PARA TRABALHAR
use dbvarejo;-- USANDO O BD
create table tbusuarios( -- TABELA SIMILAR A DO PROFESSOR
iduser int primary key,
usuario varchar(50) not null,
fone varchar(15),
login varchar(15) not null unique,
senha varchar(15) not null
);


USE dbvarejo; -- USANDO O BD
CREATE TABLE tbclientes ( -- CRIANDO TABELA CLIENTES
id INT KEY AUTO_INCREMENT,
nomecli VARCHAR (50) NOT NULL,
cpf VARCHAR (11),
fonecli VARCHAR (13) NOT NULL,
email VARCHAR (50),
data_nasc DATE, 
endcli VARCHAR (100)
);

use dbvarejo;-- USANDO O BD
create table tbprodutos( -- CRIANDO TABELA PRODUTOS
idproduto int  unique auto_increment,
categoria varchar(20),
descricao varchar(50) ,
fornecedor varchar(20) ,
tamanho varchar(20) ,
cor varchar(20) ,
custo varchar(20),
venda varchar(20) 
);

create table tbvendas(
idvenda int key auto_increment,
categoria varchar (20),
descricao_item varchar (30) not null,
vendedor varchar (20),
valor_custo int not null,
valor_venda int not null,
lucro int,
estoque int not null);
-- lucro é o txtVendaLucro dado no próprio programa

-- PARA TESTE
insert into tbvendas
(idvenda, categoria, descricao_item, vendedor, valor_custo, valor_venda, lucro, estoque)
values
('1', 'roupa', 'cor azul manga longa', 'vinicius vasconcelos', '123', '124', '1.0', '12');

-- alteração bd
use dbvarejo;
ALTER TABLE tbclientes RENAME COLUMN id to idcli;
ALTER TABLE tbclientes ADD bairro VARCHAR(30) AFTER endcli;
ALTER TABLE tbclientes ADD municipio VARCHAR(30) AFTER bairro;
ALTER TABLE tbclientes ADD cep VARCHAR(8) AFTER municipio;
