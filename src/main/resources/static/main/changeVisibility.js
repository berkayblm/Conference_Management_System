const itemPaper = document.getElementById("papers");
const itemSubmittedReviews = document.getElementById("submitted_reviews");

let userRole = sessionStorage.getItem("userRole");
if(userRole === "Reviewer"){
    console.log("reviwer");
    // itemSubmittedReviews.style.display = "none";
    itemSubmittedReviews.style.display = "block"; // To show the item
    itemPaper.style.display = "none";

} else if(userRole === "Author"){
    console.log("author");

    itemSubmittedReviews.style.display = "none"; // To show the item
    itemPaper.style.display = "block";
} else {
    console.log("asdsa")
    itemSubmittedReviews.style.display = "none";
    itemPaper.style.display = "none";
    console.log(userRole);
}
