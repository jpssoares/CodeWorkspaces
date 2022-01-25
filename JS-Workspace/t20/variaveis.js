

let x = 10;
function g(y) {
    return x + y;
}
{
    let x = 15;
    console.log(g(0));
}

// Output: 10

/*
{
    let q = 2;
}
console.log(q);
// Output: ReferenceError: x is not defined

*/
let zzz = "zzz";

console.log(zzz);   // Lança a exceção ReferenceError se zzz nao estiver definida

if( zzz === undefined ) {   // Testar se uma variável está indefinida.
    console.log("zzz is undefined");
}
if( !zzz ) {    // undefined comporta-se como false num contexto booleano
    console.log("zzz is undefined");
}

