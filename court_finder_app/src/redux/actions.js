import { GO_TO,
         TOGGLE_BAR,
         SET_USER,
         UNSET_USER,
         SET_COURTS,
         SET_COURTS_MAP } from "./actionTypeConstants";
import firebase from "../data/fbConfig";
import { checkInOut, logMessage } from "../client";


// application wide
export const goTo = (page) => ({
    type: GO_TO,
    payload: {
        page
    }
});


// OptionsBar
export const toggleBar = () => ({
    type: TOGGLE_BAR
});


// Login
export const retrieveUser = (userId) => {
    return dispatch => {
        const database = firebase.firestore();
        database
            .collection("users")
            .doc(userId)
            .get()
            .then(doc => {
                if (doc.exists) {
                    dispatch(setUser(userId, doc.data().firstName, doc.data().lastName, doc.data().email));
                }
                else {
                    console.log("Document doesn't exist in database");
                }

            })
            .catch(error => {
                console.log("Error retrieving user from database");
                console.log(error.code + " ---> " + error.message);
            });
    }
}


// SignupForm
export const saveUser = (userId, firstName, lastName, email) => {
    return dispatch => {
        const database = firebase.firestore();
        database
            .collection("users")
            .doc(userId)
            .set({
                firstName,
                lastName,
                email
            })
            .then(() => {
                console.log("User info successfully saved in database");
                dispatch(setUser(userId, firstName, lastName, email));
            })
            .catch(error => {
                console.log("ERROR saving user info to database");
                console.log(error.code);
                console.log(error.message);
            });
    }
}

export const setUser = (userId, firstName, lastName, email) => ({
    type: SET_USER,
    payload: {
        userId,
        firstName,
        lastName,
        email
    }
});

export const unsetUser = () => ({
    type: UNSET_USER
});


// CourtSearch
export const searchCourts = (city) => {
    return dispatch => {
        const database = firebase.firestore();
        database
            .collection("courts")
            .where('city', '==', city)
            .get()
            .then(snapshot => {
                if (!snapshot.empty) {
                    console.log(snapshot);
                    let courts = snapshot.docs.map(court => {
                        const info = court.data();
                        const courtInfo = {
                            name: info.name,
                            city: info.city,
                            photoURL: info.photoURL,
                            state: info.state,
                            street: info.street,
                            streetNum: info.streetNum,
                            type: info.type,
                            zipCode: info.zipCode,
                            id: court.id
                        }
                        return courtInfo
                    });
                    dispatch(setCourts(courts));
                }
                else {
                    console.log("Didn't find any courts in the given city");
                }

            })
            .then(dispatch(handleCheckInOut("newClient", "newClient")))
            .catch(error => {
                console.log("Error searching courts by city in database");
                console.log(error.code + " ---> " + error.message);
            });
    }

}



// let courts = snapshot.docs.map(court => court.data());

export const setCourts = (courts) => ({
    type: SET_COURTS,
    payload: {
        courts
    }
});


export const handleCheckInOut = (courtId, userId) => {
    return dispatch => {
        checkInOut(courtId, userId, courtsMap => {
            dispatch(setCourtsMap(courtsMap));
        });
    }
}

export const setCourtsMap = (courtsMap) => ({
    type: SET_COURTS_MAP,
    payload: {
        courtsMap
    }
});

export const sendToServer = message => {
    return dispatch => {
        logMessage(message);
    }
}