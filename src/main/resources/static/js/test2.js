/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}");
//var weakRegex = new RegExp(/^([a-z0-9]{5,})$/);

function  checkValidation()
{
    var weakRegex = new RegExp("^([a-z])");
var x = "juliA444";

    if (strongRegex.test(x)) {

        console.log("passed");

    } else {
       console.log("NotPassed");
   }
}

checkValidation();