(*					Pratica 02					*)

(*ex13.a*)

let f1 x = x+1;;
(* int -> int *)

let f2 x = f1 x;;
(* int -> int *)

let f3 x = 1 + x 5;;
(* (int -> int) -> int *)

let f4 x y = x < y x;;
(* 'a -> ('a -> 'a) -> bool*)


(*ex13.b*)

(* (int -> int) -> int *)
let f5 x = 0 + x 0;;

(* bool -> float -> string *)
let f6 b fl =
	if b == true  && fl == 3.69 then "ola"
	else "adeus"
;;



(*ex14*)

(* succAll : int list -> int list *)
let lint = [3; 6; 1; 0; -4];;

let rec succAll l =
	match l with
	| [] -> []
	| x :: xs -> x+1 :: succAll xs
;;
	

(*ex15*)

(*belongs: 'a -> 'a list -> bool*)
(* teste de pertença *) (* para ser resolvido no quadro *)

let rec belongs e l = 
	match l with
	| [] -> false
	| x::xs ->
		if e = x then true else belongs e xs
;;


(*union: 'a list -> 'a list -> 'a list*)
(* união de conjuntos *)

let rec union l1 l2=
	match l1 with
	| [] -> l2
	| x::xs -> x :: union xs l2
;;

(*sem repeticoes*)
let rec unionOther l1 l2 =
	uniq (List.sort compare (l1 @ l2))
and uniq l =
	match l with
	|  [] -> []
	| [x] -> [x]
	| x::y::xs ->
		if x = y then uniq (y::xs)
		else x::uniq (y::xs)
;;

(*inter: 'a list -> 'a list -> 'a list*)
(* intersecção de conjuntos *)

let rec inter l1 l2=
	match l1 with
	| [] -> []
	| x::xs -> 
		if belongs x l2 = true then x :: inter xs l2
		else inter xs l2
;;

(*diff: 'a list -> 'a list -> 'a list*)
(* diferença de conjuntos *)

let rec diff l1 l2=
	match l1 with
	| [] -> []
	| x::xs -> 
		if belongs x l2 = true then diff xs l2
		else x :: diff xs l2
;;

(*power: 'a list -> 'a list list*)
(* conjunto potência: conjunto dos subconjuntos - difícil *)

(* funcao auxiliar *)
let rec insert e ll=
	match ll with
	| []->[]
	| l::ls -> [e::l] @ insert e ls
		
;;

let rec power l =
	match l with
	| [] -> [[]]
	| x :: xs -> power xs @ insert x (power xs)
;;

(*ex16*)

(*let rec fact x = if x = 0 then 1 else x * fact (x-1) *)

(*nat : int -> int list*)
let rec nat n =
	if n = 0 then []
	else n-1 :: nat (n-1)
;;


(*ex17.a*)
let templist = [10.1; 10.1; 10.1; 10.1; 10.1; 10.1; 10.1; 10.0; 10.0; 10.1; 10.0];;

(*pack : 'a list -> ('a * int) list*)
let rec pack l =
	match l with
	| [] -> []
	| x::xs -> 
  		match pack xs with
  			| [] -> [(x,1)]
  			| (a,b)::ys -> 
					if a =x then (a,b+1)::ys
					else (x,1)::(a,b)::ys
;;

(*ex17.b*)
let rec unpack l =
	match l with
	| [] -> []
	| (x,n)::xs -> 
		

(*ex18.a*)

(* TODO *)


(*ex18.b*)

(* TODO *)

(*ex19*)
(* TODO *)

(*ex20*)
(* ('a -> 'b) -> ('c -> 'a) -> 'c -> 'b *)

(*TODO*)

