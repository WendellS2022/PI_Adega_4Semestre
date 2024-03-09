create database Adega;
use  Adega;

create table Users(
UserID int primary key auto_increment,
Nome varchar(100),
Email nvarchar(100),
Senha varchar(150), 
CPF VARCHAR(11),
Situacao bit,
Grupo smallint not null
);

create table Produtos(
ProdutoID int primary key auto_increment,
Nome varchar(50), 
Quantidade int not null,
Avaliacao smallint not null,
Descricao varchar (2000),
Preco decimal (20, 2),
Situacao bit 
);


create table Imagens(
ImagemID int primary key auto_increment,
ProdutoId int not null,
foreign key (ProdutoId) references Produtos(ProdutoID),
Diretorio varchar(100),
Nome varchar(100),
Extensao varchar(10)
);
