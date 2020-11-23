import React from 'react';
import { Button, ListGroup, ListGroupItem } from 'react-bootstrap';


class ParticipantsList extends React.Component {
  constructor(props) {
    super(props);

    this.handleClose = this.handleClose.bind(this);
    this.handleOpen = this.handleOpen.bind(this);
  }

  handleClose() {
    this.setState({
      isBackdropOpen: false
    });
  }

  handleOpen() {
    this.setState({
      isBackdropOpen: true
    });
  }

  render() {
    const participants = this.props.players.map((player, i) => 
      <ListGroupItem key={i}>
        {player}
      </ListGroupItem>);
    const startButton = this.props.players.length === 0 ?
                          <React.Fragment/> :
                          <Button variant="secondary"
                                  onClick={() => this.props.switchView("shuffling")}>
                            Empezar sorteo
                          </Button>;
    return (
      <React.Fragment>
        <ListGroup className="listGroupCustom">
          {participants}
        </ListGroup>
        {startButton}
      </React.Fragment>

    );
  }
}
export default ParticipantsList;