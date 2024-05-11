const itemPaper = document.getElementById("papers");
const itemSubmittedReviews = document.getElementById("submitted_reviews");

const itemConferenceSetup = document.getElementById("conferenceSetup")

let userRole = sessionStorage.getItem("userRole");

if(userRole === "Reviewer"){
    console.log("reviwer");
    // itemSubmittedReviews.style.display = "none";
    itemSubmittedReviews.style.display = "block"; // To show the item
    itemPaper.style.display = "none";
    try{
        itemConferenceSetup.style.display = "none";
    } catch (Exception){

    }

} else if(userRole === "Author"){
    console.log("author");

    itemSubmittedReviews.style.display = "none"; // To show the item
    itemPaper.style.display = "block";
    try{
        itemConferenceSetup.style.display = "none";
    } catch (Exception){

    }
} else if(userRole === "Organizer"){
    console.log("organizer");
    itemPaper.style.display = "none";

    itemConferenceSetup.style.display = "block";



    itemSubmittedReviews.style.display = "none";
} else {
    console.log("asdsa")
    itemSubmittedReviews.style.display = "none";
    itemPaper.style.display = "none";
    try{
        itemConferenceSetup.style.display = "none";
    } catch (Exception){

    }
    console.log(userRole);
}

updateNavbar();
// document.addEventListener("DOMContentLoaded", function() {
//     console.log("it is being called v1")
//     updateNavbar();
// });

function updateNavbar() {
    const userName = sessionStorage.getItem("userName");
    const userRole = sessionStorage.getItem("userRole");
    console.log("it is being called")
    if (userName && userRole) {
        document.getElementById("userNameDisplay").textContent = userName;
        document.getElementById("userRoleDisplay").textContent = userRole;
        document.getElementById("userArea").style.display = "block";
        document.getElementById("loginLink").style.display = "none";
    } else {
        document.getElementById("userArea").style.display = "none";
        document.getElementById("loginLink").style.display = "block";
    }

    console.log(userName, userRole)
}

