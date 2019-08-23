import React from 'react';
import {connect} from "react-redux";
import Lobby from "./Lobby";
import Landing from "./Landing";
import Login from './Login';
import Signup from './Signup';
import firebase from "../data/fbConfig";
import {sendToServer, handleCheckInOut} from "../redux/actions";


const mapStateToProps = (state) => {
  const appContent = state.appContent;
  // ----------------------------------------------- for development purposes
  const user = state.user;
  const courts = state.courts;
  const checkedIn = state.checkedIn;
  // -----------------------------------------------

  return {appContent, user, courts, checkedIn};
}

const App = (props) => {
  // props.sendToServer("rendering...");

  // choose content to render

  let content = <Lobby/>

  if (props.appContent === "landing") {
    content = <Landing/>;
  }
  else if (props.appContent === "login") {
    content = <Login/>
  }
  else if (props.appContent === "signup") {
    content = <Signup/>
  }

  console.log("from firebase::");
  console.log(firebase.auth().currentUser);
  console.log("from state::");
  console.log(props.user);

  // console.log("courts from state::");
  // console.log(props.courts);


  // render content
  return(
    <React.Fragment>
      {content}
    </React.Fragment>
  )
}

export default connect(mapStateToProps, {sendToServer, handleCheckInOut})(App);
