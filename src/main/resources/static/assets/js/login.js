const pass= "password";

function myFunction() {

    let password= document.getElementById("password").value;

    if(password===pass){
        window.location.href="dashboard.html";
    } else {
        alert("Wrong Password!");
    }

}