/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");

function  checkStrengthPassword()
{

    //Store the password field objects into variables ...
    var pass1 = document.getElementById('pass1');

    var permission = document.getElementById('register');
    //Store the Confimation Message Object ...
    var message = document.getElementById('validationMessage');
    //Set the colors we will be using ...
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
    //Compare the values in the password field 
    //and the confirmation field
    if (pass1.value === strongRegex) {
        //The passwords match. 
        //Set the color to the good color and inform
        //the user that they have entered the correct password 

        pass1.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "You choosen a strong password!";
        permission.removeAttribute("disabled");

    } else {
        //The passwords do not match.
        //Set the color to the bad color and
        //notify the user.
        permission.setAttribute("disabled", "disabled");
        pass1.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Password too weak! \n\
                                Password should contain at least:\n\
                                1 digit\n\
                                1 capital letter\n\
                                1 special character\n\
                                total 8 characters";
    }
}  