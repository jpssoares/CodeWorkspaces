(*					Pratica 03					*)

type 'a tree = Nil | Node of 'a * 'a tree * 'a tree
let t = Node(1, Node(2,Nil,Nil), Node(3,Nil,Nil)) ;;

(*ex21*)
let rec size t =
    match t with
        Nil -> 0
      | Node(x,l,r) ->
            1 + size l + size r
;;

let rec union l1 l2=
	match l1 with
	| [] -> l2
	| x::xs -> x :: union xs l2
;;

(* Conta número de ocorrências do valor na árvore *)
(* howMany : 'a -> 'a tree -> int *)
let rec howMany v t =
    match t with
        Nil -> 0
      | Node(x,l,r) ->
            (if x = v then 1 else 0)
						+ howMany v l + howMany v r
;;

(* eqPairs : ('a * 'a) tree -> int *)
(* Conta número de pares com as duas componentes iguais *)
let rec eqPairs t =
    match t with
        Nil -> 0
      | Node((a,b),l,r) ->
            (if a = b then 1 else 0)
						+ eqPairs l + eqPairs r
;;

(* treeToList : 'a tree -> 'a list *)
(* Converte árvore em lista, por uma ordem qualquer*)
let rec treeToList t =
    match t with
        Nil -> []
			| Node(x,Nil,Nil) -> [(x,[])]
			| Node(x,l,Nil) -> union (treeToList l) [(x,[getRoot l])]
			| Node(x,Nil,r) -> union (treeToList r) [(x,[getRoot r])]
      | Node(x,l,r) -> union[(x,[getRoot l; getRoot r])] (union (treeToList l) (treeToList r))
and getRoot t =
	match t with
	| Nil -> failwith "getRoot"
	| Node(x,l,r) -> x
;;

(*RESOLUCAO DO PROFESSOR PRATICA*)

let rec treeToListSimple t =
    match t with
			| Nil -> []
      | Node(x,l,r) ->
				x :: treeToListSimple l @ treeToListSimple r
;;

(*ex22*)


(* height: 'a tree -> int *)
let rec height t =
    match t with
       Nil -> 0
     | Node(x,l,r) ->
           1 + max (height l) (height r)
;;

(*balanced: 'a tree -> bool *)
(*determine se uma árvore binária está ou não equilibrada*)

let rec balanced t=
	match t with
	| Nil -> true
	| Node(x,l,r) ->
		abs(height l - height r) <=1
		&& balanced l && balanced r
;;

(*ex23*)

(*funcoes auxiliares*)
let rec union l1 l2=
	match l1 with
	| [] -> l2
	| x::xs -> x :: union xs l2
;;

let rec uniq l =
	match l with
	|  [] -> []
	| [x] -> [x]
	| x::y::xs ->
		if x = y then uniq (y::xs)
		else x::uniq (y::xs)

let clean l = (* removes repetitions *)
	uniq (List.sort compare l)


(* subtrees: 'a tree -> 'a tree list *)
(*produz a lista de todas as subárvores distintas que ocorrem na árvore argumento*)

let rec subtrees t=
	match t with
	| Nil -> [Nil]
	| Node(x,l,r)->
		clean (t :: union (subtrees l) (subtrees r))
;;

(*ex24*)
(*spring: 'a tree -> 'a tree*)
let rec spring e t =
    match t with
      | Nil -> Node(e,Nil,Nil)
      | Node(x,l,r) ->
				Node(x,spring e l,spring e r)
;;


(*ex25*)
(*fall: 'a tree -> 'a tree*)

let rec fall t =
    match t with
      | Nil -> Nil
			| Node(x,Nil,Nil)-> Nil
      | Node(x,l,r) ->
				Node(x,fall l,fall r)
;;


(*ex26*)

type 'a ntree = NNil | NNode of 'a * 'a ntree list

(*
let rec f t =
    match t with
        NNil -> ...
      | NNode(x,cs) -> ... lf cs ...
and lf tl =
    match tl with
        [] -> ...
      | t::ts -> ... f t ... lf ts ... 
;;
*)

(* ncons : 'a ntree -> 'a ntree list -> 'a ntree list *)

let ncons t l =
    if t = NNil then l  (* ignora NNil *)
    else t::l           (* acrescenta *)
;;


(*	nTreeToList : 'a ntree -> 'a list*)

let rec nTreeToList t =
    match t with
        NNil -> []
      | NNode(x,cs) -> x::lnTreeToList cs
and lnTreeToList tl =
    match tl with
        [] -> []
      | t::ts -> nTreeToList t @ lnTreeToList ts 
;;

(*	nSubtrees : 'a ntree -> 'a ntree list*)

let rec nSubtrees t =
    match t with
      | NNil -> [NNil]
			| NNode(x,[])	-> [NNode(x,[]);NNil]
      | NNode(x,cs) -> NNode(x,cs) :: lnSubtrees cs
and lnSubtrees tl =
    match tl with
      | [] -> []
      | t::ts -> clean (nSubtrees t @ lnSubtrees ts) 
;;

(* nSpring: 'a -> 'a ntree -> 'a ntree*)
let rec nSpring e t =
    match t with
      | NNil -> NNode(e,[])
			| NNode(x,[]) -> NNode(x,[NNode(e,[])])
      | NNode(x,cs) -> NNode(x,lnSpring e cs)
and lnSpring e tl =
    match tl with
      | [] -> []
      | t::ts -> [nSpring e t] @ lnSpring e ts
;;

(* nFall: 'a ntree -> 'a ntree*)

let rec nfall t =
    match t with
      | NNil -> NNil
      | NNode(x,[]) -> NNil
			| NNode(x,cs) -> NNode(x,lnfall cs)
and lnfall tl =
    match tl with
        [] -> []
      | t::ts -> ncons (nfall t) (lnfall ts)
;;


(*ex27*)


