-- Ficha 4
-- Proposta de solução

-- Para aceder na sessão as tabelas do utilizador candidaturas
alter session set current_schema = candidaturas;

-- 1
-- Quais o nomes e médias do secundário dos vários candidatos 
-- ao ensino superior?
select nome, mediaSec from candidatos;

-- 2
-- Para cada candidato qual o nome da escola secundária que 
-- frequentou?
select nome, nomeEscola
from candidatos, escolas
where candidatos.escola = escolas.escola;

-- ou
select nome, nomeEscola
from candidatos inner join escolas using (escola);

-- ou
select nome, nomeescola
from candidatos inner join escolas on (candidatos.escola=escolas.escola);

-- mas não:
select nome, nomeescola
from candidatos natural join escolas;

-- 3
-- Quais os candidatos que frequentaram uma escola secundária no mesmo 
-- concelho em que residiam (e qual o nome da escola e do concelho)?
select nome, nomeEscola, descrConcelho
from candidatos inner join escolas using (escola, distrito, concelho)
                inner join concelhos using (distrito, concelho);
                
--ou
select nome, nomeEscola, descrConcelho
from escolas natural join candidatos natural join concelhos;

-- 4
-- A que cursos (e onde) se candidatou o candidato com identificador 23152, 
-- dizendo para cada curso a ordem em que o colocou e a média de candidatura? 
select ordem, nomeCurso, nomeEstab, notaCand
from candidaturas inner join estabelecimentosSup using (estab)
                  inner join cursos using (curso)
where idCandidato = 23152
order by ordem;

-- 5
-- Relembre a que curso é que você se candidatou?
select ordem, nomeCurso, nomeEstab, notaCand
from candidatos inner join candidaturas using (idCandidato)
                inner join estabelecimentosSup using (estab) 
                inner join cursos using (curso)
where nome = 'JO�O S. C. S.'
order by ordem;

-- 6
-- ﻿Que exames fez o candidato com o identificador 23152?
select nomeExame, ano, fase
from alunosExames natural join exames
where idCandidato = 23152;

-- ﻿Já agora, relembre os exames que fez.
select nomeExame, ano, fase
from candidatos inner join alunosExames using (idCandidato)
                inner join exames using (exame)
where nome = 'JO�O S. C. S.';

