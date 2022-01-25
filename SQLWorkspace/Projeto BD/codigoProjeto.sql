
--criacao de tabelas

drop table parques cascade constraints;
drop table zonas cascade constraints;
drop table locais cascade constraints;
drop table lojas cascade constraints;
drop table atracoes cascade constraints;
drop table restaurantes cascade constraints;
drop table vendas cascade constraints;
drop table avaliacoes cascade constraints;
drop table pessoas cascade constraints;
drop table funcionarios cascade constraints;
drop table visitantes cascade constraints;
drop table artigos cascade constraints;
drop table lembrancas cascade constraints;
drop table comidas cascade constraints;

create table parques(
  idParque number(4,0),
  nomeParque varchar2(30) ,
  pais varchar2(30),
  localidade varchar2(30),
  lotacao_max number(6,0)
);

create table zonas(
  idParque number(4,0),
  idZona number(3,0),
  nomeZona varchar2(30),
  casasdebanho number(2,0)
);

create table locais(
  idZona number(3,0),
  idLocal number(5,0),
  nomeLocal varchar2(30)
);

create table lojas(
  idZona number(3,0),
  idLocal number(5,0),
  nomeLocal varchar2(30),
  idLoja

);

create table atracoes(
  idZona number(3,0),
  idLocal number(5,0),
  nomeLocal varchar2(30),
  tipoAtraçao varchar2(15)
);

create table restaurantes(
  idZona number(3,0),
  idLocal number(5,0),
  nomeLocal varchar2(30)
  idRest
);


create table avaliacoes(
  idPessoa
  idLocal
  idAvaliacao
  notaAvaliacao
  descAvaliacao
);

create table pessoas(
  idPessoa
  nomePessoa
  idade
  altura
);

create table funcionarios(
  idPessoa
  idLocal
  idFuncionario
  tipoFuncionario
  dataContratacao
);

create table visitantes(
  idPessoa
  idVisitante
  nomePessoa
  idade
  altura
);

create table artigos(
  idArtigo
  nomeArtigo
  preco
);

create table lembrancas(
  idArtigo
  nomeArtigo
  preco
  idLoja
);

-- tipo-> bebida/snack/quente/frio
create table comidas(
  idArtigo
  nomeArtigo
  preco
  tipo
  idRest
);

create table vendas(
  idPessoa
  idFuncionario
  dataVenda
  horaVenda
);

-- Chaves primárias
-- alter table colocados add constraint pk_col primary key(idCandidato);

-- TODO

-- Chaves candidatas e estrangeiras
-- alter table colocados add constraint fk_colcurso foreign key (curso) references cursos(curso);
-- alter table matriculas add constraint un_mat unique(idCandidato);

-- TODO

-- Outras restricoes
--alter table cadeiras add constraint numCred check(ects >= 3 and ects <=60);

-- TODO

-- Criação prévia de sinónimos
-- NOTA: pode nao ser necessario

--TODO

-- Criação prévia de sequencias

--create sequence seq_num_aluno
--start with 60000
--increment by 1;

--TODO

-- Criação prévia de triggers para a insercao de dados

--TODO

  
-- Insercao de dados

-- Parques
insert into parques values (1121, 'PortAventura World', 'Espanha', 'Barcelona', 300000);
insert into parques values (1122, 'DisneyLand Paris', 'Franca', 'Paris', 600000);
insert into parques values (1123, 'Universal Sutdios Florida', 'E.U.A.', 'Florida', 500000);

-- Zonas

insert into zonas values (1121, 311, 'Mediterranea', 8);
insert into zonas values (1121, 312, 'China Town', 4);
insert into zonas values (1121, 313, 'Polynesia', 3);
insert into zonas values (1121, 314, 'Mexico Land', 7);
insert into zonas values (1121, 315, 'Rua Sesamo', 3);
insert into zonas values (1121, 316, 'Far West', 4);

insert into zonas values (1122, 571, 'Main Street U.S.A.', 5);
insert into zonas values (1122, 572, 'Frontier Land', 7);
insert into zonas values (1122, 573, 'Fantasy Land', 3);
insert into zonas values (1122, 574, 'Adventure Land', 6);
insert into zonas values (1122, 575, 'Discovery Land', 8);

insert into zonas values (1123, 291, 'Production Central', 5);
insert into zonas values (1123, 292, 'New York', 5);
insert into zonas values (1123, 293, 'Hollywood', 5);
insert into zonas values (1123, 294, 'World Expo', 5);
insert into zonas values (1123, 295, 'Springfield', 5);
insert into zonas values (1123, 296, 'Diagon Alley', 5);
insert into zonas values (1123, 297, 'San Francisco', 5);

-- Locais

-- TODO

-- Lojas

-- TODO

--Atraçoes

-- TODO

-- Restaurantes

-- TODO

-- Pessoas

-- TODO

-- Visitantes

-- TODO

-- Funcionarios

-- TODO

-- Artigos

-- TODO

-- Comidas

-- TODO

-- Lembranças

-- TODO
