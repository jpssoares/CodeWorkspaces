(* Module body BinTree *)

type tree = Nil | Node of int * tree * tree

let rec make l =
	match l with
	| [] -> Nil
	| x::xs -> Node(x, Nil, make xs)

let isEmpty t = 
	t = Nil

let rec max t =
	match t with
	| Nil -> failwith "max: 'arvore vazia"
	| Node(x,Nil,Nil) -> x
	| Node(x,l,Nil) -> Pervasives.max x (max l)
	| Node(x,Nil,r) -> Pervasives.max x (max r)
	| Node(x,l,r) -> Pervasives.max x (Pervasives.max (max l) (max r))

let rec loadChannel ci =
	try
		let s = input_line ci in
			if s = "-" then Nil
			else
				let l = loadChannel ci in
					 let r = loadChannel ci in
							Node(int_of_string s, l, r)
	with End_of_file -> failwith "load: leitura para la' do fim do ficheiro"
	
	
let load ni =
	let ci = open_in ni in
		let t = loadChannel ci in
			close_in ci; t
	
let store no t =
	()
	
let show t =
	()