-- 7
-- Onde ficaram colocados os candidatos com `Afonso' no nome?
select nome, nomeCurso, nomeEstab
from colocacoes inner join candidatos using (idCandidato)
                inner join cursos using (curso)
                inner join estabelecimentosSup using (estab)
where nome like '%JO�O%';

-- 8
-- Quais os candidatos colocados em Informática na Nova, e para cada um deles 
-- o seu nome, a média de candidatura, a média do secundário, a ordem de 
-- preferência, e a escola secundária que frequentaram?
select nome, notaCand, mediaSec, ordem, nomeEscola
from candidatos inner join escolas using (escola)
                inner join candidaturas using (idCandidato)
                inner join colocacoes using (idCandidato, estab, curso)
                inner join cursos using (curso)
                inner join estabelecimentosSup  using (estab)
where nomeCurso like '%Informática%' and nomeEstab like '%Nova%';

-- 9
-- Quais os seus colegas do secundário que foram colocados na FCT 
-- (estabelecimento 903), e em que curso?
 select nome, nomeCurso
 from candidatos inner join colocacoes using (idCandidato)
                 inner join cursos using (curso)
                 inner join escolas using (escola)
 where nomeEscola like '%Loul�%' and estab = 903;
 -- Escola de exemplo. Deve meter a sua escola do secundário
      

-- 10
-- Quais os candidatos colocados na Nova que não entraram num curso de 
-- Engenharia, mostrando para cada um deles em que curso entraram?
select nome, nomeCurso
from candidatos inner join colocacoes using (idCandidato)
                inner join cursos using (curso)
                inner join estabelecimentosSup  using (estab)
where nomeEstab like '%Nova%' and nomeCurso not like 'Engenharia%';

-- 11
-- Quais os colocados deslocados (i.e. residentes num distrito diferente 
-- daquale em que se situa o estabelecimento de ensino superior onde foram 
-- colocados). Para cada um deles, mostre o nome, o distrito onde foram 
-- colocados, e o distrito onde residem. .
select nome, Col.descrDistrito as Colocado, Res.descrDistrito as Residente
from candidatos inner join colocacoes using (idcandidato)
                inner join estabelecimentossup using (estab)
                inner join distritos Res on (candidatos.distrito=Res.distrito)
                inner join distritos Col on (Col.distrito=estabelecimentossup.distrito)            
where candidatos.distrito != estabelecimentossup.distrito;


-- 12
-- Quais os candidatos que se candidataram ao curso G005 (que é o vosso) e ao 
-- curso 9367 (que é o MIEEC) no estabelecimento 903 (que é a FCT) e que, 
-- acertadamente, preferiam o curso G005? Para cada um apresente o nome, e a 
-- ordem em que colocou o MIEI e o MIEEC.
select nome, CandMIEI.ordem OrdemMIEI, CandMIEEC.ordem OrdemMIEEC,  CandMIEI.notaCand
from candidaturas CandMIEI, candidaturas CandMIEEC, candidatos
where candidatos.idCandidato = CandMIEI.idCandidato 
	and CandMIEI.idCandidato = CandMIEEC.idCandidato
	and CandMIEI.curso = 'G005' and CandMIEEC.curso = '9367' 
	and CandMIEI.estab = 903 and CandMIEEC.estab = 903
	and CandMIEI.ordem < CandMIEEC.ordem
order by CandMIEI.notaCand;
  
-- 13
-- Quais os candidatos que se candidataram a Informática na Nova e no Técnico,
-- mas que, acertadamente, preferiam a Nova? Para cada um apresente o nome, e 
-- a ordem em que colocou a Nova e o Técnico.
select nome, CandNova.ordem OrdemNova, CandTecn.ordem OrdemTecnico
from candidaturas CandNova inner join estabelecimentosSup EstNova using (estab)
                           inner join cursos CursoNova using (curso),
     candidaturas CandTecn inner join estabelecimentosSup EstTecn using (estab)
                           inner join cursos CursoTecn using (curso),
     candidatos
where candidatos.idCandidato = CandNova.idCandidato 
	and CandNova.idCandidato = CandTecn.idCandidato
	and EstNova.nomeEstab like '%Nova%' and EstTecn.nomeEstab like '%Técnico'
	and CursoNova.nomeCurso like '%Informática%' 
	and CursoTecn.nomeCurso like '%Informática%'
	and CandNova.ordem < CandTecn.ordem;
    
-- ou aproveitando a informação anterior em relação a FCT:

select nome, cNova.ordem OrdemNova, cTec.ordem OrdemTecnico
from candidatos inner join candidaturas cNova on 
							(candidatos.idcandidato=cNova.idcandidato)
                inner join candidaturas cTec on 
                			(candidatos.idcandidato = cTec.idcandidato)
                inner join cursos on (cTec.curso=cursos.curso)
                inner join estabelecimentossup on 
                			(estabelecimentossup.estab = cTec.estab)
where cNova.estab= 903 and cNova.curso='G005' 
	and nomeestab like '%T�cnico' and nomecurso like '%Inform�tica%' 
	and cNova.ordem< cTec.ordem;

-- 14
-- Quais os candidatos não colocados?
(select idcandidato, nome from candidatos)
minus
(select idcandidato, nome
from candidatos inner join colocacoes using (idcandidato));
-- ou 
select idcandidato, nome
from candidatos left outer join colocacoes using (idcandidato)
where estab is null;

-- 15
-- ﻿Quais os cursos do estabelecimento 903 (a FCT) que tiveram colocados que 
-- fizeram exame de Geometria Descritiva A?
select distinct nomeCurso
from colocacoes inner join alunosExames using (idCandidato)
                inner join exames using (exame)
                inner join cursos using (curso)
where nomeExame = 'Geometria Descritiva A' and estab = 903;

-- 16
-- ﻿Quais os cursos do estabelecimento 903 (a FCT) que tiveram colocados 
-- que fizeram exame de Geometria Descritiva A, e que também tiveram 
-- colocados que fizeram algum exame de História?
(select nomeCurso
from colocacoes inner join alunosExames using (idCandidato)
                inner join exames using (exame)
                inner join cursos using (curso)
where nomeExame = 'Geometria Descritiva A' and estab = 903)
intersect
(select nomeCurso
from colocacoes inner join alunosExames using (idCandidato)
                inner join exames using (exame)
                inner join cursos using (curso)
where nomeExame like '%Hist�ria%' and estab = 903);

-- 17
-- ﻿Quais os cursos do estabelecimento 903 (a FCT) que tiveram colocados 
-- que fizeram exame de ﻿Geometria Descritiva e algum exame de História?
select distinct nomeCurso
from colocacoes      inner join alunosExames on 
						(colocacoes.IDCANDIDATO = alunosExames.IDCANDIDATO)
                     inner join exames using (exame),
     colocacoes ColM inner join alunosExames examesM on 
     					(ColM.IDCANDIDATO = examesM.IDCANDIDATO)
                     inner join exames ExM using (exame),
    cursos
where exames.nomeExame = 'Geometria Descritiva A' 
	and ExM.nomeExame like '%Hist�ria%'
	and colocacoes.estab = 903 and ColM.estab = 903
	and colocacoes.idCandidato = ColM.idCandidato
    and cursos.curso = colocacoes.curso;

