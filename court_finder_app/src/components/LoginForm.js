import React, {Component} from 'react';
import { Form, FormGroup, Button, Label, Input, Alert } from 'reactstrap';
import {connect} from "react-redux";
import { goTo, retrieveUser } from '../redux/actions';
import firebase from "../data/fbConfig";


const mapStateToProps = (state) => {
  const loginMessage = state.loginMessage;
  return {loginMessage};
}

class LoginForm extends Component {
  constructor(props) {
    super(props);

    this.state = {
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

  attemptLogin(email, password) {
    firebase.auth().signInWithEmailAndPassword(email, password)
      .then(() => {this.props.retrieveUser(firebase.auth().currentUser.uid)})
      .catch(error => {
      // Handle errors AND HANDLE ERROR MESSAGE FEEDBACK TO USER (wrong password, no existing username, etc)
      console.log("Error logging in");
      console.log(error.code + "--->" + error.message);
    });
  }

  render() {
    const email = this.state.email;
    const pass = this.state.password;
    const alert = this.props.loginMessage === "" ?
      <React.Fragment/> :
      <Alert color="info">{this.props.loginMessage}</Alert>

    return(
      <Form>
        {alert}
        <FormGroup>
          <Label for="emailInput"></Label>
          <Input onChange={(event) => this.handleEmail(event.target.value)} value={email} type="email" id="emailInput" placeholder="email" />
        </FormGroup>
        <FormGroup>
          <Label for="passwordInput"></Label>
          <Input onChange={(event) => this.handlePassword(event.target.value)} value={pass} type="password" id="passwordInput" placeholder="password" />
        </FormGroup>
        <Button onClick={() => this.attemptLogin(email, pass)} color="info">Log in</Button>
      </Form>
    );
  }
}
export default connect(mapStateToProps, {goTo, retrieveUser}) (LoginForm);