
let colors = ["Red", "Green", "Blue"];
//let colors = new Array("Red", "Green", "Blue");    // Equivalente


//Tal como em Java os índices começam em zero e existe uma propriedade length.
let len = colors.length;                           // Vale 3


//Ao contrário do Java, os arrays crescem automaticamente. Basta atribuir a uma posição inexistente para o array crescer.
let colors2 = [];                                   // Array vazio                                    
colors2[2] = "Blue";
let len = colors2.length;                           // Vale 3
let t = typeof(colors2[0]);                         // Vale undefined

colors[colors.length] = "Yellow";
// equivalente a -> colors.push("Yellow");    


//Para aceder e remover o último elemento dum array fazer:
let last = colors.pop();

//É possível escrever diretamente na propriedade length dum array para fazer um array crescer, ou para truncar o array:
colors.length = 2;

//Para concatenar dois arrays, criando um novo array, existe a operação concat. Os arrays originais não são alterados. Exemplo:
let arr1 = [1, 2, 3];
let arr2 = [4, 5, 6];
let arr3 = arr1.concat(arr2);  // concatenacao

//Para percorrer os elementos dum array pode usar-se um for clássico, mas também se pode fazer assim:
colors.forEach(function(c) { console.log(c) });      // Iteração usando função anónima

/* ou assim:
let colors = ["Red", "Green", "Blue"];
for( c of colors ) console.log(c);      // Iteração usando for-of
*/

//Eis um array a duas dimensões, 2x3:
let table = [[0, 1, 2],
             [3, 4, 5]];
let r = table[0][2];                             // Vale 2
