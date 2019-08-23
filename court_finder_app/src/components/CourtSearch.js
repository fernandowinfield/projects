import React from 'react';
import { Jumbotron } from 'reactstrap';
import SearchForm from "./SearchForm";
// import firebase from "../data/fbConfig";


const CourtSearch = () => {
  return (
    <Jumbotron>
      <h1 className="display-3">Find courts</h1>
      <p className="lead">For the moment we only support search by city. More search options and filtering under construction</p>
      <hr className="my-2" />
      <SearchForm/>
    </Jumbotron>
  )
}
export default CourtSearch;