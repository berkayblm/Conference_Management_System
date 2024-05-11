
document.getElementById("continueAsGuest").addEventListener("click", function(event) {
    event.preventDefault(); // Prevents the link from navigating to another page

    sessionStorage.clear();
    window.location.href = "index.html";
});

