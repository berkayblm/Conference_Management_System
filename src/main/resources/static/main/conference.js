
const getConferences = async () => {
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

            const conferenceListDiv = document.querySelector(".conference-list");

            // Create select element
            const selectElement = document.createElement('select');
            selectElement.className = 'conference-select'; // Optional: add class name for styling

            // Append select element to the div
            conferenceListDiv.appendChild(selectElement);

            // Create and append options for each conference
            conferenceList.forEach(conference => {
                const option = document.createElement('option');
                option.value = conference.conferenceId; // Set value to conference ID
                option.textContent = conference.title; // Set text to conference title
                selectElement.appendChild(option);
            });


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

getConferences();
