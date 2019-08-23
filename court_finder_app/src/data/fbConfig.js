import firebase from "firebase/app"
import "firebase/firestore";
import "firebase/auth";

  // Your web app's Firebase configuration
  var firebaseConfig = {
    apiKey: "AIzaSyAkSnVKmyeEBV8bLVL2UssuaMrlJYl0ru4",
    authDomain: "courtfinderapp-69ad4.firebaseapp.com",
    databaseURL: "https://courtfinderapp-69ad4.firebaseio.com",
    projectId: "courtfinderapp-69ad4",
    storageBucket: "gs://courtfinderapp-69ad4.appspot.com/",
    messagingSenderId: "151503041821",
    appId: "1:151503041821:web:9ac959a95a6375cf"
  };
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);

  export default firebase;
