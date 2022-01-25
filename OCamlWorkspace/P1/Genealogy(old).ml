(* Genealogy module body *)

(* 
Aluno 1: 57498 mandatory to fill
Aluno 2: 57609 mandatory to fill

Comment:

?????????????????????????
?????????????????????????
?????????????????????????
?????????????????????????
?????????????????????????
?????????????????????????

*)

(*
0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789
   100 columns
*)


(* COMPILATION - How to build this module (used by Mooshak))
         ocamlc -c Genealogy.mli Genealogy.ml
*)


(* AUXILIARY BASIC FUNCTIONS - you can add more *)
let rec append l1 l2 =
		match l1 with
      | [] -> l2
			| x::xs -> x:: append xs l2
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

let len =
	List.length

let map =
	List.map

let filter =
	List.filter

let mem =
	List.mem

let flatMap f l =
	List.flatten (map f l)

let partition =
	List.partition

let exists =
	List.exists

let for_all =
	List.for_all

let cFlatMap f l =
	clean (flatMap f l)

let union l1 l2 =
	clean (l1 @ l2)

let inter l1 l2 =
	filter (fun x -> mem x l2) l1

let diff l1 l2 =
	filter (fun a -> not (mem a l2)) l1



(* TYPES *)

type item = string * string list
type repository = item list

type aTree = ANil | ANode of string * aTree * aTree
type dTree = DNil | DNode of string * dTree list


(* EXAMPLES - you can add more *)

let example = [
           ("a", ["f";"g"]);
           ("b", ["f";"h"]);
           ("c", ["h";"i"]);
           ("f", ["g"; "j"]);
           ("g", ["j"]);
           ("h", []);
           ("i", []);
           ("j", [])
          ]
;;
				
let at = (ANode("g", ANode("a", ANil, ANil),
	ANode("f", ANode("a", ANil, ANil), ANode("b", ANil, ANil))));;

let dt = DNode("a", [DNode("f", [DNode("g", [DNode("j", [])]);
	DNode("j", [])]);  DNode("g", [DNode("j", [])])]);;

(* BASIC REPOSITORY FUNCTIONS - you can add more *)

let size rep = (* number of individuals *)
	len rep

let all1 rep = (* all the individuals *)
	map fst rep

let all2 rep = (* all the children (of anyone) *)
	cFlatMap snd rep

let roots rep = (* individuals without any parents *)
	diff (all1 rep) (all2 rep)

let inners rep = (* individuals with children *)
	let xs = filter (fun (p,cs) -> cs <> []) rep in
		all1 xs

let leaves rep = (* individuals without any children *)
	let xs = filter (fun (p,cs) -> cs = []) rep in
		all1 xs

let cut1 rep l = (* partition based on first component of the repository *)
	partition (fun (p,cs) -> mem p l) rep

let cut2 rep l = (* partition based on second component of the repository *)
	partition (fun (p,cs) -> inter cs l <> []) rep

let cut rep = (* partition -> (root pairs, rest pairs) *)
	cut1 rep (roots rep)

let children rep l = (* get all the children of the list l *)
	let (a,b) = cut1 rep l in
		all2 a

let rec parents rep l = (* get all the parents of the list l *)
	let (a,b) = cut2 rep l in
		all1 a

(* AUX REPOSITORY FUNCTIONS - that we added*)
let rec heightOfElem rep elem =
	match rep with
	| [] -> -1
	| (x,l)::xs ->
		if existsInRep elem rep then
			let papas = (parents rep [elem]) in
				1 + lheightOfElem rep papas
		else -1
and lheightOfElem r ls =
	match ls with
	| [] -> 0
	| y::ys->
		let h = 1 + lheightOfElem r (parents r [y]) in
			if h > lheightOfElem r ys then h
			else lheightOfElem r ys
;;

let rec existsInRep elem rep =
	match rep with
	| [] -> false
	| (x,_)::xs ->
		if elem = x then true
		else existsInRep elem xs
;;

