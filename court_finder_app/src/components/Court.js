import React from 'react';
import { Row, Col, Badge, Button } from "reactstrap";
import {connect} from "react-redux";
import { handleCheckInOut } from '../redux/actions';


const mapStateToProps = (state) => {
  // let userId = "none";
  // if (state.user !== null) {
  //   userId = state.user.id;
  // }
  const user = state.user;
  const courtsMap = state.courtsMap;
  // const checkedIn = state.checkedIn;
  return {user, courtsMap};
}

const authorizeCheckIn = (props, courtId, user) => {
  if (user !== null) {
    props.handleCheckInOut(courtId, user.userId);
  }
  else {
    // TODO: alert here saying "need to be logged in to check in/out"
  }
}

const isCheckedIn = (props, user) => {
  if (user === null) {
    return false;
  }
  for (var court in props.courtsMap) {
    if (props.courtsMap.hasOwnProperty(court)) {
      if (props.courtsMap[court].includes(user.userId)) {
        return true;
      }
    }
  }
  return false;
}

const Court = (props) => {
  const court = props.info;
  const address = court.streetNum + " "
                  + court.street + ", "
                  + court.city + ", "
                  + court.state + " "
                  + court.zipCode;

  return (
    <Row className="courtRow">
      <Col xs="12" md="4">
        <img className="courtImg" src={court.photoURL} alt={court.name}/>
      </Col>
      <Col className="horCenter">
        <h3>{court.name}</h3>
        <p>
          {address}
        </p>
        <Badge color="warning" pill>{court.type}</Badge>
      </Col>
      <Col>
        <Button onClick={() => authorizeCheckIn(props, court.id, props.user)}
                className="blockBtn"
                color="info"
                outline>
                {isCheckedIn(props, props.user) ? "Check out" : "Check in"}
        </Button>
        <Button className="blockBtn" color="info" outline>
          Players
          {" "}
          <Badge color="secondary" pill>{props.playersNum}</Badge>
        </Button>
      </Col>
    </Row>
  );
}
export default connect(mapStateToProps, {handleCheckInOut}) (Court);
