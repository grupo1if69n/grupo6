CREATE TABLE usuario(
	u_nome 		varchar(30) 	not null,
	u_senha 	varchar(30) 	not null,
	u_celular 	varchar(20) 	not null,
	constraint u_pk
		primary key(u_nome)
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

create table Conta(
 idConta Serial primary key,
 nomeConta varchar(100) not null,
 valorTotal float not null
);
