

let rec fact n =
    if n = 0 then 1
    else n * fact (n-1)
;;

let rec len l =
    match l with
      | [] -> 0
      | x::xs -> 1 + len xs
;;

len [1;2;3;4;5];;

let rec sum l = 
		match l with
      | [] -> 0
			| x::xs -> x + sum xs
;;

sum [1;2;3;4;5];;

let rec append l1 l2 =
		match l1 with
      | [] -> l2
			| x::xs -> x:: append xs l2
;;

append [3;4;5] [1;2] 

let rec endOfList v l =
		match l with
      | [] -> [v]
			| x::xs -> x:: endOfList v xs
;;

endOfList 4 [1;2;3]

let rec rev l =
		match l with
		| [] -> []
		| x ::xs -> endOfList x (rev xs)

;;

rev  [1;2;3;4;5];;

let rec maxList l = (* pre: l <> [] *)
    match l with
			| [] -> failwith "maxList: empty arg"
      | [x] -> x
      | x::xs -> max x (maxList xs)
;;

maxList[1;2;3;4;5];;
maxList[];;

let rec map f l =
		match l with
      | [] -> []
			| x::xs -> f x:: map f xs
;;

map (fun x -> x+1) [1;2;3];;

let rec filter b l =
		match l with
      | [] -> []
			| x::xs -> if b x then x:: filter b xs
								else filter b xs
;;

filter (fun x -> x mod 2 = 0) [1;2;3;4;5];;

let rec flatMap f l =
		match l with
      | [] -> []
			| x::xs -> append(f x) (flatMap f xs)
;;

flatMap(fun x -> [x;x]) [1;2;3]

let rec insOrd v l =
    match l with
      | [] -> [v]
      | x::xs ->
          if v <= x then v::x::xs
          else x::insOrd v xs
;;

let rec sortList l =
    match l with
      | [] -> []
      | x::xs ->
          insOrd x (sortList xs)
;;






let rec fusion l1 l2 = (* pre: l1, l2 sorted with no repetitions *)
    match l1, l2 with
      | _, [] -> l1
      | [], _ -> l2
      | x::xs, y::ys ->
          if x < y then x::fusion xs l2
          else if y < x then y::fusion l1 ys
          else x::fusion xs ys
;;



