(* Module body BinTree *)

type tree = Nil | Node of int * tree * tree

let rec make l =
	match l with
	| [] -> Nil
	| x::xs -> Node(x, Nil, make xs)

let isEmpty t = 
	t = Nil

let max t =
	match t with
	| Nil -> failwith ""
	
let load ni =
	Nil
	
let store no t =
	()
	
let show t =
	()
