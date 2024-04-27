create database Adega;
use  Adega;

create table Usuarios(
UsuarioID int primary key auto_increment,
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
Valor decimal (20, 2),
Situacao bit 
);


create table Imagens(
ImagemID int primary key auto_increment,
ProdutoId int not null,
foreign key (ProdutoId) references Produtos(ProdutoID),
Diretorio varchar(100),
Nome varchar(100),
Qualificacao bit,
Extensao varchar(10)
);

create table Clientes (
    IdCliente int primary key auto_increment,
    Nome varchar(255),
    DataNascimento Date,
    CPF varchar(11),
    EMAIL varchar(80),
    SENHA varchar(100),
    GENERO varchar(20)
);

create table Endereco (
    IdEndereco int primary key auto_increment,
    CEP varchar(10),
    Logradouro varchar(255),
    Numero int,
    Complemento varchar(255),
    Bairro varchar(80),
    Cidade varchar(100),
    UF varchar(2),
    Status bit,
    PADRAO bit,
    EnderecoFaturamento bit,
    IdCliente int,
    foreign key (IdCliente) references Clientes(IdCliente)
);

create table Carrinho (
    IdCarrinho int not null,
    ProdutoId int not null,
    foreign key (ProdutoID) references Produtos(ProdutoID),
    IdCliente int,
    foreign key (IdCliente) references Clientes(IdCliente),
    Quantidade int,
    NomeProduto varchar(100),
    Descricao varchar(2000),
    Valor decimal(20, 2)
);