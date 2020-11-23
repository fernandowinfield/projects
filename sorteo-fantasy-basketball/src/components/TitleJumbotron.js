import React from 'react';
import Jumbotron from 'react-bootstrap/Jumbotron';


const TitleJumbotron = () => {
  return (
    <Jumbotron className="customJumbotron">
      <h1>Sorteo Fantasy</h1>
      <hr className="shortHr"/>
      <p>
        2020-2021
      </p>
    </Jumbotron>
  );
}
export default TitleJumbotron;