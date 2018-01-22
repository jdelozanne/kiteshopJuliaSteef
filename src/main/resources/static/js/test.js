/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//function doThis() {
//    var tekst = "julia";
//    console.log(tekst);
//    console.log(this.a);
//}
//
//doThis();
//
//var a = doThis.bind({a: "hoi"});
//
//a();
//var b = {this: "hee"};
//console.log(b.this);
//
//var c =
//        {
//            d: 500,
//            dd: "string"
//        };
//
//for (const prop in c) {
//    if (typeof prop === "number") {
//        console.log(prop);
//    }
//    //console.log(prop);
//}
function* NinjaGenerator(action) {
    const imposter = yield ("Hattori " + action);
    if(imposter === "Hanzo"){
        console.log("The generator has been infiltrated");};
    yield ("Yoshi (" + imposter + ") " + action);
}

const ninjaIterator = NinjaGenerator("skulk");
console.log(ninjaIterator);
const result1 = ninjaIterator.next();
console.log(result1);
//assert(result1.value === "Hattori skulk", "Hattori is skulking");

function* GeneratorFunction(act){
    yield  act + " how to";
    yield act + "dont";
}

const generate = GeneratorFunction("acting");
console.log(generate.next());
console.log(generate.next("playing"));
//const generated2 = generate.next("playing");
//console.log(generated1 + generated2);


