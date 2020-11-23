import React from 'react';
import Button from 'react-bootstrap/Button';
import TitleJumbotron from '../components/TitleJumbotron';


const Landing = (props) => {
  return (
    <div className="customContainer">
      <TitleJumbotron/>
      <Button variant="outline-secondary"
              onClick={() => props.switchView("addParticipants")}>
        Empezar
      </Button>
    </div>
  );
}
export default Landing;