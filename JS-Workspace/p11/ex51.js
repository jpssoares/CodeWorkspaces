// ----------------
// Ex. 51

class Succ {
    constructor(a0) {
        this.a0 = a0;
        this.current = a0;
    }
    first() {
        this.current = this.a0;
        return this.current;
    }
    curr() {
       return this.current;
    }
    at(i) {
        this.first();
        for(let j = 0 ; j < i ; j++)
            this.next();
       return this.current;
    }
    print(n) {
        this.first();
        for(let j = 0 ; j < n ; j++) {
            console.log(this.curr());
            this.next();
        }
    }
}

class Arith extends Succ {
    constructor(a0, inc) {
        super(a0)
        this.inc = inc;
    }
    next() {
        this.current += this.inc;
        return this.curr();
    }
}

class Geom extends Succ {
    // COMPLETAR
}

class Const extends Succ {
// o construtor herdado serve, nao vale a pena redefinir.
    // COMPLETAR
 }

// test
new Arith(1,2).print(10);
console.log("-----");

