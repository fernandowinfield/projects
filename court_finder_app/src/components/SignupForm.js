import React, {Component} from 'react';
import { Form, FormGroup, Button, Label, Input, Alert } from 'reactstrap';
import {connect} from "react-redux";
import { goTo, saveUser } from '../redux/actions';
import firebase from "../data/fbConfig";


const mapStateToProps = (state) => {
  const signupMessage = state.signupMessage;
  return {signupMessage};
}

class SignupForm extends Component {
  constructor(props) {
    super(props);

    this.state = {
      firstName: "",
      lastName: "",
      email: "",
      password: ""
    }
  }

  handleEmail(email) {
    this.setState({
      email
    });
  }

  handlePassword(password) {
    this.setState({
      password
    });
  }

  handleFirstName(firstName) {
    this.setState({
      firstName
    });
  }

  handleLastName(lastName) {
    this.setState({
      lastName
    });
  }

  createAccount(email, password, firstName, lastName) {
    firebase.auth().createUserWithEmailAndPassword(email, password)
      .then(() => {this.saveUserInfo(email, firstName, lastName)})
      .catch(function(error) {
      // Handle errors
      var errorCode = error.code;
      var errorMessage = error.message;
      console.log(errorCode + "--->" + errorMessage);
    });
  }

  saveUserInfo(email, firstName, lastName) {
    const userId = firebase.auth().currentUser.uid;
    // save user info to database here right after account creation
    this.props.saveUser(userId, firstName, lastName, email);
  }


  render() {
    const email = this.state.email;
    const pass = this.state.password;
    const firstName = this.state.firstName;
    const lastName = this.state.lastName;
    const alert = this.props.signupMessage === "" ?
      <React.Fragment/> :
      <Alert color="info">{this.props.signupMessage}</Alert>

    return(
      <Form>
        {alert}
        <FormGroup>
          <Label for="firstNameInput"></Label>
          <Input onChange={(event) => this.handleFirstName(event.target.value)} value={firstName} id="firstNameInput" placeholder="First name" />
        </FormGroup>
        <FormGroup>
          <Label for="lastNameInput"></Label>
          <Input onChange={(event) => this.handleLastName(event.target.value)} value={lastName} id="lastNameInput" placeholder="Last name" />
        </FormGroup>
        <FormGroup>
          <Label for="emailInput"></Label>
          <Input onChange={(event) => this.handleEmail(event.target.value)} value={email} type="email" id="emailInput" placeholder="email" />
        </FormGroup>
        <FormGroup>
          <Label for="passwordInput"></Label>
          <Input onChange={(event) => this.handlePassword(event.target.value)} value={pass} type="password" id="passwordInput" placeholder="password" />
        </FormGroup>
        <Button onClick={() => this.createAccount(email, pass, firstName, lastName)} color="info">Submit</Button>
      </Form>
    );
  }
}
export default connect(mapStateToProps, {goTo, saveUser}) (SignupForm);