let rec existsPair p rep =
	match rep with
	| [] -> false
	| (x, _)::xs -> p = x || existsPair p xs
;;

let rec addToRep elem lst rep =
	match rep with
	| [] -> [(elem, lst)]
	| (x, l)::xs ->
		if elem = x then
			(x, union lst l)::xs
		else
			(x, l)::addToRep elem lst xs
;;

let rec joinPairs rep =
	match rep with
	| [] -> []
	| (x, l)::xs -> clean (addToRep x l (joinPairs xs))
;;

let rec hasChild p c rep =
	match rep with
	| [] -> false
	| (x, l)::xs ->
		if p = x then (mem c l)
		else
			hasChild p c xs
;;


(* AUX ATREE FUNCTIONS that we added*)
let getARoot t =
	match t with
	| ANil -> ""
	|	ANode(x,_,_) -> x
;;

let rec createPairs t =
	match t with
	| ANil -> []
	| ANode(x,l,r) ->
		if l != ANil then
			if r != ANil then [(getARoot l, [x]);
			(getARoot r, [x])] @ union (createPairs l) (createPairs r)
			else [(getARoot l, [x])] @ union (createPairs l) (createPairs r)
		else
			if r != ANil then [(getARoot r, [x])] @ union (createPairs l) (createPairs r)
			else union (createPairs l) (createPairs r)
;;

let rec initiateRep t =
	match t with
	| ANil -> []
	| ANode(x,l,r) -> union [(x,[])] (union (initiateRep l) (initiateRep r))
;;


(* AUX DTREE FUNCTIONS that we added*)
let getDRoot t =
	match t with
	| DNil -> ""
	| DNode(x,_) -> x
;;

let rec listOfDRoots t =
    match t with
      | [] -> []
      | x::xs -> getDRoot x :: listOfDRoots xs
;;

(* FUNCTION height *)

let rec height rep =
	if rep = [] then 0
	else let (_, b) = cut rep in 1 + height b
;;


(* FUNCTION makeATree *)

let rec makeATree rep a =
	match parents rep [a] with
	| [] -> ANode(a, ANil, ANil)
	| [x] -> ANode(a, makeATree rep x, ANil)
	| x::y::_ -> ANode(a, makeATree rep x, makeATree rep y) 
;;


(* FUNCTION repOfATree *)

let rec repOfATree t =
	match t with
	| ANil -> []
	| ANode(x, ANil, ANil) -> [(x, [])]
	| ANode(x, l, r) -> joinPairs
		((x, [])::
		(getARoot l, [x])::
		(getARoot r, [x])::
		(repOfATree l @ repOfATree r))
;;


(* FUNCTION makeDTree *)

let rec makeDTree rep a =
	match children rep [a] with
	| [] -> DNode(a, [])
	| [x] -> DNode(a, [makeDTree rep x])(*simp*)
	| l -> DNode(a, lmakeDTree rep a l) (*simp*)

and lmakeDTree rep a l =
	match l with
	| [] -> []
	| y::ys -> (makeDTree rep y)::(lmakeDTree rep a ys)
;;


(* FUNCTION repOfDTree *)

let rec repOfDTree t =
    match t with
    | DNil -> []
    | DNode(x, xs) -> union [(x, listOfDRoots xs)] (lrepOfDTree xs)
	
and lrepOfDTree tl =
    match tl with
    | [] -> []
    | y::ys -> (repOfDTree y)@(lrepOfDTree ys)
;;


(* FUNCTION descendantsN *)

let rec descendantsN rep i lst =
	if i = 0 then
		lst
	else
		descendantsN rep (i - 1) (children rep lst)
;;


(* FUNCTION siblings *)

let rec siblings rep lst =
	match lst with
	| []-> []
	| x::xs->  union (rsiblings rep x) (siblings rep xs)
and rsiblings r e=
	match r with
	| []->[]
	| (y,l)::ys ->
		if hasChild y e r then
			union (rsiblings ys e) (children r [y])
		else
			union [e] (rsiblings ys e)
