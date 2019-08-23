
import React from 'react';
import { Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem } from 'reactstrap';
import {toggleBar, goTo, unsetUser} from "../redux/actions";
import {connect} from "react-redux";
import firebase from "../data/fbConfig";


const mapStateToProps = (state) => {
  const collapsed = state.barCollapsed;
  const user = state.user;
  return {collapsed, user};
}

const logOutUser = (props) => {
  firebase.auth().signOut()
    .then(() => {props.unsetUser()})
    .catch((error) => {
    // Handle errors
    var errorCode = error.code;
    var errorMessage = error.message;
    console.log(errorCode + "--->" + errorMessage);
  });
}

const OptionsBar = (props) => {
  const signUp = props.user === null ? 
    <NavItem>
      <button className="backgroundlessBtn barOption" onClick={() => props.goTo("signup")}>Create acount</button>
    </NavItem> :
    <React.Fragment/>

  const logIn = props.user === null ? 
    <NavItem>
      <button className="backgroundlessBtn barOption" onClick={() => props.goTo("login")}>Log in</button>
    </NavItem> :
    <React.Fragment/>

  const logOut = props.user !== null ? 
    <NavItem>
      <button className="backgroundlessBtn barOption" onClick={() => logOutUser(props)}>Log out</button>
    </NavItem> :
    <React.Fragment/>

  return (
    <div>
      <Navbar color="faded" light>
        <NavbarToggler onClick={props.toggleBar} className="mr-2"/>
        <NavbarBrand className="mr-auto">
          <button className="backgroundlessBtn barOption" onClick={() => props.goTo("landing")}>
            Court Finder App
          </button>
        </NavbarBrand>
        <Collapse isOpen={!props.collapsed} navbar>
          <Nav navbar>
            <NavItem>
              <hr/>
            </NavItem>
            <NavItem>
              <button className="barOption" onClick={() => props.goTo("landing")}>Photograpy & Iconography credits</button>
            </NavItem>
            <NavItem>
              <hr/>
            </NavItem>
          </Nav>
        </Collapse>
        <Nav>
          {signUp}
          {logIn}
          {logOut}
        </Nav>
      </Navbar>
    </div>
  )
}


export default connect(mapStateToProps, {goTo, toggleBar, unsetUser}) (OptionsBar);