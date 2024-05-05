
document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevents the form from submitting in the traditional way


    window.location.href = "index.html";


});

document.getElementById("continueAsGuest").addEventListener("click", function(event) {
    event.preventDefault(); // Prevents the link from navigating to another page

    // Your redirection logic here
    window.location.href = "index.html";
});

