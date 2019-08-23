import React, {Component} from 'react';
import { Form, FormGroup, Button, Label, Input } from 'reactstrap';
import {connect} from "react-redux";
import { searchCourts } from '../redux/actions';


// const mapStateToProps = (state) => {
//   const loginMessage = state.loginMessage;
//   return {loginMessage};
// }

class SearchForm extends Component {
  constructor(props) {
    super(props);

    this.state = {
      city: ""
    }
  }

  handleCity(city) {
    this.setState({
      city
    });
  }

  render() {
    const city = this.state.city;

    return(
      <Form>
        <FormGroup>
          <Label for="cityInput">City</Label>
          <Input onChange={(event) => this.handleCity(event.target.value)} value={city} id="cityInput" placeholder="city" />
        </FormGroup>
        <Button onClick={() => this.props.searchCourts(city)} color="info">Search</Button>
      </Form>
    );
  }
}
export default connect(null, {searchCourts}) (SearchForm);