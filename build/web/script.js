/* 
 * © 2022 tienhuynh-tn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
console.log('Tiên');

//CODE FOR USING SERVLET
//Creating a form object
var form = document.getElementById("form");
//What to do when the submit event occurs
form.addEventListener("submit", function (event) {
    //Delete the default function of form and manage it with JavaScript
    event.preventDefault();
    sendData();
});

function sendData() {
    //Creating a FormData object
    var formData = new FormData(form);
    //Send HTTP request with XMLHttpRequest
    var req = new XMLHttpRequest();

    //Processing when transmission / reception is completed
    req.addEventListener("load", function (event) {
        if (req.responseText !== null) {
            //Processing when processing is successful
            console.log(req.responseText);
            document.getElementById("checkLogin").innerHTML =
                    this.responseText;
        } else {
            alert("Failed to send.");
        }
    });
    
    //Processing when transmission / reception fails
    req.addEventListener("error", function (event) {
        alert("Error");
    });

    //HTTP request settings
    req.open("POST", "/get-json-response/LoginServlet"); // /application name/Servlet name
    //Send request
    req.send(formData);
}

//CODE FOR USING RESTFUL API
var usernameInput = document.querySelector('input[name="username"]');
var usernameInputText = '';
var apiOrginal = "http://localhost:8084/get-json-response/api/user/showprofile?username=";
var api = '';
usernameInput.onchange = function (e) {
    usernameInputText = e.target.value;
    console.log('username: ', usernameInputText);
    api = apiOrginal + usernameInputText;
    console.log('api: ', api);
    return api;
};

var btn = document.querySelector('button');

function getProfile() {
    var xhttp = new XMLHttpRequest();

    xhttp.open("GET", api);
    xhttp.send();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("showProfile").innerHTML =
                    this.responseText;
        }
    };
}





