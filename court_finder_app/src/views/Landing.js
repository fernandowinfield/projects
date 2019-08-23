import React from 'react';
import OptionsBar from '../components/OptionsBar';
import { Container } from 'reactstrap';
import CourtSearch from '../components/CourtSearch';
import Courts from '../components/Courts';


const Landing = () => {
  console.log();

  return (
    <React.Fragment>
      <OptionsBar/>
      <Container>
        <CourtSearch/>
        <Courts/>
      </Container>
    </React.Fragment>
  )
}
export default Landing;