import { initializeApp } from "https://www.gstatic.com/firebasejs/12.13.0/firebase-app.js";
import { getAuth, signInWithEmailAndPassword } 
from "https://www.gstatic.com/firebasejs/12.13.0/firebase-auth.js";

const firebaseConfig = {
  apiKey: "YOUR_KEY",
  authDomain: "certify-pht.firebaseapp.com",
  projectId: "certify-pht",
  appId: "1:645469897315:web:e0fd0b46368e1988e12e56"
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);

window.login = function () {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    signInWithEmailAndPassword(auth, email, password)
    .then((userCredential) => {
        userCredential.user.getIdToken().then((token) => {
            // send token to Spring Boot
            fetch("/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ token: token })
            }).then(() => {
                window.location.href = "/dashboard";
            });
        });
    })
    .catch((error) => {
        alert(error.message);
    });
}