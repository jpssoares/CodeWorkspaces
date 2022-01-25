-- Ficha 5
-- Proposta de solução

-- Muda a sessão para aceder, por default, às tabelas do utilizador candidaturas
alter session set current_schema = candidaturas;

-- 1.
-- Quantos alunos se candidataram a cursos da FCT (ou seja, quantos candidatos há na base de dados)?
select count(*)
from candidatos;


-- 2.
-- Qual a média das notas do secundário de todos os candidatos à FCT?
select avg(mediaSec)/10
from candidatos;


-- 3.
-- Qual a média das notas do secundário dos candidatos a cada curso da FCT?
with codMedia as
  ( select curso, avg(mediaSec)/10 media
    from candidatos inner join candidaturas using (idCandidato)
    where estab = 903
    group by curso)
select nomeCurso, media
from codMedia natural inner join cursos;

-- ou (mais simples, já que os nomes dos cursos da FCT são todos distintos)
select nomecurso, avg(mediaSec)/10 media
from candidatos inner join candidaturas using (idcandidato)
                inner join cursos using (curso)
where estab = 903
group by nomecurso;

-- 4.
-- De quantas escolas secundárias diferentes houve candidatos à FCT?
select count(distinct escola)
from candidatos;


-- 5.
-- E de quantas escolas do país não houve candidatos à FCT?
with Houve as
  (select count(distinct escola) as N from candidatos),
  Todas as
  (select count(*) as T from escolas)
select T-N
from Houve, Todas;

-- ou
with todasEscolas as
  (select distinct escola from candidatos)
select count(*)
from escolas left join todasEscolas on (escolas.escola = todasEscolas.escola)
where todasEscolas.escola is null;

-- ou - mais simples, já que para filtrar pelos nulls não precisamos escolas distintas
select count(*)
from escolas left join candidatos on (escolas.escola = candidatos.escola)
where candidatos.escola is null;

-- ou ainda
select count(*)
from escolas
where escola not in (select escola from candidatos);

-- ou ainda
select count(*)
from ((select escola from escolas) minus (select escola from candidatos));


-- 6.
-- Para cada curso da FCT, de quantas escolas secundárias diferentes houve candidatos a esse curso?
select nomeCurso, count(distinct escola)
from candidatos inner join candidaturas using (idCandidato)
                inner join cursos using (curso)
where estab = 903
group by nomeCurso;


-- 7.
-- Quantos alunos e quantas alunas se candidataram à FCT?
select sexo, count(*) as nCandidatos
from candidatos
group by sexo;

-- 8.
-- Quantos candidatos há de cada uma das escolas (em que houve candidatos), ordenado da escola com mais candidatos para a escola com menos?
select nomeEscola, quantos
from escolas inner join (	select escola, count(*) as quantos
							from candidatos
							group by escola)
			using (escola)
order by quantos desc;


-- 9.
-- Para cada escola do pais, quantos candidatos houve à FCT vindos dessa escola?
-- Para as escolas em que não houve candidatos, o nome da escola deve aparecer na mesma, mas com null no número de candidatos.
select nomeEscola, quantos
from escolas left join (	select escola, count(*) as quantos
							from candidatos
							group by escola)
			using (escola)
order by quantos desc nulls last;


-- 10.
-- Quais as escolas do distrito de Setúbal que tiveram mais de 30 candidatos à FCT?
-- Para simplificar, pode assumir que não há duas escolas no distrito de Setúbal com o mesmo nome.
select nomeEscola
from escolas inner join candidatos using (escola)
             inner join distritos on (escolas.distrito = distritos.distrito)
where descrDistrito like 'Set�bal'
group by nomeEscola
having count(*) > 30;


-- 11.
-- Qual o nome do candidato à FCT com maior média do secundário, e qual essa média? 
select nome, mediaSec/10
from candidatos, (select max(mediaSec) as mediaMax from candidatos) A
where A.mediaMax = mediaSec;

-- ou
select nome, mediaSec/10
from candidatos
where mediaSec = all (select max(mediaSec) from candidatos);

-- ou
select nome, mediasec/10
from candidatos 
where mediaSec in (select max(mediaSec) from candidatos);


-- 12.
-- Quais as médias das notas do secundário dos candidatos colocados em cada um dos cursos da FCT, indicando o nome do curso?
-- (de notar que na FCT não há dois cursos com o mesmo nome)
select nomeCurso, avg(mediaSec)/10
from colocacoes inner join cursos using (curso)
                inner join candidatos using (idCandidato)
where estab = 903
group by nomeCurso
order by avg(mediaSec);


-- 13.
-- Qual a nota mínima dos colocados no contingente geral em cada dos cursos da FCT (a que é anunciada nos jornais como nota do último colocado)?
select nomeCurso, min(notacand)/10 as ultimo
from contingentes inner join colocacoes using (conting)
                  inner join candidaturas using (idCandidato, estab, curso)
                  inner join cursos using (curso)
where estab = 903 and descrConting = 'Geral'
group by nomeCurso;


-- 14.
-- Quais as nomes, e respetivas médias do secundário, dos candidatos não colocados?
select candidatos.nome, candidatos.mediaSec/10
from candidatos left join colocacoes on (candidatos.idCandidato = colocacoes.idCandidato)
where colocacoes.idCandidato is null;

