(* Teorica 5 (26/03/2021)*)


(*--------------------------------Tipos produto em OCaml---------------------------*)

(*Em OCaml há duas variedades de tipos produto:
tipos produto não etiquetados e tipos produto etiquetados. *)

(*NAO ETIQUETADOS*)

(*Literais*)
(* Para exemplificar, eis um valor do tipo anterior*)

("João ", 1970, "Lisboa")

(*Construção*)
(* Para exemplificar vejamos uma função que muda a morada duma pessoa, criando um tuplo novo*)

let moveTo (x,y,_) city = (x, y, city) ;;

(*Processamento*)
(* Como se processam tuplos? De duas formas*)

(*Usando emparelhamento de padrões*)

let getName p =
	match p with
		(x, _, _) -> x
;;


(*ETIQUETADOS*)

type pessoa = { nome:string ; anoNasc:int ; morada:string } ;;

(*Literais*)
(* Eis um literal deste tipo*)

{ nome = "João" ; anoNasc = 1970 ; morada = "Lisboa" }


(*Construção*)
(* Para exemplificar vejamos uma função que muda a morada duma pessoa, criando um registo novo*)

let moveTo p city =
	{ nome = p.nome ; anoNasc = p.anoNasc ; morada = city }
;;

(*Processamento*)
(* Como se processam registos? De duas formas*)

    (*Usando emparelhamento de padrões:*)

let getNome p =
	match p with
		{ nome = x ; anoNasc = _ ; morada = _ } -> x
;;

    (*Ou usando a operação de acesso "." e que funciona em OCaml exatamente como em Pascal ou C*)

let getNome p = p.nome ;;


(*--------------------------------Tratamento de excepcoes---------------------------*)

(*Deliberadamente pelos próprios programas, usando a construção RAISE*)

let rec fact x =
    if x = 0 then 1
    else if x>0 then x * fact(x-1)
    else raise (Arg.Bad "fact")
;;

(*ou usando a função FAILWITH, que também gera uma exceção, mas duma forma mais prática de escrever.*)
(*Por exemplo, para lidar explicitamente com argumentos proibidos*)

let rec fact x =
    if x = 0 then 1
    else if x>0 then x * fact(x-1)
    else failwith "fact"
;;

(*CAPTURA E TRATAMENTO DE EXEPCOES*)

(*Em OCaml, um tratador de exceções escreve-se usando uma expressão TRY-WITH*)
(*A expressão exp, no seu interior, diz-se uma expressão protegida.*)


try
    exp
with Sys_error _ -> exp1
   | Division_by_zero -> exp2
   | End_of_file -> exp3
   ...
;;


(*RESULTADOS:*)
(*# 4/0;;
Exception: Division_by_zero.

# open_in "fdsg" ;;
Exception: Sys_error "fdsg: No such file or directory".
*)

(*NOVAS EXCEPCOES*)

exception Stack_overflow of string * int ;;
exception I_m_so_out_of_here ;;


(*--------------------------------Tratamento de excepcoes---------------------------*)


(*	TODO	*)