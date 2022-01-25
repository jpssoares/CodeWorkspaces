
alter session set current_schema = candidaturas;

-- Pergunta 9: Quais os seus colegas do secundário que foram colocados na FCT (estabelecimento 903), e em que curso?
select nome, nomeCurso
from candidatos inner join colocacoes using (idCandidato)
                inner join cursos using (curso)
                inner join escolas using (escola)
where estab = 903 and nomeEscola like '%Loulé';


-- Pergunta 11: Quais os colocados deslocados (i.e. residentes num distrito diferente daquele
-- em que se situa o estabelecimento de ensino superior onde foram
-- colocados)?
--
--Para cada um deles, mostre o nome, o distrito onde foram colocados, e o
--distrito onde residem.

select nome, Col descrDistrito, Res descrDistrito
from candidatos inner join colocacoes using (idCandidato)
                inner join estabelecimentosSup using (estab)
                inner join distritos Col on (estabelecimentosSup.distrito = Col.distrito)
                inner join distritos Res on (candidatos.distrito = Res.distrito)
where candidatos.distrito != estabelecimentosSup.distrito;
