import {getApp, initializeApp} from "https://www.gstatic.com/firebasejs/10.11.1/firebase-app.js";
import {
    getStorage,
    ref,
    uploadBytes, uploadBytesResumable,
    getDownloadURL
} from "https://www.gstatic.com/firebasejs/10.11.1/firebase-storage.js";


// Your web app's Firebase configuration
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
    const fileList = inputElement.files; /* now you can work with the file list */
    const selectedFile = fileList[0]
    console.log(selectedFile.name)

    const storageRef = ref(storage, 'files/' + selectedFile.name)

    uploadBytes(storageRef, selectedFile)
        .then((snapshot) => {
            console.log('Uploaded a blob or file!');
        });

    const uploadTask = uploadBytesResumable(storageRef, selectedFile);

    // Register three observers:
    // 1. 'state_changed' observer, called any time the state changes
    // 2. Error observer, called on failure
    // 3. Completion observer, called on successful completion
    uploadTask.on('state_changed',
        (snapshot) => {
            // Observe state change events such as progress, pause, and resume
            // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
            const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
            console.log('Upload is ' + progress + '% done');
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
        },
        () => {
            // Handle successful uploads on complete
            // For instance, get the download URL: https://firebasestorage.googleapis.com/...
            getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
                console.log('File available at', downloadURL);
                //todo: handle downloadURL
                submitPaper(downloadURL, document.getElementById("paper_title").value);
            });
        }
    );

}


const submitPaper = async (downloadURL, paperTitle) => {
    const url = 'http://localhost:8080/api/papers/submitPaper';
    const data = {
        title: paperTitle,
        paperUrl: downloadURL,
        senderUserId: 2,
        conferenceId: 2
    };

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            console.log('Paper submitted successfully, şimdi biat et bana köle');
            // You can handle success actions here, such as showing a success message to the user
        } else {
            console.error('Failed to submit paper');
            // Handle error scenarios here
        }
    } catch (error) {
        console.error('Error submitting paper:', error);
        // Handle network errors or other exceptions
    }
};

// Call the function to submit the paper
// submitPaper();


