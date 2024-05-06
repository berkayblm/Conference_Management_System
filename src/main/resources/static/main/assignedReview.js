import {getReviews} from "./api/api.js"

document.addEventListener("DOMContentLoaded", function() {
    var userRole = sessionStorage.getItem("userRole");
    if (userRole === 'Reviewer') {
        var ul = document.querySelector('.navbar-nav');
        var li = document.createElement('li');
        li.classList.add('nav-item');
        var a = document.createElement('a');
        a.classList.add('nav-link');
        a.href = 'assigned_reviews.html';
        a.textContent = 'Submitted Reviews';
        li.appendChild(a);
        ul.appendChild(li);
    }
});




const getReviewsAsyc = async () => {

    try {
        const reviewList = await getReviews();

        console.log('Returned review list:', reviewList);

        // Process the data and generate paper list
        const paperListDiv = document.getElementById('customList');
        reviewList.forEach(paper => {
            const li = document.createElement('li');
            li.classList.add('custom-list');
            li.style.display = 'flex';
            li.style.justifyContent = 'space-between';
            li.style.alignItems = 'center';
            li.innerHTML = `
                    <h3>${paper.paper.title}</h3>- <p> ${paper.paper.paperUrl}</p>
                    <div>
     
                        <button class="btn" type="button"  style="background-color: #00c6a9; color: white;" class="btn">Send Review</button>
                    </div>`;
            paperListDiv.appendChild(li);

            // Add event listener to the button
            const button = li.querySelector('.btn');
            button.addEventListener('click', () => {
                // Print out the paper associated with the clicked button
                console.log(paper.paper);
            });


            console.log(paper.paper)
        });

    }
    catch (error) {
        console.error('Error fetching reviews:', error);
    }



}

getReviewsAsyc();


// Function to send review to the backend
