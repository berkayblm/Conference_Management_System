import {getReviews, sendReview} from "./api/api.js"



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
                <h3>${paper.paper.title}</h3>
                <div style="display: flex; justify-content: space-between; gap: 200px;">
                    <a>${paper.paper.conference.title}</a>
                    <div>
                        <a href="${paper.paper.paperUrl}" download>
                            <button class="btn" style="background-color: #00c6a9; color: white;" class="btn">View Paper</button>
                        </a>
                        <button class="btn-send-review" type="button" style="background-color: #00c6a9; color: white;">Send Review</button>
                    </div>
                </div>`;
            paperListDiv.appendChild(li);

            // Add event listener to the button
            const button = li.querySelector('.btn-send-review');
            button.addEventListener('click', () => {
                // Create popup
                const popup = document.createElement('div');
                popup.classList.add('review-popup');
                popup.innerHTML = `
        <div class="popup-content">
    <button id="close-btn" class="close-btn">&times;</button>
    <textarea id="comment" placeholder="Enter your comment"></textarea>
    <input type="number" id="rating" placeholder="Enter your rating (1-5)">
    <select id="status">
        <option value="Accepted">Accepted</option>
        <option value="Revision">Revision</option>
        <option value="Rejected">Rejected</option>
    </select>
    <button id="send-review-btn" class="btn" style="background-color: #00c6a9; color: white;">Send Review</button>
</div>
    `;
                document.body.appendChild(popup);  // Append the popup to the body or a specific visible area.

                // Handle Close Button
                const closeButton = popup.querySelector('#close-btn');
                closeButton.addEventListener('click', () => {
                    popup.remove();  // Remove the popup from the DOM
                });

                // Add event listener to the "Send Review" button in the popup
                const sendReviewBtn = popup.querySelector('#send-review-btn');
                sendReviewBtn.addEventListener('click', async () => {
                    const comment = popup.querySelector('#comment').value;
                    const rating = popup.querySelector('#rating').value;
                    const status = popup.querySelector('#status').value;
                    // You can send the review data here, for example:
                    const result = await sendReview(paper.reviewId, paper.paper.paperId, status, rating, comment);

                    if (result.success) {
                        showSuccessMessage(result.message);
                    } else {
                        // You could also implement an error message display using a similar approach
                        alert(result.message);  // Using alert just as an example, better to use a styled notification
                    }

                    // Close the popup
                    li.removeChild(popup);
                });
            });

            console.log(paper.paper)
        });

    } catch (error) {
        console.error('Error fetching reviews:', error);
    }

}

getReviewsAsyc();

const closeButton = popup.querySelector('#close-btn');
closeButton.addEventListener('click', () => {
    // Close the popup and remove blur effect
    li.removeChild(popup);
    document.body.classList.remove('blur-background');
});


function showSuccessMessage(message) {
    const messageBox = document.createElement('div');
    messageBox.className = 'success-message';
    messageBox.textContent = message;
    document.body.appendChild(messageBox);
    setTimeout(() => {
        messageBox.style.bottom = '20px'; // Animate message sliding up
    }, 100);
    setTimeout(() => {
        messageBox.style.bottom = '-100px'; // Animate message sliding down after 4 seconds
    }, 4000);
    setTimeout(() => {
        document.body.removeChild(messageBox); // Remove message
    }, 4500);
}
