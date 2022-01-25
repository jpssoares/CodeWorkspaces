// ----------------
// Ex. 52
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

class ArithOpt extends Arith {
    at(i) {
        this.current = this.a0 + i * this.inc;
        return this.curr();
    }
}

class SumComp extends Succ {
    constructor(s1, s2) {
        super(s1.first() + s2.first());
        this.s1 = s1;
        this.s2 = s2;
    }
    first() {
        this.s1.first();
        this.s2.first();
        return super.first();
    }
    next() {
        this.current = this.s1.next() + this.s2.next();
        return this.curr();
    }
}


// tests
new Arith(1,2).print(10);
console.log("-----");
console.log(new Arith(1,2).at(1000));
console.log("-----");
new SumComp(new Arith(1,2), new Arith(1,2)).print(10);
console.log("-----");


