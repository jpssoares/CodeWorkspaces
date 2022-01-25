(* Module interface BinTree *)

type tree 

(* Verifica se uma arvore e' vazia *)
val isEmpty: tree -> bool

(* Cria uma arvore com os elementos da lista alinhados sempre para a direita *)
val make : int list -> tree

(* Determina o valor maior que ocorre numa arvore *)
val max : tree -> int

(* Carrega uma arvore a partir dum ficheiro de texto *)
val load : string -> tree

(* Escreve uma arvore num ficheiro de texto *)
val store : string -> tree -> unit

(* Mostra uma arvore num ficheiro de texto *)
val show : tree -> unit