;;


(* FUNCTION siblingsInbreeding *)
let rec pairsWithCommonChild rep l =
	match l with
	| [] -> []
	| (a, b)::xs ->
		if inter (children rep [a]) (children rep [b]) <> [] then
			(a, b)::pairsWithCommonChild rep xs
		else
			pairsWithCommonChild rep xs
;;

let rec pairs l =
	match l with
	| [] -> []
	| [x] -> []
	| x::xs -> (lpairs x xs) @ (pairs xs)

and lpairs a l =
	match l with
	| [] -> []
	| [x] -> [(a, x)]
	| x::xs -> (a, x)::lpairs a xs
;;

let rec siblingsInbreedingAux rep =
	match rep with
	| [] -> []
	| (_, l)::xs -> pairs l @ siblingsInbreedingAux xs
;;

let siblingsInbreeding rep =
	clean (pairsWithCommonChild rep (siblingsInbreedingAux rep))
;;

(* FUNCTION waveN *)

let rec waveNAux rep n lst v =
	if n = 0 then
		lst
	else if n = 1 then
		diff ((parents rep lst) @ (children rep lst)) v
	else
		let visited = (children rep lst) @ (parents rep lst) in
    		waveNAux rep (n - 1) visited (lst @ visited @ v)
;;

let waveN rep n lst =
	waveNAux rep n lst []
;;


(* FUNCTION merge *)

let rec merge rep1 rep2 =
	match rep2 with
	| [] -> rep1
	| (x, l)::xs -> merge (addToRep x l rep1) xs
;;


(* FUNCTION supremum *)

(*returns list of ancestors (from the bottom to the top)*)
let rec listOfAncestors rep v =
	match rep with
	| [] -> []
	| _ ->
		if existsInRep v rep then
			union (parents rep [v]) (llistOfAncestors rep (parents rep [v]))
		else []
and llistOfAncestors r ls =
	 match ls with
	| [] -> []
	| y::ys ->
		let p = parents r [y] in
			union (union p (llistOfAncestors r p)) (llistOfAncestors r ys)
;;

(*recebe uma lista de elementos e devolve uma lista de elementos*)
(* com a maior altura de todos da listas*)
let rec getBottomLevel rep l =
	match l with
	| [] -> []
	| x::xs ->
		if xs != [] then
			let h = (heightOfElem rep x) in
				let b = lgetBottomLevel rep (getBottomLevel rep xs) in
    			if h > b then [x]
    			else
    				if h = b then [x] @ getBottomLevel rep xs
    				else getBottomLevel rep xs
		else [x]
and lgetBottomLevel r ls =
	match ls with
	| [] -> 0
	| [y] -> heightOfElem r y
	| y::ys -> heightOfElem r y
;;


let rec supremum rep s =
	match s with
	| [] -> []
	| x::[] -> listOfAncestors rep x
	| x::xs ->
		getBottomLevel rep (inter (listOfAncestors rep x) (supremum rep xs))
;;

(* FUNCTION validStructural *)

let validStructural rep = 
	let all = all1 rep in
        diff all (clean all) = [] &&
		for_all(fun x -> existsPair x rep) (all2 rep)
;;

(* FUNCTION validSemantic *)
let exampleFFF = [
    ("g",[]);
    ("a",["f";"g"]);
    ("f",["a"])]
;;

let rec checkLoop rep =
	match rep with
	| []-> false
	| (x,_)::xs ->
		(hasLoop rep [x] x) || checkLoop xs
;;

let rec hasLoop rep l v =
	let p = (parents rep [v]) in
	match l with
	| []
	| _::_->
  	if (inter l p) != [] then true
  	else
  		lhasLoop rep l p
and lhasLoop r li lp =
 	match lp with
 	| [] -> false
 	| x::xs ->
		let nlist = x::li in
  		(hasLoop r nlist x) || (lhasLoop r li xs)
;;	

let validSemantic rep =
	false


