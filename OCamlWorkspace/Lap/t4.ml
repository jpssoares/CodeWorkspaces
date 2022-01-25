
(* Teorica 4 (24/03/2021)*)

(*-------------------Tipos soma em OCaml---------------------------*)
type color = int ;;
type point = float*float ;;

type cshape = Line of color*point*point
            | Circle of color*point*float
            | Rect of color*point*point ;;

(* Chamam-se etiquetas, aos nomes das variantes. *)

(* Literais: Eis dois literais de tipo cshape. *)
let a = Line (34658, (2.5, 7.8), (-24.005, 1000.0001)) ;;
let b = Circle (11111, (-24.005, 1000.0003), 3.1233333) ;;

(* Construção: Por exemplo, a seguinte função cria círculos centrados no ponto zero: *)
let zeroCircle c r = Circle (c, (0.0, 0.0), r) ;;

zeroCircle 11112 2.0000000;;

(*Processamento: Eis uma função que calcula a área duma forma colorida.*)
(* Os elementos de qualquer tipo soma são processados*)
(* usando emparelhamento de padrões. *)

let area cs =
    match cs with
        Line   (_, _, _) -> 0.0
      | Circle (_, _, r) -> 3.14159 *. r *. r
      | Rect   (_, (tx,ty), (bx,by)) -> abs_float ((bx -. tx) *. (by -. ty))
;;

(*Eis uma função que determina o raio duma forma. Só está definida para círculos. *)
let radius (Circle (_, _, r)) = r ;;(* Isto vai dar origem ao erro Warning 8: this pattern-matching is not exhaustive.*)

(*-------------------Arvores binarias---------------------------*)


type 'a tree = Nil | Node of 'a * 'a tree * 'a tree ;;


let t = Node(1, Node(2,Nil,Nil), Node(3,Nil,Nil)) ;;

let makeLeaf v = Node(v, Nil, Nil) ;;

let rec size t =
    match t with
        Nil -> 0
      | Node(x,l,r) ->
            1 + size l + size r
;;

size t;;


(* height: 'a tree -> int *)

let rec height t =
    match t with
       Nil -> 0
     | Node(x,l,r) ->
           1 + max (height l) (height r)
;;

height t;;
height Nil;;

(* mirror: 'a tree -> 'a tree *)

let rec mirror t =
    match t with
      Nil -> Nil
    | Node (x,l,r) ->
           Node (x,mirror r,mirror l) (* o r e l trocam de posição *)
;;

mirror t;;

(*-------------------Arvores n-arias---------------------------*)

(*Numa árvore n-ária, cada nó pode ter um número qualquer (ilimitado) de filhos. *)

type 'a ntree = NNil | NNode of 'a * 'a ntree list ;;


let nt = NNode(1, [NNode(2,[]); NNode(3,[]); NNode(4,[])]) ;;

let makeLeaf v = NNode(v, []) ;;


(* size: 'a ntree -> int *)

let rec size t =
    match t with
        NNil -> 0
      | NNode(x,cs) -> 1 + lsize cs
and lsize tl =
    match tl with
        [] -> 0
      | t::ts -> size t + lsize ts
;;


(* mirror: 'a ntree -> 'a ntree *)

let rec mirror t =
    match t with
        NNil -> NNil
      | NNode(x,cs) -> NNode(x,lmirror cs)
and lmirror tl =
    match tl with
        [] -> []
      | t::ts -> lmirror ts @ [mirror t]
;;





