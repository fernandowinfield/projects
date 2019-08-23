import { createStore, applyMiddleware } from "redux";
import thunkMiddleware from "redux-thunk";
import { INITIAL_STATE } from "./stateConstants";
import { GO_TO, TOGGLE_BAR, SET_USER, UNSET_USER, SET_COURTS, SET_COURTS_MAP } from "./actionTypeConstants";


function rootReducer(state = INITIAL_STATE, action) {
    switch (action.type) {
        // application wide
        case GO_TO:
            return {...state, appContent: action.payload.page};

        // OptionsBar
        case TOGGLE_BAR:
            return {...state, barCollapsed: !state.barCollapsed};

        // Signup
        case SET_USER:
            return {...state, user: action.payload, appContent: "landing"};
        case UNSET_USER:
            return {...state, user: null, appContent: "landing"};

        // CourtSearch
        case SET_COURTS:
            return {...state, courts: action.payload.courts};
        case SET_COURTS_MAP:
            console.log(action.payload.courtsMap);
            return {...state, courtsMap: action.payload.courtsMap};


        default:
            return state;
    }
}

export default createStore(rootReducer, applyMiddleware(thunkMiddleware));