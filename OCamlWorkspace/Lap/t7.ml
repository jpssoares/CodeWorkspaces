(* Teorica 7 (07/04/2021)*)

(*------------Fun��es com m�ltiplos argumentos em OCaml--------------*)


  (*FORMA NAO-CURRIED*)
  
    (*Os v�rios argumento agrupam-se num �nico tuplo ordenado*)
    
    let nAdd (x,y) = x+y ;;
    (*nAdd: (int * int) -> int*)
    
    let nAdd3 (x,y,z) = x+y+z ;;
    (*nAdd3: (int * int * int) -> int*)
    
    nAdd (3,4) = 7
    nAdd3 (2,8,1) = 11
    
  
  (*FORMA CURRIED*)
  
    (*Os argumentos ficam separados*)
    
    let cAdd x y = x+y ;;
    (*cAdd: int -> int -> int*)
    
    let cAdd3 x y z = x+y+z ;;
    (*cAdd: int -> int -> int -> int*)
    
    cAdd 3 4 = 7
    cAdd3 2 8 1 = 11
    
    
  (*REPRESENTACAO INTERNA DAS FUNCOES EM OCAML*)
  
    (*Uma fun��o com m�ltiplos argumentos � convertida para um formato interno especial*)
    (* - chamado FORMA INTERNA -*)
    (* que envolve apenas fun��es an�nimas com um �nico argumento.*)
    
    (*Por exemplo, a fun��o*)
    
    let cAdd x y = x+y ;;
    
    (*� internamente convertida em:*)
    
    let cAdd = fun x -> (fun y -> x+y) ;;s
    
    
    
    (*Outros exemplos*)
    
    let cAdd x y z = x+y+z ;;     (*let cAdd = fun x -> (fun y -> (fun z -> x+y+z)) ;;*)
    
    let f x = x+1 ;;              (*let f = fun x -> x+1 ;;*)
    
    let nAdd (x,y) = x+y ;;       (*let nAdd = fun (x,y) -> x+y ;;*)


(*-----------------------Aplica�oes parciais--------------------------*)

	(*As fun��es CURRIED t�m a vantagem de poderem ser invocadas omitindo alguns dos argumentos do final.*)
	
		let succ = cAdd 1 ;;
		(*succ: int -> int*)


	(*ASSOCIATIVIDADES*)
	
  	(*O OPERADOR DE APLICACAO � associativo � esquerda. (Note que este operador � invis�vel, pois nunca se escreve).
  
      Portanto, a express�o f a b deve ser interpretada como (f a) b. *)
  		
  	(*O CONSTRUTOR DE TIPOS FUNCIONAIS -> � associativo � direita.
  
      Portanto, o tipo int -> int -> int deve ser interpretado como int -> (int -> int). *)


	(*FORMAS EQUIVALENTES DE ESCREVER A MESMA FUNCAO*)
	
		let f x y = x + y ;;	             (* formato externo preferido *)
		let g x = (fun y -> x+y) ;;	     (* formato externo *)
		let h = (fun x y -> x+y) ;;	     (* formato externo *)
		let w = (fun x -> (fun y -> x+y)) ;; (* formato interno *)
		
		
(*-----------------------Fun�oes de ordem superior sobre listas--------------------------*)

	(*FUNCAO MAP*)
		(*Aplica uma fun��o f: 'a -> 'b a todos os elementos duma lista,*)
		(*para produzir a lista dos resultados.*)


    (* map : ('a -> 'b) -> 'a list -> 'b list *)
    let rec map f l =
        match l with
            [] -> []
          | x::xs -> (f x) :: map f xs
    ;;

		(*Esta fun��o est� dispon�vel na biblioteca do OCaml como List.map.*)

		(*Exemplo de chamada:*)

    List.map (fun x -> x + 1) [1;2;3]

	(*FUNCAO FLATMAP*)
	
		(*Aplica uma fun��o f: 'a -> 'b list a todos os elementos duma lista,*)
		(* para produzir a lista dos resultados.*)

		(*A fun��o f define uma rela��o de um-para-n,*)
		(* pelo que a lista dos resultados tem um comprimento sem qualquer rela��o*)
		(* com comprimento da lista de entrada.*)

    (* flatMap : ('a -> 'b list) -> 'a list -> 'b list *)
    let rec flatMap f l =
        match l with
            [] -> []
          | x::xs -> (f x) @ flatMap f xs
    ;;

		(*Exemplo de chamada:*)

    flatMap (fun x -> [x + 1; x + 2]) [1;2;3]

		(*Esta fun��o n�o est� diretamente dispon�vel na biblioteca do OCaml,*)
		(* pode ser obtida como a combina��o de List.flatten com List.map.*)
		
		
    (* flatMap : ('a -> 'b list) -> 'a list -> 'b list *)
    let flatMap f l =
        List.flatten (List.map f l)
    ;;

	(*MAIS FUNCOES DE ORDEM SUPERIOR SOBRE LISTAS*)

    List.exists (* : ('a -> bool) -> 'a list -> bool*)(* Testa se pelo menos um dos valores duma lista satisfaz um dado predicado. *)

    List.filter (*: ('a -> bool) -> 'a list -> 'a list *) (* Seleciona os elementos duma lista que satisfazem um dado predicado. *)

    List.partition (*: ('a -> bool) -> 'a list -> 'a list * 'a list*) (* Separa os elementos que satisfazem um predicado dos elementos que n�o o satisfazem *) 

		
		(*Fun��o de teste de perten�a a uma lista ("membership")*)
    List.mem (*: 'a -> 'a list -> bool*) (* Testa se um valor pertence a uma lista. *) 

	(*Exemplo de utiliza��o: QUANTIFICACAO UNIVERSAL*)
		
		(*Verificar se todos os elementos duma lista s�o pares:*)
    
		let allEven l =
        List.for_all (fun x -> x mod 2 = 0) l
    ;;

		(*Testar se todos os elementos duma lista s�o menores do que os elementos de outra lista:*)
    
		let allLess l1 l2 =
        List.for_all (fun x ->
              List.for_all (fun y -> x < y) l2) l1
    ;;

