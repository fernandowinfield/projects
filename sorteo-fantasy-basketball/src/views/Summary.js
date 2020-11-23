import React from 'react';
import ListGroup from 'react-bootstrap/ListGroup';
import TitleJumbotron from '../components/TitleJumbotron';


const Summary = (props) => {
  const resultsList = props.players.map((player, i) => 
      <ListGroup.Item>
        <span className="blackFont">
          {`${props.players.length - i} `}
        </span>
        <span>
          {`${player}`}
        </span>
      </ListGroup.Item>);

  return (
    <div className="customContainer">
      <TitleJumbotron/>
      <ListGroup className="listGroupCustom">
        {resultsList}
      </ListGroup>
    </div>
  );
}
export default Summary;