
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