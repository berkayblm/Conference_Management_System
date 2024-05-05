import {getConferences} from "./api/api.js";


const fetchConferences = async () => {
    try {
        const conferenceList = await getConferences();
        console.log('Returned conference list:', conferenceList);
        // You can use the conferenceList object here

        /*const carouselContainer = document.querySelector('.owl-carousel.team_carousel');

        // Loop through the conferenceList and create conference items
        conferenceList.forEach(conference => {
            // Create conference item elements
            const itemDiv = document.createElement('div');
            itemDiv.classList.add('item');
            const boxDiv = document.createElement('div');
            boxDiv.classList.add('box');
            const imgBoxDiv = document.createElement('div');
            imgBoxDiv.classList.add('img-box');
            const img = document.createElement('img');
            img.src = 'images/konferans_ornek5.jpg';
            img.alt = 'Conference Image';
            const detailBoxDiv = document.createElement('div');
            detailBoxDiv.classList.add('detail-box');
            const titleH5 = document.createElement('h5');
            titleH5.style.color = '#00c6a9';
            titleH5.textContent = conference.title;
            const descriptionH6 = document.createElement('h6');
            descriptionH6.style.color = 'black';
            descriptionH6.textContent = conference.date;
            const venueH6 = document.createElement('h6');
            venueH6.style.color = 'black';
            venueH6.style.textAlign = 'start';
            venueH6.innerHTML = `<i class="fa fa-map-marker"></i> ${conference.location}`;
            const dateH6 = document.createElement('h6');
            dateH6.style.color = 'black';
            dateH6.innerHTML = `<i class="fa fa-calendar"></i> ${conference.date}`;
            const button = document.createElement('button');
            button.type = 'submit';
            button.style.backgroundColor = '#00c6a9';
            button.style.color = 'white';
            button.classList.add('btn');
            button.textContent = 'View Conference';

            // Append elements to the conference item
            imgBoxDiv.appendChild(img);
            detailBoxDiv.appendChild(titleH5);
            detailBoxDiv.appendChild(descriptionH6);
            detailBoxDiv.appendChild(venueH6);
            detailBoxDiv.appendChild(dateH6);
            detailBoxDiv.appendChild(button);
            boxDiv.appendChild(imgBoxDiv);
            boxDiv.appendChild(detailBoxDiv);
            itemDiv.appendChild(boxDiv);

            // Append the conference item to the carousel container
            carouselContainer.appendChild(itemDiv);

        })*/


    } catch (error) {
        console.error('Error fetching conferences:', error);
    }
};

// Call the function
fetchConferences();