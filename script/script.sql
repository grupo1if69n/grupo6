--DROP TABLE usuario;
CREATE TABLE usuario(
	u_nome 		VARCHAR(30) 	NOT NULL,
	u_senha 	VARCHAR(30) 	NOT NULL,
	u_celular 	VARCHAR(20) 	NOT NULL,
	CONSTRAINT u_pk
		PRIMARY KEY(u_nome)
);

--DROP TABLE conta;
CREATE TABLE conta(
	c_id 		SERIAL 		NOT NULL,
	c_nome 		VARCHAR(30) 	NOT NULL,
	c_valor		FLOAT		NOT NULL,
	c_gerente	VARCHAR(30)	NOT NULL,
	CONSTRAINT c_pk 
		PRIMARY KEY(c_id)
);

--DROP TABLE usuario_conta;
CREATE TABLE usuario_conta(
	u_nome 		VARCHAR(30) 	NOT NULL,
	c_id		INTEGER		NOT NULL,
	u_c_valor	FLOAT		NOT NULL,
	CONSTRAINT u_c_pk 
		PRIMARY KEY(u_nome, c_id),
	CONSTRAINT u_nome_fk 
		FOREIGN KEY (u_nome) REFERENCES usuario(u_nome),
	CONSTRAINT c_id_fk 
		FOREIGN KEY (c_id) REFERENCES conta(c_id)
);

create table Produto(
 idProduto Serial primary key,
 nomeProduto varchar(100) not null,
 precoProduto float not null
);

create table usuario_produto(
 u_nome varchar(30) not null,
 produto int not null,
 constraint PK_user_produto primary key(u_nome,produto),
 constraint FK_user_produto foreign key(u_nome) references
 usuario(u_nome),
 constraint FK_user_produto2 foreign key(produto) references
 Produto(idProduto)
);
