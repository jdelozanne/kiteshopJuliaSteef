/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function myFunction() {
    document.getElementById("demo").innerHTML = "Hello World";
}
var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}");

function  checkValidation()
{
    var weakRegex = new RegExp("^([a-z])");
    //Store the password field objects into variables ...
    var pass1 = document.getElementById('pass1');
    var pass2 = document.getElementById('pass2');
    //Store the Confimation Message Object ...
    var message = document.getElementById('validationMessage');
    //Set the colors we will be using ...
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
    //Compare the values in the password field 
    //and the confirmation field
    if (strongRegex.test(pass1.value)) {

        pass1.style.backgroundColor = goodColor;
        message.style.color = "black";
        message.innerHTML = "You choosen a strong password!";
        pass2.removeAttribute("disabled");

    } else {
        pass1.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Password too weak! Please use: <br>At least one Digit <br>At least one Uppercase<br> At least 8 characters";
        pass2.setAttribute("disabled", "disabled");
    }
}
function checkPass()
{

    var pass1 = document.getElementById('pass1');
    var pass2 = document.getElementById('pass2');
    var permission = document.getElementById('register');

    var message = document.getElementById('confirmMessage');
    //Set the colors we will be using 
    var goodColor = "#66cc66";
    var badColor = "#ff6666";

    if (pass1.value === pass2.value) {
        pass2.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "Passwords Match!";
        permission.removeAttribute("disabled");

    } else {
        permission.setAttribute("disabled", "disabled");
        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Passwords Do Not Match!";
    }
}  