-- ou
select nome, mediaSec/10
from candidatos
where idCandidato not in (select idCandidato from colocacoes);


-- 15.
-- Quais os cursos da FCT que tiveram candidatos de escolas todos os distritos?
select nomeCurso
from cursos
where
	not exists
  ((select distrito from distritos)
    minus
    (	select distinct escolas.distrito
		from candidatos inner join escolas using (escola) 
                        inner join candidaturas using (idcandidato)
		where cursos.curso = candidaturas.curso and estab = 903
	  )
  );

-- ou
select nomeCurso
from candidatos inner join escolas using (escola)
	inner join candidaturas using (idcandidato)
  inner join cursos using (curso)
where estab = 903
group by nomeCurso
having count(distinct escolas.distrito)
          = some (select count(*) from distritos);

-- ou ainda, usando diretamente a definicao do operador de divisao

select distinct nomeCurso        -- Todos os cursos da FCT
from cursos natural join ofertas
where estab = 903

minus

select nomeCurso      -- Curso que nao tem candidatos de todos os distrito
from
(
  ( select distrito, nomeCurso         -- Todos os pares possiveis, com curso da FCT e distrito
    from distritos,
      ( select distinct nomeCurso       
        from cursos natural join ofertas
        where estab = 903)
  )
  minus
  ( select distinct escolas.distrito, nomeCurso -- Pares distrito-curso, em que ha um candidato desse distrito a esse curso
    from candidatos inner join escolas using (escola)
      inner join candidaturas using (idcandidato)
      inner join cursos using (curso)
    where estab = 903
  )
);


-- 16.
-- Quais os nomes e notas de candidatura dos alunos colocados no MIEI que tiveram nota de candidatura superior à de todos os alunos colocados no MIEEC?
-- Para simplificar, assuma que G005 é o código do MIEI e o do MIEEC é 9367.
select candidatos.nome, notaCand
from colocacoes inner join candidaturas using (idCandidato, estab, curso)
                inner join candidatos using (idCandidato)
where estab = 903 and curso = 'G005'
	and notaCand > all
		(select notaCand
         from colocacoes inner join candidaturas using (idCandidato, estab, curso)			
         where estab = 903 and curso = '9367'
		);

-- ou
select candidatos.nome, notaCand
from colocacoes inner join candidaturas using (idCandidato, estab, curso)
                inner join candidatos using (idCandidato)
where estab = 903 and curso = 'G005'
	and notaCand > some
		(select max(notaCand)
         from colocacoes inner join candidaturas using (idCandidato, estab, curso)		
         where estab = 903 and curso = '9367'
		);

-- 17
-- Quantos alunos se candidataram ao MIEI e ao MIEEC?
select count(*)
from candidaturas A inner join candidaturas B using (idCandidato)
where A.estab = 903 and A.curso = 'G005' and 
      B.estab = 903 and B.curso = '9367';

-- 18
-- Quantos alunos se candidataram ao MIEI e não se candidataram ao MIEEC?
select count(*)
from
	(select distinct idCandidato from candidaturas where estab = 903 and curso = 'G005') I
    left join
	(select distinct idCandidato from candidaturas where estab = 903 and curso = '9367') E
		on (I.idCandidato = E.idCandidato)
where E.idCandidato is null;
                            
-- ou
select count(distinct idCandidato)
from candidaturas
where estab = 903 and curso = 'G005' and idCandidato not in (select idCandidato
                                                             from candidaturas
                                                             where estab = 903 and curso = '9367');


-- 19
-- E quantos se candidataram ao MIEEC e não se candidataram ao MIEI?
select count(*)
from
	(select distinct idCandidato from candidaturas where estab = 903 and curso = 'G005') I
    right join
	(select distinct idCandidato from candidaturas where estab = 903 and curso = '9367') E
		on (I.idCandidato = E.idCandidato)
where I.idCandidato is null;
                            
-- ou
select count(distinct idCandidato)
from candidaturas
where estab = 903 and curso = '9367' and idCandidato not in (select idCandidato
							                                 from candidaturas
                                                             where estab = 903 and curso = 'G005');

-- 20
-- Para cada cursos da FCT, qual a percentagem de raparigas colocadas?

select nomeCurso, 100*raparigas/(raparigas+rapazes) as percentagemMulheres
from cursos inner join 
	(select curso, count(*) rapazes
	from colocacoes natural join candidatos
	where estab = 903 and sexo = 'M'
	group by curso) Rapazes using (curso)
	inner join
	(select curso, count(*) raparigas
	from colocacoes natural join candidatos
	where estab = 903 and sexo = 'F'
    group by curso) Raparigas using (curso)
order by percentagemMulheres;
    
-- 21
-- Para cada curso da FCT, qual a média das notas de candidatura dos alunos que foram 
--  colocados nesse curso e que repetiram o exame Matemática A?
select nomeCurso, avg(notacand)/10
from cursos inner join 
     (select idCandidato, curso, estab
	  from alunosExames inner join colocacoes using (idCandidato)
                        inner join exames using (exame)
	  where nomeExame = 'Matem�tica A' and estab = 903
	  group by idCandidato, curso, estab
      having count(*) > 1) repet using (curso)
            inner join candidaturas using (idcandidato,curso,estab)
group by nomeCurso;
    
    