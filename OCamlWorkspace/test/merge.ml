module StrMap = Map.Make(String)

let list1 = [ "a", 1; "a", 4; "b", 2 ]
let list2 = [ "a", 5; "b", 1; "c", 3 ]

let map1 = List.to_seq list1 |> StrMap.of_seq
let map2 = List.to_seq list2 |> StrMap.of_seq

let max_merge =
  StrMap.merge (fun key x y ->
      match x, y with
      | Some a, None -> Some a
      | None, Some b -> Some b
      | Some a, Some b -> Some (max a b)
      | None, None -> None (* Shouldn't happen but silences a warning. *))

let map3 = max_merge map1 map2
let list3 = StrMap.bindings map3 (* [("a", 5); ("b", 2); ("c", 3)] *)
