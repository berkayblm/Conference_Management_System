import {getApp, initializeApp} from "https://www.gstatic.com/firebasejs/10.11.1/firebase-app.js";
import {
    getStorage,
    ref,
    uploadBytes, uploadBytesResumable,
    getDownloadURL
} from "https://www.gstatic.com/firebasejs/10.11.1/firebase-storage.js";

import {getConferences} from "./api/api.js"

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


const firebaseConfig = {
    apiKey: "AIzaSyCQiv1dgKBHYZIxNvVsUB7u11mHnUroiZ8",
    authDomain: "conference-management-sy-81c69.firebaseapp.com",
    projectId: "conference-management-sy-81c69",
    storageBucket: "conference-management-sy-81c69.appspot.com",
    messagingSenderId: "565707700208",
    appId: "1:565707700208:web:48afdcab1c63dc46678f35"
};


// Initialize Firebase
const app = initializeApp(firebaseConfig);

const storage = getStorage()



const inputElement = document.getElementById("uploadInput")
document.getElementById("send_button").addEventListener("click", handleFiles, false);

function handleFiles() {
    const fileList = inputElement.files;
    const selectedFile = fileList[0];
    console.log(selectedFile.name);

    const storageRef = ref(storage, 'files/' + selectedFile.name);

    // Show progress bar container
    document.getElementById("progressBarContainer").style.display = 'block';

    const uploadTask = uploadBytesResumable(storageRef, selectedFile);

    uploadTask.on('state_changed',
        (snapshot) => {
            const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
            console.log('Upload is ' + progress + '% done');
            document.getElementById("progressBar").style.width = progress + '%'; // Update progress bar width

            switch (snapshot.state) {
                case 'paused':
                    console.log('Upload is paused');
                    break;
                case 'running':
                    console.log('Upload is running');
                    break;
            }
        },
        (error) => {
            // Handle unsuccessful uploads
            console.error('Upload failed: ', error);
            alert('Error during file upload: ' + error);
        },
        () => {
            getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
                console.log('File available at', downloadURL);
                submitPaper(downloadURL, document.getElementById("paper_title").value);
                document.getElementById("progressBarContainer").style.display = 'none'; // Hide progress bar after upload
                document.getElementById("progressBar").style.width = '0%'; // Reset progress bar
            });
        }
    );
}


const submitPaper = async (downloadURL, paperTitle) => {
    const url = 'http://localhost:8080/api/papers/submitPaper';

    const selectElement = document.querySelector('.conference-select');
    const selectedConferenceId = selectElement.value;

    const abstractValue = document.getElementById("abstract").value;
    const keywordsValue = document.getElementById("keywords").value;

    const data = {
        title: paperTitle,
        paperAbstract: abstractValue,
        keywords: keywordsValue,
        paperUrl: downloadURL,
        senderUserId: sessionStorage.getItem("userId"),
        conferenceId: selectedConferenceId
    };

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        // existing code...
        if (response.ok) {
            showSuccessPopup("Paper submitted successfully!"); // Show success popup
        } else {
            console.error('Failed to submit paper');
        }
    } catch (error) {
        console.error('Error submitting paper:', error);
        // Handle network errors or other exceptions
    }
};


const fetchConferences = async () => {
    try {
        const conferenceList = await getConferences();
        console.log('Returned conference list:', conferenceList);
        // You can use the conferenceList object here


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


    } catch (error) {
        console.error('Error fetching conferences:', error);
    }
};

// Call the function
fetchConferences();


console.log(sessionStorage.getItem("userId"), sessionStorage.getItem("userRole"), sessionStorage.getItem("userName"))



function showSuccessPopup(message) {
    const popup = document.createElement('div');
    popup.className = 'success-popup';
    popup.textContent = message;
    document.body.appendChild(popup);
    setTimeout(() => {
        popup.style.bottom = '20px'; // Animate popup sliding up
    }, 100);
    setTimeout(() => {
        popup.style.bottom = '-100px'; // Animate popup sliding down after 4 seconds
    }, 4000);
    setTimeout(() => {
        document.body.removeChild(popup); // Remove popup after it slides down
    }, 4500);
}




