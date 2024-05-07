
export const getConferences = async () => {
    const url = 'http://localhost:8080/api/conferences';

    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {

            const conferenceList = await response.json();

            console.log('Conferences listed', conferenceList);
            return conferenceList;



            // You can handle success actions here, such as showing a success message to the user
        } else {
            console.error('Failed to get conferences');
            // Handle error scenarios here
        }
    } catch (error) {
        console.error('Error retrieving conferences:', error);
        // Handle network errors or other exceptions
    }
};


export const getReviews = async () => {

    const reviewerId = sessionStorage.getItem("userId");

    const url = `http://localhost:8080/api/reviews/${reviewerId}`;


    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {

            const reviewList = await response.json();

            console.log('Reviews listed', reviewList);
            return reviewList;



            // You can handle success actions here, such as showing a success message to the user
        } else {
            console.error('Failed to get reviews');
            // Handle error scenarios here
        }
    } catch (error) {
        console.error('Error retrieving reviews:', error);
        // Handle network errors or other exceptions
    }

}


export const sendReview = async (reviewId, paperId, status, rating, comment) => {

    console.log(reviewId, paperId, status, rating, comment)

    const url = `http://localhost:8080/api/reviews/updateReview`;


    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({reviewId: reviewId, paperId: paperId, status: status, rating: rating, comment: comment})
        });

        if (response.ok) {

            return res;



            // You can handle success actions here, such as showing a success message to the user
        } else {
            console.error('Failed to get reviews');
            // Handle error scenarios here
        }
    } catch (error) {
        console.error('Error retrieving reviews:', error);
        // Handle network errors or other exceptions
    }

}