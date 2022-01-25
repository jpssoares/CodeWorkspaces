(* Teorica 6 (31/03/2021)*)


(*----------------Efeitos laterais em OCaml-------------*)

(*Um efeito lateral � qualquer atividade que uma fun��o desenvolva*)
(*para al�m de calcular um resultado a partir dos argumentos*)


(*operador de sequencia��o -> ";"*)

let hello () =
        print_string "ola" ; print_string "ole"
    ;;


(*----------------Motiva��o do tipo "unit"---------------*)


(*O tipo unit � um tipo b�sico com apenas um valor, que se escreve ()*)

(*print_string: string -> unit
read_int: unit -> int
print_newline: unit -> unit*)

let x = read_int () in
	print_int (x+1)
;;

(*Exemplo de fun��o para escrever uma lista de inteiros no ecr�*)

let rec printList l =
	match l with
		[] -> ()
		| x::xs -> print_int x ; print_newline () ; printList xs
;;


(*--------------------------------Input/Output em OCaml--------------------------------*)

(*As opera��es sobre ficheiros s�o efetuadas atrav�s de canais*)


(*Em OCaml existem tr�s canais predefinidos que s�o automaticamente abertos quando o programa come�a a correr:

    stdin : in_channel
    stdout : out_channel
    stderr : out_channel
*)


(*Primitivas de input/output

    Primitivas para escrita em stdout

        print_char: char -> unit
        print_string: string -> unit
        print_int: int -> unit
        print_float: float -> unit
        print_newline: unit -> unit

    Primitivas para leitura de stdin

        read_line: unit -> string
        read_int: unit -> int
        read_float: unit -> float

    Primitivas para abertura e fecho de canais

        open_in: string -> in_channel
        open_out: string -> out_channel
        close_in: in_channel -> unit
        close_out: out_channel -> unit

    Primitivas para escrita em canais

        output_char: out_channel -> char -> unit
        output_string: out_channel -> string -> unit
        flush: out_channel -> unit

    Primitivas para leitura de canais

        input_char: in_channel -> char
        input_line: in_channel -> string
*)


(*------------M�todo indutivo na programa��o de fun��es sobre ficheiros---------------*)

(*EXEMPLO -> COPIA DE FICHEIROS*)

(* copyChannel: copia o canal de input ci para o canal de output co *)

let rec copyChannel ci co =
    try
        let s = input_line ci in
            output_string co s ;
            output_string co "\n" ;
            copyChannel ci co
    with End_of_file -> ()
;;

(* copyFile: abre os ficheiros ni e depois usa a fun��o copyChannel *)

let copyFile ni no =
    let ci = open_in ni in
        let co = open_out no in
            copyChannel ci co ;
            close_in ci ;
            close_out co
;;

(*Esquema geral de utiliza��o do m�todo indutivo*)
(* no tratamento de ficheiros linha a linha*)

let rec f ci =
    try
        let s = input_line ci in
            ... f ci ...
    with End_of_file -> ...
;;


(*--------------------------Introdu��o aos m�dulos em OCaml----------------------*)


(*Na primeira alternativa, podemos usar refer�ncias qualificadas*)

Sys.os_type ;;
(*- : string = "Unix"*)

Sys.command "ls -l" ;;
(*total 24
-rw-r--r-- 1 amd amd  259 2008-03-11 10:26 main.ml
-rw-r--r-- 1 amd amd  440 2008-03-11 10:28 MySet.cmi
-rw-r--r-- 1 amd amd  654 2008-03-11 10:28 MySet.cmo
-rw-r--r-- 1 amd amd  394 2008-03-11 10:11 MySet.ml
-rw-r--r-- 1 amd amd  164 2008-03-11 10:11 MySet.mli
- : int = 0*)

List.rev [1;2;3] ;;
(*- : int list = [3; 2; 1]*)


(*CRIACAO DUM MODULO ABERTO*)


(*Para criar um m�dulo aberto, no qual todas as defini��es s�o p�blicas,*)
(* basta criar um ficheiro com o nome pretendido e extens�o "ml", digamos "MySet.ml".*)
(* Depois � necess�rio compilar o m�dulo usando o comando

    ocamlc -c MySet.ml

	sendo gerados os ficheiros "MySet.cmi" e "MySet.cmo".*)


(*CRIACAO DUM MODULO FECHADO*)

(*Para ocultar a representa��o interna dos dados e as opera��es auxiliares*)
(* � necess�rio criar adicionalmente um ficheiro interface "MySet.mli"*)
(* onde se declaram todas as entidades p�blicas da forma que se pretende*)
(* que sejam vistas do exterior. Depois compila-se o m�dulo assim

    ocamlc -c MySet.mli MySet.ml

	sendo gerados os ficheiros "MySet.cmi" e "MySet.cmo".*)
